package com.whatev.basicbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.whatev.basicbe.consts.ApplicationConst.V1;

@SpringBootApplication(scanBasePackages = V1)
public class BasicbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicbeApplication.class, args);
	}

}
