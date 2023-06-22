package com.han.p0308;

public class Master {
    private String name;
    public Master(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //ʹ�ö�̬���ƣ�����ͳһ�Ĺ�������ιʳ������
//animal ���������� Animal,����ָ��(����) Animal ����Ķ���
//food ���������� Food ,����ָ��(����) Food ����Ķ���
    public void feed(Animal animal, Food food) {
        System.out.println("���� " + name + " �� " + animal.getName() + " �� " + food.getName());
    }
//���˸�С�� ιʳ ��ͷ
// public void feed(Dog dog, Bone bone) {
// System.out.println("���� " + name + " �� " + dog.getName() + " �� " + bone.getName());
// }
// //���˸� Сèι �ƻ���
// public void feed(Cat cat, Fish fish) {
// System.out.println("���� " + name + " �� " + cat.getName() + " �� " + fish.getName());
// }
//�������ܶ࣬ʳ��ܶ�
//===> feed �����ܶ࣬�����ڹ����ά��
//Pig --> Rice
//Tiger ---> meat ... //...
}