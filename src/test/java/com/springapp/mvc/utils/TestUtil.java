package com.springapp.mvc.utils;

import com.springapp.mvc.util.Utilities;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;

/**
 * @author sivakom
 */
public class TestUtil {

    private static final Logger log = LoggerFactory.getLogger(TestUtil.class);


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
        assertLenientEquals(message, Utilities.convertJsonString(actual, valueType), Utilities.convertJsonString(expected, valueType));
    }

}
