package com.bossware.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bossware.app.api.shared.entities.User;

@SpringBootApplication
public class SpringBootWebServiceV1Application {

	public static void main(String[] args) {
		User user = new User();
	
		SpringApplication.run(SpringBootWebServiceV1Application.class, args);
	}

}
