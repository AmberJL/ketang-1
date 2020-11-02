package com.example.demo.live;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Login implements Runnable{
	
	private Socket socket;
	
	public Login(Socket socket) {
		this.socket=socket;
	}

	@Override
	public void run() {
		try {
			DataInputStream in=new DataInputStream(socket.getInputStream());
			String login=in.readUTF();
			String[] temp=login.split(" ");
			String type=temp[0],course_id=temp[1],phone=temp[2];
			
			if("Player".equals(type)) {
				System.out.println("���Ŷ�_�γ�id:"+course_id+"_ID:"+phone);
				playerLogin(course_id,phone);
			}else if("Pusher".equals(type)) {
				System.out.println("������_�γ�id:"+course_id+"_ID:"+phone);
				pusherLogin(course_id,phone);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void pusherLogin(String course_id,String phone) {
		MapContainer.addPusher(course_id,socket,phone);
	}
	
	private void playerLogin(String course_id,String phone) {
		MapContainer.addPlayer(course_id,socket,phone);
	}

}
