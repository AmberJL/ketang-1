package com.example.demo.live;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {
	
	private final Socket socket;
	private final String course_id;
	private final String phone;
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public Player(Socket socket,String course_id,String phone) {
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
		MapContainer.removePlayer(course_id, phone);
		shutDown();
	}
	
	public void shutDown() {
		try {
			socket.close();
			//new Exception().printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public void push(int size, byte[] data) {
		try {
			out.writeInt(size);
			out.flush();
			out.write(data);
			out.flush();
		}catch(Exception e) {
			//e.printStackTrace();
			close();
		}
	}
}
