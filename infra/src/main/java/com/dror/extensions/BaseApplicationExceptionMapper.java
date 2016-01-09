package com.dror.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class BaseApplicationExceptionMapper implements ExceptionMapper<Exception>
{
    private static Logger log = LoggerFactory.getLogger(BaseApplicationExceptionMapper.class);

    public Response toResponse(Exception exception)
    {
        if(exception instanceof WebApplicationException)
        {
            log.info("Web Application Exception: " + exception);
            return ((WebApplicationException) exception).getResponse();
        }

        log.error("Internal Server Error: " + exception);
        log.error("Internal Server Error: " + exception.getCause());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
