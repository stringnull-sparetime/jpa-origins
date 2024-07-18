package org.stringnull.core;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class FrameworkConfig {
    Properties properties = new Properties();
    Connection connection;
    public FrameworkConfig build(Class<?> c) {

        try{
            //get application properties here
            log("obtaining PostgreSQL configuration properties");
            InputStream prop = c.getClassLoader().getResourceAsStream("stringnull.properties");
            properties.load(prop);
        }catch(IOException e){
            e.printStackTrace();
        }

        //database config
        try {
            log("starting connection for " + getProperty("jpaorigins.url"));
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                getProperty("jpaorigins.url"),
                getProperty("jpaorigins.username"),
                getProperty("jpaorigins.password")
            );

            if(!connection.isClosed())
                log("Successfully connected!");

            //TEST row print
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Students");
            ResultSetMetaData resultMeta = resultSet.getMetaData();

            int columnCount = resultMeta.getColumnCount();
            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultMeta.getColumnName(i) + "\t\t\t");
            }
            System.out.println();

            // Print rows
           while(resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t\t\t");
                }
                System.out.println();
            }

            //resultSet.

        }catch(SQLException sex) {
            sex.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }

        return this;
    }

    public String getProperty(String key){
        String property = properties.getProperty(key);
        return property;
    }

    private void log(String log){
        String prefix = ":::::: JPAORIGINS ::::::   ";
        System.out.println(prefix + log);
    }
}
