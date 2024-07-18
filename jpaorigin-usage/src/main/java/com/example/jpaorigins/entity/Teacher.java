package com.example.jpaorigins.entity;


import org.stringnull.utils.builder.BuilderProperty;

public class Teacher {
    String name;
    String major;

    public void setName(String name){
       this.name = name;
    }

    @BuilderProperty
    public void setMajor(String val){
        this.major = val;
    }
}
