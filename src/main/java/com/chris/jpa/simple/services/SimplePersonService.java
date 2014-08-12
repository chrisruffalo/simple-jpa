package com.chris.jpa.simple.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface describing the person service
 * 
 *
 */
@Path("/person")
@Produces(value={MediaType.APPLICATION_JSON})
public interface SimplePersonService {

    @GET
    @Path("/all")
    Response all();
    
    @GET
    @Path("/{id}")
    Response person(@PathParam("id") String id);
    
    @GET // not really REST but makes it easier to test in the browser!
    @Path("/add")
    Response add();
}
