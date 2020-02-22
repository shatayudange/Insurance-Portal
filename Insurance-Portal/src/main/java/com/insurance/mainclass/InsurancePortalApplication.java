package com.insurance.mainclass;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.insurance.mainapplication.controller.CustomerController;

@ComponentScan({"com.insurance.mainapplication.controller"})
@EnableJpaRepositories("com.insurance.mainapplication.repository")
@EntityScan("com.insurance.mainapplication.model")
@SpringBootApplication
public class InsurancePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancePortalApplication.class, args);
	}

}
