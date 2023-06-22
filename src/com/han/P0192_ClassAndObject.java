package com.han;

public class P0192_ClassAndObject {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        cat1.catName = "Ð¡°×";
        cat1.catAge = 12;
        cat1.catColor = "°×É«";
    }

}



class Cat {
    String catName;
    int catAge;
    String catColor;
}