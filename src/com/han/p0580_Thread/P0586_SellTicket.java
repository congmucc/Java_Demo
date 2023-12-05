package com.han.p0580_Thread;

import com.han.p0315.Run;

public class P0586_SellTicket {
    public static void main(String[] args) {
        // 实现继承的方式进行售票
        // SellTicket01 sellTicket01 = new SellTicket01();
        // SellTicket01 sellTicket02 = new SellTicket01();
        // SellTicket01 sellTicket03 = new SellTicket01();
        // sellTicket01.start();
        // sellTicket02.start();
        // sellTicket03.start();

        // // 实现接口的方式进行售票
        // SellTicket02 sellTicket02 = new SellTicket02();
        //
        //  new Thread(sellTicket02).start(); // 第一个线程窗口
        //  new Thread(sellTicket02).start(); // 第二个线程窗口
        //  new Thread(sellTicket02).start(); // 第三个线程窗口

        // 使用同步方法进行测试
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start(); // 第一个线程窗口
        new Thread(sellTicket03).start(); // 第二个线程窗口
        new Thread(sellTicket03).start(); // 第三个线程窗口

    }
}

// // 用继承Thread来实现三个窗口售票
// class SellTicket01 extends Thread {
//     private static int ticketNum = 100;// 让多个线程共享， ticketNum
//
//     public void run() {
//         while (true) {
//             if (ticketNum <= 0) {
//                 System.out.println("售票结束");
//                 break;
//             }
//             try {
//                 Thread.sleep(50);
//             } catch (InterruptedException e) {
//                 throw new RuntimeException(e);
//             }
//             System.out.println("窗口： " + Thread.currentThread().getName() + "售出一张票" + "剩余票数：" + (--ticketNum));
//         }
//     }
// }

// class SellTicket02 implements Runnable {
//     private int ticketNum = 100;// 让多个线程共享 ticketNum
//
//         public void run() {
//         while (true) {
//             if (ticketNum <= 0) {
//                 System.out.println("售票结束");
//                 break;
//             }
//             try {
//                 Thread.sleep(50);
//             } catch (InterruptedException e) {
//                 throw new RuntimeException(e);
//             }
//             System.out.println("窗口： " + Thread.currentThread().getName() + "售出一张票" + "剩余票数：" + (--ticketNum));
//         }
//     }
// }

// 实现接口方式，使用synchronized实现线程同步
class SellTicket03 implements Runnable {
    private int ticketNum = 1000;// 让多个线程共享 ticketNum
    private boolean loop = true;


    public /*synchronized*/  void sell() { // 同步方法， 在同一时刻， 只能有一个线程来执行run方法
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票结束");
                loop = false;
                return;
            }
            System.out.println("窗口： " + Thread.currentThread().getName() + "售出一张票" + "剩余票数：" + (--ticketNum));
        }
    }

    public void run() {
        while (loop) {
            sell();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}