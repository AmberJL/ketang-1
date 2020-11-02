package com.example.demo.live;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component//被spring容器管理
@Order(2)//如果多个自定义ApplicationRunner，用来标明执行顺序
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
