package com.p0315;

public class Student extends Person{
    String name;
    int age;
    int score;
    Student() {}
    Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public String say() {
        return super.say() + "\t" + "成绩：" + this.score + "\t";
    }
    // 特有的方法
    public void study() {
        System.out.println("学习");
    }
}
