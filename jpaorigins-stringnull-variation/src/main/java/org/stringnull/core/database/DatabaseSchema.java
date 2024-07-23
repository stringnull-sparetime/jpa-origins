package org.stringnull.core.database;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.stringnull.core.StringnullFramework;
import org.stringnull.core.data.query.annotations.JPAOriginsColumn;
import org.stringnull.core.data.query.annotations.JPAOriginsEntity;
import org.stringnull.core.data.query.annotations.JPAOriginsID;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Responsible for managing schema which auto drop or generate tables
 * based on annotated column
 *
 * @see JPAOriginsTable
 * @see JPAOriginsColumn
 * @see JPAOriginsID
 *
 * @author Stringnull
 */

public class DatabaseSchema {
    Package pkg = StringnullFramework.getApplication().getPackage();
    Set<Class<?>> tablesAnnotated;

    public DatabaseSchema(){
        Reflections refs = new Reflections(
        new ConfigurationBuilder()
           .setUrls(ClasspathHelper.forPackage(pkg.getName()))
           .setScanners(Scanners.TypesAnnotated)
        );
        tablesAnnotated = refs.getTypesAnnotatedWith(JPAOriginsTable.class).stream().collect(Collectors.toSet());
    }
    public void buildSchema() {
        //Schema Creator
        if(StringnullFramework.getProperty("jpaorigins.schema").equals("auto-create")){
            StringnullFramework.log("starting schema creator...");
            dropTables();
            createTables();
        }
    }
    private void dropTables(){
       tablesAnnotated.forEach(t -> {
           String tblName = t.getDeclaredAnnotation(JPAOriginsTable.class).name();
           executeUpdate("DROP TABLE IF EXISTS " + tblName + ";");
           StringnullFramework.log("✔️table dropped -> " + tblName);
       });
    }
    private void createTables(){
        tablesAnnotated.forEach(t -> {
            String tblName = t.getDeclaredAnnotation(JPAOriginsTable.class).name();
            Iterator<Field> fields = Arrays.stream(t.getDeclaredFields())
                    .filter(at -> at.isAnnotationPresent(JPAOriginsColumn.class))
                    .iterator();

            StringBuilder createQuery = new StringBuilder();
            createQuery.append("CREATE TABLE " + tblName + "(");

            while(fields.hasNext()){
               Field f =  fields.next();
               String fn = f.getName();
               String ft = asPSQLType(f);
               int length = f.getDeclaredAnnotation(JPAOriginsColumn.class).length();

               createQuery.append(" " + fn );

               if(f.isAnnotationPresent(JPAOriginsID.class) && asPSQLType(f).equals("INT"))
                   createQuery.append(" SERIAL PRIMARY KEY");
               else
                   createQuery.append(" " + ft);

               if(length !=0) createQuery.append("(" + length + ")");
               if(!f.getDeclaredAnnotation(JPAOriginsColumn.class).nullable())
                   createQuery.append(" NOT NULL");
               if(fields.hasNext()) createQuery.append(",");

            }

            createQuery.append(");");
            executeUpdate(createQuery.toString());
            StringnullFramework.log("✔️created table -> " + createQuery.toString());
        });
    }
    private String asPSQLType(Field f){
       switch(f.getType().getSimpleName()){
           case "String":
               return "VARCHAR";

           case "Integer":
           case "int":
               return "INT";

           case "Boolean":
               return "BOOLEAN";

           case "char":
               return "CHARACTER";

           default:
               return "UNDEFINED TYPE";
       }
    }
    private void executeUpdate(String query){
        try {
            //System.out.println(query);
            DatabaseManager.getConnection().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
