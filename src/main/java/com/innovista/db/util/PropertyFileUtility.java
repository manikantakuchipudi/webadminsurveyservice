package com.innovista.db.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.text.MessageFormat;

/**
 * Created by mkuchipudi on 29-09-2015.
 */

@PropertySources({
        @PropertySource(value="classpath:sqlqueries/survey.properties",ignoreResourceNotFound=true)
})

@Configuration
public class PropertyFileUtility {


    @Autowired
    private  Environment env;
    public  String getString(Object[] obj,String msg)
    {
        String getMsg=env.getProperty(msg);
        return MessageFormat.format(getMsg,getObject(obj));
    }


    private Object[] getObject(Object[] object)
    {
        for(int obj=0;obj<object.length;obj++)
        {
            if(object[obj] instanceof String) {
                object[obj] = "\'" + object[obj] + "\'";
            }
        }
        return object;
    }





}