package com.tankBattles;

public class Shot implements Runnable{
    // 子弹坐标
    int x;
    int y;
    // 子弹方向
    int direct = 0;
    //子弹的速度
    int speed = 3;
    // 子弹是否存活
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (direct) {
                case 0 : // 上
                    y -= speed;
                    break;
                case 1 : // 右
                    x += speed;
                    break;
                case 2 : // 下
                    y += speed;
                    break;
                case 3 : // 左
                    x -= speed;
                    break;
            }
            // 子弹移动到边界的时候就进行销毁
            // 挡子弹碰到敌人坦克时， 也应该销毁
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750) && isLive) {
                isLive = false;
                break;
            }
        }
    }
}
