package com.springapp.mvc.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;

/**
 * @author sivakom
 */
public class TestUtil {

    private static final Logger log = LoggerFactory.getLogger(TestUtil.class);

    public static String getPrettyString(String responseJson, Object jsonClass) throws IOException {

        //JavaType javaType = TypeFactory.type(((Class) jsonClass));
        Object jsonObject = convertJsonString(responseJson, (Class) jsonClass);

        ObjectWriter writer = getObjectMapper().writerWithDefaultPrettyPrinter();
        //writer = writer.withType(javaType);
        return writer.writeValueAsString(jsonObject);
    }

    public static ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        return objectMapper;
    }

    public static String toPrettyJsonString(Object jsonObject) throws IOException {

        StringWriter writer = new StringWriter();
        getObjectMapper().writeValue(writer, jsonObject);
        final String jsonString = writer.toString();
        return getPrettyString(jsonString, jsonObject.getClass());
    }

    @SuppressWarnings("unchecked")
    public static <T> T convertJsonString(String content, Class<T> valueType) throws IOException {

        ObjectMapper objectMapper = getObjectMapper();
        return (T) objectMapper.readValue(content, (Class) valueType);
    }


    public static void assertEqualsJsonStrings(final String actual, final String expected) throws IOException {

        Assert.assertEquals(new ObjectMapper().readValue(actual, new TypeReference<HashMap<String, Object>>() {
        }), new ObjectMapper().readValue(expected, new TypeReference<HashMap<String, Object>>() {
        }), "Failed when comparing Actual: \n\t" + actual + "\nWith Expected String: \n\t" + expected);
    }

    public static void assertNotEqualsJsonStrings(final String actual, final String expected) throws IOException {
        Assert.assertNotEquals(new ObjectMapper().readValue(actual, new TypeReference<HashMap<String, Object>>() {
        }), new ObjectMapper().readValue(expected, new TypeReference<HashMap<String, Object>>() {
        }), "These two strings are the same Actual: \n\t" + actual + "\n\tWith Expected String: \n\t" + expected);
    }

    public static void assertEqualsJsonStrings(final String actual, final String expected, Class<?> valueType) throws IOException {

        String message = String.format("Actual:\n %s\nExpected: %s\n", actual, expected);
        log.info(message);
        assertLenientEquals(message, convertJsonString(actual, valueType), convertJsonString(expected, valueType));
    }

}
