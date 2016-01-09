package com.dror.serializing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class JacksonSerializer implements Serializer
{
    private static final Logger log = LoggerFactory.getLogger(JacksonSerializer.class);

    private final ObjectMapper objectMapper;

    public JacksonSerializer()
    {
        log.trace("Initializing");
        this.objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
    }


    public <T> String serialize(T object)
    {
        String objectAsString = "";
        try
        {
            objectAsString = objectMapper.writeValueAsString(object);
            log.trace("object was mapped to following string: " + objectAsString);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return objectAsString;
    }

    public <T> T deserialize(String objectAsString, Class<? extends T> tClass)
    {
        T object = null;
        try
        {
            object = objectMapper.readValue(objectAsString, tClass);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return object;
    }
}
