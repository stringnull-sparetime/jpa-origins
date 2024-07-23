package com.example.usage.entity;


import org.stringnull.core.data.query.annotations.JPAOriginsColumn;
import org.stringnull.core.data.query.annotations.JPAOriginsEntity;
import org.stringnull.core.data.query.annotations.JPAOriginsID;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;

@JPAOriginsEntity
@JPAOriginsTable(name="Teachers")
public class Teacher {

    @JPAOriginsID
    @JPAOriginsColumn
    Integer id;

    @JPAOriginsColumn(nullable = false, length = 80)
    String name;

    @JPAOriginsColumn(length = 45)
    String major;

    @JPAOriginsColumn()
    Integer age;
}
