package com.han.p0391;

public class SingleTon01 {
    public static void main(String[] args) {
        // GirFriend girFriend1 = new GirFriend("小红");
        // GirFriend girFriend2 = new GirFriend("小白");

        // 通过方法可以获取对象
        GirFriend instance = GirFriend.getInstance();
        System.out.println(instance);
    }
}

class GirFriend {
    private String name;

    // 为了能够在静态方法中,返回gf对象,需要将其修饰为static
    private static GirFriend gf = new GirFriend("小红红");

    // 单例模式: 饿汉式
    // 1. 将构造器私有化
    // 2. 在类的内部直接创建(该对象是static)
    // 3.提供一个公共的static法, 返回gf对象

    private GirFriend(String name) {
        this.name = name;
    }

    public static GirFriend getInstance() {
        return gf;
    }

    @Override
    public String toString() {
        return "GirFriend{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
