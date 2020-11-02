package com.example.demo.live;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Main implements ApplicationRunner{
	
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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		main(null);
	}
}
