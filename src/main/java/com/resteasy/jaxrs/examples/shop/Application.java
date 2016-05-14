package com.resteasy.jaxrs.examples.shop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This class is needed to deploy a Spring Boot application into a
 * JEE Application Server (like Wildfly)
 * Created by Ant Brown on 13/05/2016.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ HERE WE ARE");
		return application.sources(Application.class);
	}

}