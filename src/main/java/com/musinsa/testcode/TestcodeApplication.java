package com.musinsa.testcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcodeApplication.class, args);
	}

}
