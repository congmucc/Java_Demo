package com.han.p0391;

public class SingleTon02 {
    public static void main(String[] args) {

    }
}


class Cat {
    private String name;

    private static Cat cat;

    // 懒汉式
    // 1. 将构造器私有化
    // 2. 在类的内部直接定义(该对象是static)  不是创建
    // 3.提供一个公共的static法, 返回gf对象


    private Cat (String name) {
        this.name = name;
    }
    public static Cat getInstance() {
        if (cat == null) { // 如果没有创建cat对象就创建一个
            cat = new Cat( "小白");
        }
        return cat;
    }
}

