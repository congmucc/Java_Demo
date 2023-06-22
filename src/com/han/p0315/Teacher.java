package com.han.p0315;

public class Teacher extends Person{

    String name;
    int age;
    int salary;
    Teacher() {}
    Teacher(String name, int age, int salary) {
        super(name, age);
        this.salary = salary;
    }
    public String say() {
        return super.say() + "нˮ��" + this.salary + "\t";
    }

    // ���з���
    public void teach() {
        System.out.println("����");
    }
}
