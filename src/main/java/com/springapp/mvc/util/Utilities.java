package com.springapp.mvc.util;

import com.springapp.mvc.model.attic.TalkyTalkyRequest;
import com.springapp.mvc.model.attic.TalkyTalkyResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author sivakom
 */
public class Utilities {

    private static final Logger log = LoggerFactory.getLogger(Utilities.class);


    public static TalkyTalkyResponse getTalkyTalkyResponse(TalkyTalkyRequest talkyTalkyRequest) {

        String status = "ack";
        String message = String.format("%s says %s", talkyTalkyRequest.getTarget(), talkyTalkyRequest.getMessage());

        return new TalkyTalkyResponse(status, message);
    }

    public static String getPrettyString(String responseJson, Object jsonClass) throws IOException {

        //JavaType javaType = TypeFactory.type(((Class) jsonClass));
        Object jsonObject = convertJsonString(responseJson, (Class) jsonClass);

        ObjectWriter writer = getObjectMapper().writerWithDefaultPrettyPrinter();
        //writer = writer.withType(javaType);
        return writer.writeValueAsString(jsonObject);
    }

    private static ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
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

        return (T) getObjectMapper().readValue(content, (Class) valueType);
    }
}
