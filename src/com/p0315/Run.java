package com.p0315;

// 多态的应用
// 多态数组
//         数组的定义类型为父类类型，里面保存的实际元素类型为子类类型
//         应用实例:现有一个继承结构如下：要求创建 1 个 Person 对象、2 个 Student 对象和 2 个 Teacher 对象, 统一放在数组
//         中，并调用每个对象
//         say 方法. 应用实例升级：如何调用子类特有的方法，比如
//         Teacher 有一个 teach , Student 有一个 study
//         怎么调用
public class Run {
    public static void main(String[] args) {
        // 父类对象可以放进去子类
        Person[] person = new Person[5];
        person[0] = new Person("jack", 19);
        person[1] = new Student("jack", 20, 100);
        person[2] = new Student("jack", 20, 80);
        person[3] = new Teacher("june", 30, 10000);
        person[4] = new Teacher("june", 30, 20000);

        // instanceof 判断的是前面的对象是否是后面的类或者子类的运行类型，是的话返回true 否的话返回false  老韩P0311讲过

        for (int i = 0; i < person.length ; i++) {
            System.out.println(person[i].say()); // 动态绑定，编译类型是Person   运行类型是子类和父类  根据实际情况由JVM来判断
            // 运行特有的类型
            if (person[i] instanceof Student) {
                ((Student) person[i]).study();  // 向下转型
            } else if (person[i] instanceof  Teacher) {
                ((Teacher) person[i]).teach();
            }
        }
    }
}
