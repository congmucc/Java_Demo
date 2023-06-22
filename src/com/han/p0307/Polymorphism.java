package com.han.p0307;

public class Polymorphism {
    public static void main(String[] args) {
        // ��������̬���ص�


        // animal ����������Animal ���������� Dog
        Animal animal = new Dog();
        // ��Ϊ����ʱ��ִ�е�����ʱ�� animal����������Dog�� ����cry����Dog��cry
        animal.cry();


        // animal ���������� Animal  ���������� Cat
        animal = new Cat();
        animal.cry();

    }
}