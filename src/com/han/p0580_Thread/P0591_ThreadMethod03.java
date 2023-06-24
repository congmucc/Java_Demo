package com.han.p0580_Thread;

public class P0591_ThreadMethod03 {
    public static void main(String[] args) {
        // 设置守护线程
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        // 如果我们希望当main线程结束后,子线程自动结束
        // 只需要将子线程设为守护线程即可   设置的时候要在 start() 前面
        myDaemonThread.setDaemon(true); // 设置守护线程
        myDaemonThread.start();

        for (int i = 0; i < 10; i ++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("宝强在幸苦的工作");
        }

    }
}


class MyDaemonThread extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("马蓉和宋喆在聊天");
        }
    }
}