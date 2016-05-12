package com.resteasy.jaxrs.examples.shop.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * This defines the path in the URL (after the host and the artifact name)
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}