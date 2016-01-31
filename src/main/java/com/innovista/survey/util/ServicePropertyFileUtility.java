package com.innovista.survey.util;

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
        @PropertySource(value="classpath:fileproperties/file.properties",ignoreResourceNotFound=true),
        @PropertySource(value="classpath:serviceURL/urlconstants.properties",ignoreResourceNotFound=true),
        @PropertySource(value="classpath:util/util.properties",ignoreResourceNotFound=true),
        
})

@Configuration
public class ServicePropertyFileUtility {


    @Autowired
    private  Environment env;
    
    public  String getString(Object[] obj,String msg)
    {
        String getMsg=env.getProperty(msg);
        return MessageFormat.format(getMsg,getObject(obj));
    }
    
    
   
    public  String getStringValue(String msg)
    {
        String getMsg=env.getProperty(msg);
        return getMsg;
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