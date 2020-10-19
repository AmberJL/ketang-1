package com.example.demo;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class Ketang1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ketang1Application.class, args);
    }

	//上传文件大小限制
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("1024MB"));
		factory.setMaxRequestSize(DataSize.parse("2048MB"));
		return factory.createMultipartConfig();
	}
}
