package com.ysb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public static int strInStringNum(String str, String src, int num){
        int result = -1;
        Pattern compile = Pattern.compile(str);
        Matcher matcher = compile.matcher(src);
        int index = 1;
        while (matcher.find()){
            if (index == num){
                result = matcher.start();
            }
            index++;
        }
        return result;
    }
}
