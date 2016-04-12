package com.rest.example;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by 723403 on 4/4/2016.
 */
@Path("/")
public class HelloWorld
{
    @POST
    @Path("/Hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return "Hello Royal Blob Bissell!";
    }
}
