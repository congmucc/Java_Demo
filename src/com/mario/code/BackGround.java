package com.mario.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    // 当前场景图片
    private BufferedImage bgImage = null;
    // 场景顺序
    private int sort;
    // 是否为最后的场景
    private boolean flag;

    //用于存放所有的障碍物
    private List<Obstacle> obstacleList = new ArrayList<>();
    // 用于存放所有的敌人
    private List<Enemy> enemyLise = new ArrayList<>();


    // 用于显示旗杆
    private  BufferedImage gan = null;

    // 用于显示城堡
    private BufferedImage tower = null;

    // 用于玛丽奥是否到达旗杆位置
    private boolean isReach = false;

    // 判断棋子是否落地
    private boolean isBases = false;


    public BackGround(){

    }



    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if (flag) {
            // 赋场景为最后一关的图片
            bgImage = StaticValue.bg2;

        } else {
            // 赋场景为前两关的图片
            bgImage = StaticValue.bg;
        }
        //判断是否是第一关
        if (sort == 1) {
            // 绘制第一关地面, 上地面type=1，下地面type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            // 绘制下地面
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j <= 27; j ++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }

            // 绘制砖块从左到右
            // 最左边的
            for (int i = 120; i <= 150; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }
            // 右边的
            for (int i = 300; i <= 570; i += 30) {
                if (i == 360 || i == 390 || i == 480 || i ==510 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 7, this));
                } else {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }
            // 上边的
            for(int i = 420; i <= 450; i += 30) {
                obstacleList.add(new Obstacle(i, 240, 7, this));
            }
            //绘制水管
            for (int i = 360;i <= 600;i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }
            }
            // 绘制第一关蘑菇敌人
            enemyLise.add(new Enemy(580, 385, true, 1, this));
            enemyLise.add(new Enemy(635, 420,true, 2, 328, 418, this));


        } else if (sort == 2) {
            // 绘制第一关地面, 上地面type=1，下地面type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            // 绘制下地面
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j <= 27; j ++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }

            //绘制第一个水管
            for (int i = 360;i <= 600;i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60,i,3,this));
                    obstacleList.add(new Obstacle(85,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(60,i,5,this));
                    obstacleList.add(new Obstacle(85,i,6,this));
                }
            }

            //绘制第二个水管
            for (int i = 330;i <= 600;i += 25) {
                if (i == 330) {
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }
            }

            // 绘制砖块 从左到右
            // 绘制三角的
            // 第一块最上边的
            obstacleList.add(new Obstacle(300, 330, 0, this));
            // 第二小三角
            for (int i = 270; i <= 330; i += 30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i, 360, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 360, 7, this));
                }
            }
            // 最下边一行的
            for (int i = 240; i <= 360; i += 30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i, 390, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 390, 7, this));
                }
            }
            // 最上边的砖块
            obstacleList.add(new Obstacle(240, 300, 0, this));

            // 绘制制空的
            for (int i = 360; i <= 540; i += 60) {
                obstacleList.add(new Obstacle(i, 270, 7, this));
            }
            // 绘制第二关的第一个食人花敌人
            enemyLise.add(new Enemy(75, 420, true, 2, 328, 418, this));
            // 绘制第二关的第二个食人花敌人
            enemyLise.add(new Enemy(635, 420, true, 2, 298, 388, this));
            // 绘制第二关的第一个蘑菇敌人
            enemyLise.add(new Enemy(200, 385, true, 1, this));
            // 绘制第二关的第二个蘑菇敌人
            enemyLise.add(new Enemy(500, 385, true, 1, this));

        } else if (sort == 3) {
            // 绘制第一关地面, 上地面type=1，下地面type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            // 绘制下地面
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j <= 27; j ++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }

            // 绘制前三个砖块
            int temp = 60;
            for (int i = 390; i >= 360; i -= 30) {
                for (int j = temp;j <= 90; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            // 绘制后面的砖块
            temp = 290;
            for (int i = 390; i >= 270; i -= 30) {
                for (int j = temp;j <= 410; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            // 绘制旗杆
            gan = StaticValue.gan;

            // 绘制城堡
            tower = StaticValue.tower;

            // 添加旗子到旗杆上
            obstacleList.add(new Obstacle(515, 220, 8, this));
            // 绘制第三关的第一个蘑菇敌人
            enemyLise.add(new Enemy(150, 385, true, 1, this));
        }
    }



    public BufferedImage getBgImage() {
        return bgImage;
    }

    public void setBgImage(BufferedImage bgImage) {
        this.bgImage = bgImage;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
    public void setReach(boolean reach) {
        isReach = reach;
    }

    public boolean isReach() {
        return isReach;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }
    public void setBases(boolean bases) {
        isBases = bases;
    }

    public boolean isBases() {
        return isBases;
    }

    public List<Enemy> getEnemyLise() {
        return enemyLise;
    }

}
