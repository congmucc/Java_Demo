package com.p0307;

public class Polymorphism {
    public static void main(String[] args) {
        // 体验对象多态的特点


        // animal 编译类型是Animal 运行类型是 Dog
        Animal animal = new Dog();
        // 因为运行时，执行到该行时， animal运行类型是Dog， 所以cry就是Dog的cry
        animal.cry();


        // animal 编译类型是 Animal  运行类型是 Cat
        animal = new Cat();
        animal.cry();

    }
}