package org.stringnull.core.database;

import org.stringnull.core.StringnullFramework;

import java.sql.*;

public class DatabaseManager {
    static Connection connection;

    public static void connect(){
        try {

            StringnullFramework.log("database url " + StringnullFramework.getProperty("jpaorigins.url"));
            StringnullFramework.log("starting connection ....");


            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                StringnullFramework.getProperty("jpaorigins.url"),
                StringnullFramework.getProperty("jpaorigins.username"),
                StringnullFramework.getProperty("jpaorigins.password")
            );
            StringnullFramework.log("database connection open");
            if(!connection.isClosed())
                StringnullFramework.log("Successfully connected!");


        }catch(SQLException sex) {
            sex.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }

    public static Connection getConnection(){
       return connection;
    }
}


