package com.dror.extensions;

import com.dror.serializing.Serializer;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class BaseApplicationJsonProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

    @Autowired
    private Serializer serializer;

    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);
    }

    public T readFrom(Class<T> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        String entityStreamAsString = IOUtils.toString(inputStream);
        return serializer.deserialize(entityStreamAsString, aClass);
    }

    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);
    }

    public long getSize(T t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        String objectAsString = serializer.serialize(t);
        byte[] bytes = objectAsString.getBytes();
        return bytes.length;
    }

    public void writeTo(T t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        String objectAsString = serializer.serialize(t);
        byte[] bytes = objectAsString.getBytes();
        outputStream.write(bytes);
    }
}
