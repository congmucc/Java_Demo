package com.tankBattles;

import java.util.Vector;

public class MyTank extends Tank {

    // 定义一个Shot对象, 表示一个射击线程
    Shot shot = null;

    // 定义一个vector存shot，来实现多发子弹
    Vector<Shot> shotVector = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        // 创建shot对象
        switch (getDirect()) {// 得到myTank方向
            case 0: // 向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: // 向右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: // 向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: // 向左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        if (shotVector.size() < 5) shotVector.add(shot);

        // 启动我们的Shot线程
        new Thread(shot).start();
    }
}
