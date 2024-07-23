package org.stringnull.core;


import org.stringnull.core.database.DatabaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class StringnullFramework {
    static Properties properties = new Properties();
    static Class<?> application;

    public static void build(Class<?> c) {

        application = c;

        try{
            InputStream banner  = StringnullFramework.class.getClassLoader().getResourceAsStream("stringnull.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(banner))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //get application properties here
            log("obtaining PostgreSQL configuration properties");
            InputStream prop = c.getClassLoader().getResourceAsStream("stringnull.properties");
            properties.load(prop);

            DatabaseManager.connect();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        String property = properties.getProperty(key);
        if(property == null) return "no-key";
        return property;
    }

    public static void log(String log){
        String prefix = ":::::: 🦖 ::::::   ";
        System.out.println(prefix + log);
    }

    public static Class<?> getApplication(){
        return application;
    }
}
