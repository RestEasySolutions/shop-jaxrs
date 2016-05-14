package com.resteasy.jaxrs.examples.shop.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

/*
 * This defines the path in the URL (after the host and the artifact name)
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton(CustomerResourceService.class);
	}
}