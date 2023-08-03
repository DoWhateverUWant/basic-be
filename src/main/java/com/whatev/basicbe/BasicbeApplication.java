package com.whatev.basicbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BasicbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicbeApplication.class, args);
	}

}
