package com.example.Faida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FaidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaidaApplication.class, args);
	}

}
