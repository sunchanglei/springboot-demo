package com.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    static final Map<String,Object> defaultMap = new HashMap<>();
    static {
        defaultMap.put("int",0);
        defaultMap.put("Integer",0);
        defaultMap.put("String","");
        defaultMap.put("Date",new Date());
        defaultMap.put("BigDecimal",BigDecimal.ZERO);
        defaultMap.put("byte",0);
        defaultMap.put("long",0L);
        defaultMap.put("Long",0L);
        defaultMap.put("Map",new HashMap<>());
        defaultMap.put("List",new ArrayList());
    }
    public static void main(String[] args) throws Exception{


    }

    public static void copy (Object vo,Object bo) {

        try {
            Field[] fields = vo.getClass().getDeclaredFields();
            Class bzz = bo.getClass();
            for (Field field : fields){
                if ("serialVersionUID".equals(field.getName())){
                    continue;
                }
                Field bfield = bzz.getDeclaredField(field.getName());
                field.setAccessible(true);
                bfield.setAccessible(true);

                String voTypeName = field.getType().getSimpleName();
                if (bfield == null){
                    field.set(vo,defaultMap.get(voTypeName));
                    continue;
                }
                if(!field.getName().equals(bfield.getName()) || !voTypeName.equals(bfield.getType().getSimpleName())){
                    field.set(vo,defaultMap.get(voTypeName));
                    continue;
                }
                Object o = bfield.get(bo);
                field.set(vo,o == null ? defaultMap.get(voTypeName):o);
            }
        } catch(Exception e){
            logger.error("",e);
        }
    }
}
