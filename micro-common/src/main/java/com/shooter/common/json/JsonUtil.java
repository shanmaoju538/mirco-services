package com.shooter.common.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static {
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Module dateSerializerModule = new SimpleModule("DateSerializerModule", new Version(1, 0, 0, null)).addSerializer(Date.class, new DateSerializer());
        mapper.registerModule(dateSerializerModule);
        Module dateDeserializerModule = new SimpleModule("DateDeserializerModule", new Version(1, 0, 0, null)).addDeserializer(Date.class,
                new DateDeserializer());
        mapper.registerModule(dateDeserializerModule);
    }

    public static String convertToString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("{} convert to json failed.", obj, e);
        }
        return null;
    }

    public static <T> T convertToObject(String value, Class<T> clazz) {
        if (value == null || value.trim().equals("")) {
            return null;
        }
        try {
            return mapper.readValue(value, clazz);
        } catch (Exception e) {
            logger.error("{} convert to object failed.", value.toString(), e);
        }
        return null;
    }

    public static class DateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jp.getText());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            return date;
        }
    }

    public static class DateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = "";
            if (value != null) {
                dateStr = dateFormat.format(value);
            }
            jgen.writeString(dateStr);
        }
    }

}
