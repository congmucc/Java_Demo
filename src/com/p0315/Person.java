package com.p0315;

public class Person {
    private String name;
    private int age;

    Person() {}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String say() {
        return "ĞÕÃû£º" + this.name + "\t" + "ÄêÁä" + this.age + "\t";
    }
}
