package com.han.p0580_Thread;

public class P0587_ThreadExit {
    public static void main(String[] args) {
        T t = new T();
        t.start();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 让线程退出去  设置一个boolean变量， 然后把run()  里面的while()形参为boolean变量, 在主函数中进行修改boolean变量就能使线程退出
        t.setLoop(false);
    }
}


class T extends Thread {


    private int count = 0;
    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(++count);
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }


}