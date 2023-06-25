package com.tankBattles;

import java.util.Vector;

public class Tank {
    // 坦克坐标
    private int x;
    private int y;
    private int speed = 2;// 坦克速度
    private int direct;// 坦克的方向 0 1 2 3  表示上右下左
    // 判断是否存活
    boolean isLive = true;


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    // 向上移动0
    public void moveUp() {
        // 加上边界
        if (y - this.speed > 0) y -= speed;

    }
    // 向右移动1
    public void moveRight() {
        if (x + this.speed < 940) x += speed;
    }
    // 向下移动2
    public void moveDown() {
        if (y + this.speed < 660) y += speed;

    }
    // 向左移动3
    public void moveLeft() {
        if (x - this.speed > 0) x -= speed;

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
