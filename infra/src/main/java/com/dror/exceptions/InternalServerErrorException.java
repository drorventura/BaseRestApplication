package com.dror.exceptions;


import org.springframework.http.HttpStatus;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class InternalServerErrorException extends BaseWebApplicationException
{
    public InternalServerErrorException(String message)
    {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), "50001", "Internal Server Error.", message);
    }
}
