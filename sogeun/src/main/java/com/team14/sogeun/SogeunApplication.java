package com.team14.sogeun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SogeunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SogeunApplication.class, args);
	}

}
