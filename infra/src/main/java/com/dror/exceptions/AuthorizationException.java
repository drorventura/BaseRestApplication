package com.dror.exceptions;

import org.springframework.http.HttpStatus;

/**
 * User: Dror
 * Date: 1/30/2016
 */
public class AuthorizationException extends BaseWebApplicationException
{
    public AuthorizationException(String message)
    {
        super(HttpStatus.FORBIDDEN.value(), "40301", "Not Authorized.", message);
    }
}
