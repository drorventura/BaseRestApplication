package com.dror.rest.services;

import com.dror.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Dror
 * Date: 1/8/2016
 */
@Path("test")
@Component
@Produces({MediaType.APPLICATION_JSON})
public class TestService
{
    @Autowired
    private Environment environment;

    @Path("version")
    @GET
    public Response version()
    {
        return Response.ok().entity("Running version " + environment.getProperty("application.version")).build();
    }

    @Path("error")
    @GET
    public String errorHandlerTest()
    {
        throw new InternalServerErrorException("The error handler works fine :)");
    }
}
