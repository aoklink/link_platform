package com.linkfeeling.platform.util;

import java.lang.reflect.Field;

public class BeanWriteUtil {
    public static <T> T write(Class<T> tClass,T changeFrom,T changeTo) {
        for(Field field : tClass.getDeclaredFields()){
            field.setAccessible(true);
            try {
                Object result = field.get(changeTo);
                if(result!=null){
                    field.set(changeFrom,result);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return changeFrom;
    }
}
