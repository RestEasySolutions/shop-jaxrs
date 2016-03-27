package com.resteasy.jaxrs.examples.shop.services;

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
 * Created by Ant Brown on 21/01/2016.
 */
@Path("/customers")
public interface CustomerResource {

    @POST
    @Consumes("application/json")
    public Response createCustomer(InputStream is);

    @GET
    @Path("{id}")
    @Produces("application/json")
    public StreamingOutput getCustomer(@PathParam("id") int id);

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateCustomer(@PathParam("id") int id, InputStream is);
}
