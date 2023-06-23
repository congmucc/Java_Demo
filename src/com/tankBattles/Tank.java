package com.tankBattles;

public class Tank {
    // 坦克坐标
    private int x;
    private int y;
    private int speed = 2;// 坦克速度
    private int direct;// 坦克的方向 0 1 2 3  表示上右下左

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

    // 向上移动
    public void moveUp() {
        y -= speed;
    }
    // 向右移动
    public void moveRight() {
        x += speed;
    }
    // 向下移动
    public void moveDown() {
        y += speed;
    }
    // 向左移动
    public void moveLeft() {
        x -= speed;
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
