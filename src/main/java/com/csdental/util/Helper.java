package com.csdental.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    public static <T> Object filterListById(List<T> list,Object value) {
        return filterBy(list,"getId",value);
    }

    public static <T> Object filterBy(List<T> list, String by, Object value) {
        if(!list.isEmpty()){
            Method method= null;
            try {
                method = list.get(0).getClass().getDeclaredMethod(by);
                Iterator<T> iterator=list.iterator();
                while (iterator.hasNext()){
                    T element= iterator.next();
                    if(method.invoke(element).equals(value)){
                        return element;
                    }
                }
            } catch (NoSuchMethodException e) {
                BuildStatus.getInstance().recordError();
                logger.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                BuildStatus.getInstance().recordError();
                logger.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                BuildStatus.getInstance().recordError();
                logger.error(e.getMessage(), e);
            }

        }
        return null;
    }

    public static synchronized <T> List<T> loadJson(InputStream inputStream, Class<T> clazz) {
        List<T> list=new ArrayList<>();
        try {
            Gson gson=new Gson();
            JsonArray jsonArray= JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonArray();
            for(JsonElement jsonElement:jsonArray){
                list.add(gson.fromJson(jsonElement,clazz));
            }
        } catch (Exception e) {
            BuildStatus.getInstance().recordError();
            logger.error(e.getMessage(), e);
        }
        return list;
    }


}
