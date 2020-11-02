package com.example.demo.live;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Pusher implements Runnable{

	private final Socket socket;
	private final String course_id;
	private final String phone;
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public Pusher(Socket socket,String course_id,String phone) {
		this.socket=socket;
		this.course_id=course_id;
		this.phone=phone;
		
		try {
			this.in=new DataInputStream(socket.getInputStream());
			this.out=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		MapContainer.removePusher(course_id);
		shutDown();
	}
	
	public void shutDown() {
		try {
			socket.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	@Override
	public void run() {
		for(;;) {
			try {
				int size=in.readInt();
				//System.out.println(size);
				byte[] bytes=new byte[size];
				int did=0;
                for(;;) {
                	int sizeTemp=in.read(bytes,did,bytes.length-did);
                	//System.out.println("\t"+sizeTemp);
                	did+=sizeTemp;
                	if(sizeTemp==0)break;
                	if(sizeTemp==-1)return;
                }
                new Thread(new Runnable() {
					@Override
					public void run() {
						MapContainer.push(course_id, size, bytes);
					}
                }).start();
			} catch (IOException e) {
				//e.printStackTrace();
				close();
				return;
			}
		}
	}
}
