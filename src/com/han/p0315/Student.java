package com.han.p0315;

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
        return super.say() + "\t" + "�ɼ���" + this.score + "\t";
    }
    // ���еķ���
    public void study() {
        System.out.println("ѧϰ");
    }
}
