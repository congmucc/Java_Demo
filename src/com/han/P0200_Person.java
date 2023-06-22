package com.han;

public class P0200_Person {
    public static void main(String[] args) {
        Person a = new Person();
        a.personAge = 10;
        a.personName = "小明";
        Person b;
        b = a;
        System.out.println("a.personAge = " + a.personAge);
        b.personAge = 15;
        System.out.println("a.personAge = " + a.personAge);
        b = null;
        System.out.println("a.personAge = " + a.personAge);
        System.out.println("b.personAge = " + b.personAge);

        //报错的原因是b = null  java里面的 = 可以理解为指针，此时因为b不是直接new出来的
        //在堆区没有空间 所以说经过b = a 之后  b和a一起指向a开辟的堆区空间  所以说b.personAge = 15
        //使得a.personAge = 15    之后b = null,理解为b现在指向空了 所以说报错
    }
}


class Person {
    String personName;
    int personAge;
}