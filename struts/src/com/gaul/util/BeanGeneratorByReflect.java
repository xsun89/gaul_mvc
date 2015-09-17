package com.gaul.util;

import com.gaul.bean.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * Created by gao on 2015/9/17.
 */
public class BeanGeneratorByReflect {
    public static FormBean generateBean(String beanClass, HttpServletRequest request) {
        Class clazz = null;
        FormBean formBean = null;
        try {
            clazz = Class.forName(beanClass);
            formBean = (FormBean) clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(formBean, request.getParameter(field.getName()));
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formBean;
    }
}
