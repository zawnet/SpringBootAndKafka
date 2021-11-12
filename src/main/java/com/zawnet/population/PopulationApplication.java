package com.zawnet.population;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class PopulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopulationApplication.class, args);
	}

}
