package com.dror.serializing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class JacksonSerializerTest
{
    private Serializer serializer;

    @Before
    public void setup()
    {
        serializer = new JacksonSerializer();
    }

    @Test
    public void testSerialize()
    {
        Person person = preparePersonObject();

        String personAsJson = serializer.serialize(person);
        Assert.assertEquals("{\"id\":1,\"name\":\"Dror Ventura\",\"date\":\"01-01-2016\"}", personAsJson);
    }

    @Test
    public void testDeserialize()
    {
        Person person = preparePersonObject();
        String personAsJson = "{\"id\":\"1\",\"name\":\"Dror Ventura\"}";
        Person deserializePerson = serializer.deserialize(personAsJson, Person.class);
        Assert.assertEquals(person.getId(), deserializePerson.getId());
        Assert.assertEquals(person.getName(), deserializePerson.getName());
    }

    private Person preparePersonObject()
    {
        Person person = new Person();
        person.setId(1);
        person.setName("Dror Ventura");
        Calendar instance = Calendar.getInstance();
        instance.set(2016,Calendar.JANUARY,1);
        person.setDate(instance);
        return person;
    }

    public static class Person
    {
        int id;
        String name;
        Calendar date;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Calendar getDate()
        {
            return date;
        }

        public void setDate(Calendar date) {
            this.date = date;
        }
    }
}
