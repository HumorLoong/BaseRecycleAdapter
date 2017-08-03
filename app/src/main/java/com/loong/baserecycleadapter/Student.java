package com.loong.baserecycleadapter;

/**
 * Created by lxl on 2017/7/17.
 */

public class Student {
    private String name;
    private String studyClass;
    private int sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyClass() {
        return studyClass;
    }

    public void setStudyClass(String studyClass) {
        this.studyClass = studyClass;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studyClass='" + studyClass + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
