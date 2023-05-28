package com.grm.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class SpringSecurityWithJwtAndRole3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJwtAndRole3Application.class, args);
	}

}
