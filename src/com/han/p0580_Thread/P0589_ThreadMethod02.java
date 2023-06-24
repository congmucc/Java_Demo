package com.han.p0580_Thread;

public class P0589_ThreadMethod02 {

    // 线程插队
    public static void main(String[] args) throws InterruptedException {
        T2 t2 = new T2();
        t2.start();

        for (int i = 1; i <= 20; i ++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("主线程 吃了 " + i + " 包子");
            if (i == 5) {
                System.out.println("主线程让子线程先吃");
                // t2.join(); // 让t2进行插队, t2先执行

                Thread.yield();// 礼让, 不一定成功


                System.out.println("子线程吃完了,主线程再吃");
            }
        }
    }
}


class T2 extends Thread{
    public void run() {
        for (int i = 1; i <= 20; i ++) {
            try {
                Thread.sleep(1000); // 休眠一秒
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程 吃了 " + i + " 包子");
        }
    }
}