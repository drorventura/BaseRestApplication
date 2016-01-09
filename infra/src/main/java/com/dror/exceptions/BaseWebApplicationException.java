package com.dror.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class BaseWebApplicationException extends WebApplicationException
{
    protected final int status;
    protected final String errorCode;
    protected final String errorMessage;
    protected final String developerMessage;

    public BaseWebApplicationException(int status, String errorCode, String errorMessage, String developerMessage)
    {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.developerMessage = developerMessage;
    }

    @Override
    public Response getResponse()
    {
        return Response.status(status)
                .entity(getErrorResponse())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public ErrorResponse getErrorResponse()
    {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(errorCode);
        error.setErrorMessage(errorMessage);
        error.setAdditionalInfo(developerMessage);
        return error;
    }
}
