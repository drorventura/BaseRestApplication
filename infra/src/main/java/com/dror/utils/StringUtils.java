package com.dror.utils;

import java.util.regex.Pattern;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class StringUtils
{
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$");

    public static boolean isValidUuid(String uuid)
    {
        return UUID_PATTERN.matcher(uuid).matches();
    }
}
