package com.p0315;

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
        return super.say() + "薪水：" + this.salary + "\t";
    }

    // 特有方法
    public void teach() {
        System.out.println("讲课");
    }
}
