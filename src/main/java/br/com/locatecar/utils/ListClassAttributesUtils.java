package br.com.locatecar.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListClassAttributesUtils {

    public static <T> List<String> getAvailableAttributes(Class<T> tClass) {
        List<String> attributes = new ArrayList<>();
        collectAttributes(tClass, attributes);
        return attributes;
    }

    private static void collectAttributes(Class<?> classToVerify, List<String> attributes) {
        if (classToVerify != null) {
            Field[] fields = classToVerify.getDeclaredFields();
            for (Field field : fields) {
                attributes.add(field.getName());
            }
            collectAttributes(classToVerify.getSuperclass(), attributes);
        }
    }
}
