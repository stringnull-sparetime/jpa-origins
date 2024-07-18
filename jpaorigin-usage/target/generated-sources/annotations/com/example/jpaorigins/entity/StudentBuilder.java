package com.example.jpaorigins.entity;

import com.example.jpaorigins.entity.Student;
public class StudentBuilder {

    private Student object = new Student();

    public Student build() {
        return object;
    }

    public StudentBuilder setName(java.lang.String value) {
        object.setName(value);
        return this;
    }

    public StudentBuilder setEmail(java.lang.String value) {
        object.setEmail(value);
        return this;
    }

}
