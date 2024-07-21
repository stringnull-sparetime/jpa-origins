package com.example.jpaorigins.entity;

import org.stringnull.core.data.query.annotations.JPAOriginsColumn;
import org.stringnull.core.data.query.annotations.JPAOriginsEntity;
import org.stringnull.core.data.query.annotations.JPAOriginsID;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;
import org.stringnull.utils.builder.BuilderProperty;

@JPAOriginsEntity
@JPAOriginsTable(name = "Students")
public class Student {
    @JPAOriginsID
    @JPAOriginsColumn(name = "id")
    private int id;

    @JPAOriginsColumn()
    private String name;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @BuilderProperty
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    @BuilderProperty
    public void setEmail(String email) {
        this.email = email;
    }
}
