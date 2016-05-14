package com.resteasy.jaxrs.examples.shop.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;

/**
 * This interface defines the path and the operations that can be applied to this resource
 * We don't need to use an interface, we could just add the operation and path annotations
 * to a concrete class, but this makes it easier to see what's available without cluttering up the
 * implementation
 * Created by Ant Brown on 21/01/2016.
 */

// Identifies this resource (type) in the request URL
@Path("/customers")
@Component
public interface CustomerResource {

	// What we expect the format of the request to be
	// There needs to be a matching Content-Type header in the request otherwise this will error
	@POST
	@Consumes("application/json")
	Response createCustomer(InputStream is);

    @GET
	// An additional part of the URL to specify this particular resource
	@Path("{id}")
	// The format of the response (used to populate the response header
	@Produces("application/json")
	StreamingOutput getCustomer(@PathParam("id") int id);

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
	void updateCustomer(@PathParam("id") int id, InputStream is);
}
