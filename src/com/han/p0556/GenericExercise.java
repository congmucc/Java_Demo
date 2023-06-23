package com.han.p0556;
import java.util.*;

public class GenericExercise {
    public static <Set> void main(String[] args) {
        // 使用泛型的方式给HashSet 放入3个学生对象
        HashSet<Student> students = new HashSet<Student>();
        students.add(new Student("jack", 18));
        students.add(new Student("tom", 28));
        students.add(new Student("mary", 19));

        // 遍历
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("=====================");
        // 使用泛型方式给HashMap 放入3个学生对象

        HashMap<String, Student> hm = new HashMap<String, Student>();
        hm.put("milan", new Student("milan", 38));
        hm.put("smith", new Student("smith", 48));

        // 迭代器
        java.util.Set<Map.Entry<String, Student>> entries = hm.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Student> next =  iterator.next();
            System.out.println(next.getKey() + "-" +next.getValue());
        }
    }
}



class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", age=" + age +
                '}';
    }
}