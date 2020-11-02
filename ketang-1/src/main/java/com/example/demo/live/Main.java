package com.example.demo.live;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	
	private static final int PORT=8180;
	private static ServerSocket server;
	
	public static void main(String[] args) {
		try {
			server=new ServerSocket(PORT);
			for(;;) {
				new Thread(new Login(server.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
