package com.ysb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * @author yinshuaibin
 * @date 2020/6/3 16:10
 */
public class JacksonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T>T jsonStrToBean(@NonNull String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }

    public static String beanToJsonStr(@NonNull Object o){
        if (o == null) {
            return null;
        }
        try {
            return o instanceof String ? (String) o : objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
