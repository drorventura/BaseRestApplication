package com.dror.serializing;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public interface Serializer
{
    <T> String serialize(T object);
    <T> T deserialize(String objectAsString, Class<? extends T> tClass);
}
