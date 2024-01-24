package com.skilldistillery.quickfix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuickFixHomeHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickFixHomeHubApplication.class, args);
	}

	@Bean
	PasswordEncoder configurePasswordEncoder() {
	   return new BCryptPasswordEncoder();
	}
	
}
