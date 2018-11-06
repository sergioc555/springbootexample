package com.websystique.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.websystique.springboot.model.Customer;

@SpringBootApplication(scanBasePackages = { "com.websystique.springboot" }) // same as @Configuration
																			// @EnableAutoConfiguration @ComponentScan
																			// combined
public class SpringBootRestApiApp {

	private static final Logger log = LoggerFactory.getLogger(SpringBootRestApiApp.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringBootRestApiApp.class, args);
	}

}
