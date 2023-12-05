package com.heima.thread.test.packet;

import java.util.Random;

public class Packet extends Thread {

    // 总金额
    static double packets = 100;

    // 三个红包
    static int count = 3;

    // 最小金额
    final double min = 0.01;

    @Override
    public void run() {
        synchronized (Packet.class) {
            if (count == 0) {
                System.out.println(Thread.currentThread() + "没有抢到");
            } else {
                double money;
                if (count == 1) {
                    money = packets;
                } else {
                    Random random = new Random();
                    // 100 - (3 - 1) * 0.01 = 99.98  这是第一个最多的
                    money = (packets - (count - 1) * min) * random.nextDouble();
                    if (money < min) money = min;
                }
                System.out.println(Thread.currentThread() + "抢到了" + money + "元");
                packets = packets - money;
                count--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
