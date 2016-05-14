package com.resteasy.jaxrs.examples.shop.spring;

import com.resteasy.jaxrs.examples.shop.hostaccess.CustomerDatabase;
import com.resteasy.jaxrs.examples.shop.hostaccess.CustomerDatabaseFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by Ant Brown on 13/05/2016.
 */
@SpringBootApplication
public class SpringConfig {

	@Bean
	public CustomerDatabaseFactory customerDatabaseFactory() {
		System.out.println("IN CONFIG");

		return new CustomerDatabaseFactory();
	}

	@Bean
	public CustomerDatabase customerDatabase(CustomerDatabaseFactory factory) {
		System.out.println("IN CONFIG");
		return factory.getCustomerDatabase("HashMap");
	}
}
