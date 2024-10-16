package com.ebalefn.dest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ebalefn.dest.client","com.ebalefn.dest.controllers"})
public class DestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DestApplication.class, args);
	}

}
