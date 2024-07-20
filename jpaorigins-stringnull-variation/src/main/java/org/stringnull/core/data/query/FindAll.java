package org.stringnull.core.data.query;

import org.stringnull.core.StringnullFramework;
import org.stringnull.core.data.JPAOriginsCrudRepositoryInvocationHandler;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;
import org.stringnull.core.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FindAll<T> implements JPAOriginsCrudRepositoryInvocationHandler.MethodHandler {

    //add constructor that will handle the entity manager
    private final Class<T> entity;
    public FindAll(Class<T> c){
       entity = c;
    }
    @Override
    public Object invoke(Object[] args) {
           try{
                String query = "SELECT * FROM " +  entity.getAnnotation(JPAOriginsTable.class).name() + ";";
                ResultSet resultSet =
                    DatabaseManager
                        .getConnection()
                        .createStatement()
                        .executeQuery(query);

                StringnullFramework.log("query statement executed " + query);

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
           }catch(SQLException sex) {
              sex.printStackTrace();
           }
        return null;
    }
}
