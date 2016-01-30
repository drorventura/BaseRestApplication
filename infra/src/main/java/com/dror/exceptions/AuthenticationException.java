package com.dror.exceptions;

import org.springframework.http.HttpStatus;

/**
 * User: Dror
 * Date: 1/30/2016
 */
public class AuthenticationException extends BaseWebApplicationException
{
    public AuthenticationException(String message)
    {
        super(HttpStatus.UNAUTHORIZED.value(), "40101","Authentication Error.", message);
    }
}
