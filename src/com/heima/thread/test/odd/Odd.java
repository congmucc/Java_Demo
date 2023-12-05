package com.heima.thread.test.odd;

public class Odd implements Runnable{
    public static int i = 1;
    @Override
    public void run() {
        while (i <= 100) {
            synchronized (Odd.class) {
                try {
                    if (i % 2 == 1) System.out.println("线程" + Thread.currentThread() + "打印了奇数：" + i++);
                    else i++;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
