package com.whatev.basicbe;

import com.whatev.basicbe.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Import(AppV1Config.class)
@EnableJpaAuditing
@SpringBootApplication
public class BasicbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicbeApplication.class, args);
	}

}
