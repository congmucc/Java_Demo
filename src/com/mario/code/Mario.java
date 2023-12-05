package com.mario.code;

import java.awt.image.BufferedImage;

public class Mario implements Runnable{
    // 用于存储马里奥的横纵坐标
    private  int x;
    private  int y;
    // 用于表示当前的状态
    private String status;
    // 用于显示当前状态对应的图像
    private BufferedImage show = null;
    // 用来获取障碍物的信息
    private  BackGround backGround = new BackGround();
    // 用来实现马里奥的动作
    private Thread thread = null;
    // 马里奥的移动速度
    private int xSpeed;
    // 马里奥的跳跃速度
    private int ySpeed;
    // 定义变量，以便获得马里奥的运动图像
    private int indexL;
    private int indexR;

    // 表示马里奥的上升时间
    private int upTime = 0;

    // 用于判断马里奥是否走到了城堡的门口
    private boolean isReachTower;

    // 判断马里奥是否死亡
    private boolean isDeath = false;

    // 积分
    private int score = 0;
    // 用于转换音乐
    Myframe myframe = null;

    public Mario() {

    }


    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        // 初始情况，马里奥向右站立
        show = StaticValue.allMarioImage.get(7);
        this.status = "stand--right";
        thread = new Thread( this);
        thread.start();
    }

    // 马里奥向左移动
    public void leftMove() {
        // 改变速度
        xSpeed = -5;
        indexL = indexL == 0 ? 2 : 0;
        // 判断马里奥是否碰到旗子
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        // 判断马里奥是否处于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }

    }

    // 马里奥向右移动
    public void rightMove() {
        // 改变速度
        xSpeed = 5;
        // 判断马里奥是否碰到旗子
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        indexL = indexL == 1 ? 3 : 1;
        // 判断马里奥是否处于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }

    }

    // 马里奥向左停止
    public void leftStop() {
        // 改变速度
        xSpeed = 0;
        // 判断马里奥是否处于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }

    }

    // 马里奥向右停止
    public void rightStop() {
        // 改变速度
        xSpeed = 0;
        // 判断马里奥是否处于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }

    }




    // 马里奥跳跃
    public void jump() {
        // 判断是否处于跳跃状态   没有则返回-1
        if (status.indexOf("jump") == -1) {
            // 跳跃背景音乐
            new AePlayWave("src/com/mario/music/jump.wav").start();
            // 如果含有left则马里奥正在向左
            if (status.indexOf("left") != -1) {
                status = "jump--left";
            } else {
                status = "jump--right";
            }
            //马里奥飞天，下面两行
            // 跳起来y轴会减小
            ySpeed = -10;
            // 设置马里奥条约的高度为70 最后要与y轴相乘，这个是跳跃的时间
            upTime = 7;
        }

        // 判断马里奥是否碰到旗子
        if (backGround.isReach()) {
            ySpeed = 0;
        }
    }

    // 马里奥下落
    public void fall() {
        // 因为有可能是从障碍物上跳下来,所以说不用判断是否处于跳跃阶段
        if (status.indexOf("left") != -1) {
            status = "jump--left";
        } else {
            status = "jump--right";
        }
        // 跳起来y轴会减小
        ySpeed = 10;
    }


    // 马里奥死亡方法
    public void death() {
        isDeath = true;
    }




    @Override
    public void run() {
        while (true) {
            // 判断马里奥是否在障碍物上
            boolean onObstacle = false;
            // 判断马里奥是否可以向右走
            boolean canRight = true;
            // 判断马里奥是否可以向左走
            boolean canLeft = true;
            // 判断玛丽奥是否达到旗杆位置 最后一个场景，并且x大于500才能通过
            if (backGround.isFlag() && this.x >= 500) {
                // 达到的话，把达到变为true
                this.backGround.setReach(true);

                // 判断旗子是否下落完成
                if (this.backGround.isBases()) {
                    status = "move--right";
                    if (x < 690) {
                        x += 5;
                    } else {
                        //表示马里奥走到了城堡口处
                        isReachTower = true;
                    }
                } else {
                    // 判断马里奥是否在空中， 如果大于395就是在空中
                    if (y < 395) {
                        xSpeed = 0;
                        // 控制马里奥逐渐下落
                        this.y += 5;
                        status = "jump--right";
                    }

                    // 如果马里奥落到最下边，让马里奥向右站立
                    if (y > 395) {
                        this.y = 395;
                        status = "stop--right";
                    }
                }

            } else {
                // 遍历当前场景里所有的障碍物
                for (int i = 0; i < backGround.getObstacleList().size(); i ++ ) {
                    Obstacle ob = backGround.getObstacleList().get(i);
                    // 判断马里奥是否在障碍物上
                    if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                       if (ob.getType() >= 5 && ob.getType() <= 6) continue;
                        onObstacle = true;
                    }

                    // 判断马里奥是否跳起来顶到砖块
                    if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        // 判断顶到的砖块是否是普通砖块  是的话把他移出去
                        if (ob.getType() == 0) {
                            this.score++;
                            backGround.getObstacleList().remove(ob);
                        }
                        upTime = 0;
                    }

                    // 判断是否可以往右走
                    if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canRight = false;
                    }
                    // 判断是否可以往左走
                    if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canLeft = false;
                    }
                }

                // 判断马里奥是否碰到敌人死亡或者踩死蘑菇敌人
                for (int i = 0; i < backGround.getEnemyLise().size(); i ++) {
                    Enemy e = backGround.getEnemyLise().get(i);
                    // 判断马里奥是否刚处于蘑菇头上
                    if (e.getY() == this.y + 20 && (e.getX() - 25 < this.x && e.getX() + 35 >= this.x)) {
                        // 如果是1，说明是蘑菇敌人，调用死亡函数
                        if (e.getType() == 1) {
                            this.score++;
                            e.death();
                            upTime = 3;
                            ySpeed = -10;
                        } else if (e.getType() == 2) {
                            // 马里奥死亡
                            death();
                        }
                    }

                    if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x) && (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) {
                        // 马里奥死亡
                        death();
                    }

                }


                // 进行马里奥跳跃的操作
                if (onObstacle && upTime == 0) {
                    // 如果含有left，说明向左的
                    if (status.indexOf("left") != -1) {
                        // 判断是否动
                        if (xSpeed != 0) {
                            status = "move--left";
                        } else {
                            status = "stop--left";
                        }
                    } else {
                        if (xSpeed != 0) {
                            status = "move--right";
                        } else {
                            status = "stop--right";
                        }
                    }
                } else {
                    if (upTime != 0) {
                        upTime--;
                    } else {
                        fall();
                    }
                    y += ySpeed;
                }
            }
            // 判断马里奥是否在运动
            if ((canRight && xSpeed > 0) || (canLeft && xSpeed < 0)) {
                x += xSpeed;
                // 判断玛丽奥是否在最左边
                if (x < 0) {
                    x = 0;
                }
            }
            // 判断是否向左移动  是的话返回true, 否则返回false
            if ("move--left".equals(status)) {
                // 这点优化可以把向左移动和向右移动的图像单独列为一个数组，然后之间三元表达式一下就好了。
                // 这个意思是，刷新大跑和小跑的图像
                indexL = indexL == 0 ? 2 : 0;
                show = StaticValue.allMarioImage.get(indexL);
            }
            // 判断是否向右移动
            if ("move--right".equals(status)) {
                indexR = indexR == 1 ? 3 : 1;
                show = StaticValue.allMarioImage.get(indexR);
            }
            // 判断是否向左站立
            if ("stop--left".equals(status)) {
                show = StaticValue.allMarioImage.get(6);
            }
            // 判断是否向右站立
            if ("stop--right".equals(status)) {
                show = StaticValue.allMarioImage.get(7);
            }
            // 马里奥此时是否是向左跳跃
            if ("jump--left".equals(status)) {
                show = StaticValue.allMarioImage.get(4);
            }
            // 马里奥此时是否是向右跳跃
            if ("jump--right".equals(status)) {
                show = StaticValue.allMarioImage.get(5);
            }


            try {
                // 休眠50ms
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
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


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }
    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }
    public boolean isReachTower() {
        return isReachTower;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public int getScore() {
        return score;
    }
    public String getStatus() {
        return status;
    }

}
