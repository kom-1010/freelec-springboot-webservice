package com.sbweb.freelecspringbootwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class FreelecSpringbootWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelecSpringbootWebserviceApplication.class, args);
	}

}
