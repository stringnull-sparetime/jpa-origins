package com.example.jpaorigins.entity;

import com.example.jpaorigins.entity.Teacher;
public class TeacherBuilder {

    private Teacher object = new Teacher();

    public Teacher build() {
        return object;
    }

    public TeacherBuilder setMajor(java.lang.String value) {
        object.setMajor(value);
        return this;
    }

}
