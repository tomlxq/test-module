package com.example.entity;

/**
 * Created by tom on 2016/6/25.
 */
public class Student {
    private int id;
    private String name;
    public int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
    }

    public Student(int age, String name) {

        this.age = age;
        this.name = name;
    }

    public void hello(String name) {
        System.out.println("hello," + name);
    }

    public void hello(String name, int age) {
        System.out.println("我是" + name + "， 年龄" + age + "岁");
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
