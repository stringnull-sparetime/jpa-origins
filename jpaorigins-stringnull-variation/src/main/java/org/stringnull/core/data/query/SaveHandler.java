package org.stringnull.core.data.query;

import org.stringnull.core.data.JPAOriginsCrudRepositoryInvocationHandler;
import org.stringnull.core.data.query.annotations.JPAOriginsID;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;
import org.stringnull.core.database.DatabaseManager;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SaveHandler<T> implements JPAOriginsCrudRepositoryInvocationHandler.MethodHandler{

    @Override
    public Object invoke(Object[] args) {

        /**
         * INSERT INTO tbl_name(column_names[])
         * VALUES( q_marks[] )
          */

        Class entity = args[0].getClass();
        String tblName = args[0].getClass().getDeclaredAnnotation(JPAOriginsTable.class).name();
        Map<Integer, Object> mapTarget = new HashMap<>();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder query = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        query.append("INSERT INTO " + tblName + "(");

        Arrays
        .stream(entity.getDeclaredFields())
        .forEach(f-> {

            try {
                f.setAccessible(true);
                if(!f.isAnnotationPresent(JPAOriginsID.class)){
                    mapTarget.put(index.getAndIncrement(), f.get(args[0]));
                    columns.append(f.getName() + " ");
                    values.append("? ");
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        query.append(
           columns.toString().trim().replace(" ",", ") + ") " +
           "VALUES(" + values.toString().trim().replace(" ",", ") + ");"
        );

        System.out.println(query);

        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query.toString());
            mapTarget.forEach((i, obj) -> {
                try {
                    statement.setObject(i, obj);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println(statement.toString());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
