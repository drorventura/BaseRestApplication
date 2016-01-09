package com.dror.extensions;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.LinkedList;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class AuthenticationHelper
{
    public static boolean isAuthenticated()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    public static String getCurrentUsername()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public static Collection<String> getCurrentRoles()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        Collection<String> roles = new LinkedList<>();
        for(GrantedAuthority authority : authorities)
        {
            String role = authority.getAuthority();
            roles.add(role);
        }

        return roles;
    }
}
