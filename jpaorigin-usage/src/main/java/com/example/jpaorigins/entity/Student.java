package com.example.jpaorigins.entity;

import org.stringnull.core.data.query.annotations.JPAOriginsEntity;
import org.stringnull.core.data.query.annotations.JPAOriginsTable;
import org.stringnull.utils.builder.BuilderProperty;

//import javax.persistence.*;


@JPAOriginsEntity
@JPAOriginsTable(name = "Students")
public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private int id;

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
