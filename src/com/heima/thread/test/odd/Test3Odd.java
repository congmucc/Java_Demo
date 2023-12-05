package com.heima.thread.test.odd;

public class Test3Odd {

    /*
    同时开启两个线程，共同获取1-100之间的所有数字要求：将输出所有的奇数
     */
    public static void main(String[] args) {
        Odd odd1 = new Odd();
        Odd odd2 = new Odd();
        Thread thread1 = new Thread(odd1);
        Thread thread2 = new Thread(odd2);
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start();
        thread2.start();
    }

}
