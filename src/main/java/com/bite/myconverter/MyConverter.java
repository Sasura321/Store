package com.bite.myconverter;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器 :表单String的日期--->保存到Date日期
 */
public class MyConverter implements Converter {
    /**
     *
     * @param clazz   需要被转换的类型
     * @param value   需要转换的对象
     * @return
     */
    @Override
    public Object convert(Class clazz, Object value) {

        //需String--->Date
        //创建SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            //解析
           Date date =  sdf.parse((String)value) ;
           return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
