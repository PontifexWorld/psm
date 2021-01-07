package com.example.psm;

import lombok.Data;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-26 10:55
 **/
@Data
public class Student {

    private String name;

    private Integer age;

    private Integer stature;


    public Student() {
    }

    public Student(String name, Integer age, Integer stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }
}