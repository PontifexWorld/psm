package com.example.lesscode.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 工具类
 *
 * @author liubicong
 * @since 2020/7/9 6:24 下午
 */
public class JsonUtils {

    /**
     * 对象序列化成Json
     *
     * @param object（任何Object类的子类）
     * @return json字符串
     */
    public static String serializeJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Json反序列化成 POJO类,list集合对象,Map对象
     *
     * @param jsonString json字符串
     * @param beanType   (传入目标对象的运行时类，eg: List.class,Map.class 等)
     * @return 返回目标对象(List, Map 等)
     */
    public static <T> T deserializeJson(String jsonString, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T t = mapper.readValue(jsonString, beanType);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
