package com.han.p0315;

// ��̬��Ӧ��
// ��̬����
//         ����Ķ�������Ϊ�������ͣ����汣���ʵ��Ԫ������Ϊ��������
//         Ӧ��ʵ��:����һ���̳нṹ���£�Ҫ�󴴽� 1 �� Person ����2 �� Student ����� 2 �� Teacher ����, ͳһ��������
//         �У�������ÿ������
//         say ����. Ӧ��ʵ����������ε����������еķ���������
//         Teacher ��һ�� teach , Student ��һ�� study
//         ��ô����
public class Run {
    public static void main(String[] args) {
        // ���������ԷŽ�ȥ����
        Person[] person = new Person[5];
        person[0] = new Person("jack", 19);
        person[1] = new Student("jack", 20, 100);
        person[2] = new Student("jack", 20, 80);
        person[3] = new Teacher("june", 30, 10000);
        person[4] = new Teacher("june", 30, 20000);

        // instanceof �жϵ���ǰ��Ķ����Ƿ��Ǻ���������������������ͣ��ǵĻ�����true ��Ļ�����false  �Ϻ�P0311����

        for (int i = 0; i < person.length ; i++) {
            System.out.println(person[i].say()); // ��̬�󶨣�����������Person   ��������������͸���  ����ʵ�������JVM���ж�
            // �������е�����
            if (person[i] instanceof Student) {
                ((Student) person[i]).study();  // ����ת��
            } else if (person[i] instanceof  Teacher) {
                ((Teacher) person[i]).teach();
            }
        }
    }
}
