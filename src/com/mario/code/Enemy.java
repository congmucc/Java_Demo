package com.mario.code;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
    // 存储当前坐标
    private int x;
    private int y;
    // 存储敌人类型
    private int type;
    // 判断敌人运动的方向
    private boolean face_to = true;
    // 用于显示敌人的当前图像  用于删除敌人和使敌人防止移动
    private BufferedImage show;
    // 定义一个背景对象
    private BackGround bg;
    // 食人花的极限范围
    private int max_up = 0;
    private int max_down = 0;

    // 实现线程对象，用于敌人的运动
    Thread thread = new Thread(this);
    // 表示当前图片的状态
    private int image_type = 0;




    public Enemy() {}

    // 蘑菇敌人构造函数
    public Enemy(int x, int y, boolean face_to, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.bg = bg;
        show = StaticValue.mogu.get(0);
        thread.start();
    }

    // 食人花敌人构造函数
    public Enemy(int x, int y, boolean face_to, int type, int max_up, int max_down, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.max_up = max_up;
        this.max_down = max_down;
        this.bg = bg;
        show = StaticValue.flower.get(0);
        thread.start();
    }

    // 死亡方法
    public void death() {
        show  = StaticValue.mogu.get(2);
        this.bg.getEnemyLise().remove(this);
    }



    @Override
    public void run() {
        while (true) {
            // 判断敌人是否是蘑菇敌人
            if (type == 1) {
                // 蘑菇敌人的方向是哪里 true的时候向左移动，false的时候向右移动
                if (face_to) {
                    this.x -= 2;
                } else {
                    this.x +=2;
                }
                // 切换蘑菇行走的图片
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.mogu.get(image_type);
            }

            // 定义两个布尔变量
            boolean canLeft = true;
            boolean canRight = true;
            // 遍历当前场景所有障碍物
            for (int i = 0; i < bg.getObstacleList().size(); i ++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                // 判断是否可以向右走
                if (ob1.getX() == this.x + 36 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canRight = false;
                }
                // 判断是否可以向左走
                if (ob1.getX() == this.x - 36 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canLeft = false;
                }
            }
            // 是否向左走，已经碰见了障碍物(不能走就是碰见了障碍物)，或者走到了最左侧  将蘑菇方向改变一下
            if (face_to && !canLeft || this.x == 0) {
                face_to = false;
            } else if ((!face_to) && (!canRight) || this.x == 764) {
                face_to = true;
            }

            // 是否是食人花敌人
            if (type == 2) {
                // 判断食人花敌人的运动方向  true  为向上移动， false  为向下移动
                if (face_to) {
                    this.y -= 2;
                } else {
                    this.y += 2;
                }

                image_type = image_type == 1 ? 0 : 1;
                // 食人花是否达到极限位置
                // 食人花是否向上移动，并且移动到了上极限位置
                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }
                if ((!face_to) && (this.y == max_down)) {
                    face_to = true;
                }
                show = StaticValue.flower.get(image_type);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {
        return type;
    }


}

