package com.p0318_Equals;

public class Equals {
    public static void main(String[] args) {

        // == 判断即可以判断基本类型，又可以判断引用类型
        // equals 只能判断引用类型  即所指向的空间是否相等
        A a = new A();
        A b = a;
        A c = b;
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(a.equals(c));

        B bObj = a;
        System.out.println(bObj == c);
        System.out.println(bObj.equals(c));

        System.out.println("hello".equals("hell"));

    }
}

class B{}
class A extends B{

}