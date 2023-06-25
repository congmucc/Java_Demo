package com.tankBattles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

// 为了让panel 不停的绘制子弹，需要将mypanel实现Runnable ，当作一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable{
    // 定义我的坦克
    MyTank myTank = null;
    // 定义敌人的坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    // 定义一个vector用于存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    // 设置敌人坦克个数
    int enemyTanksSize = 5;

    // 定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        myTank = new MyTank(200, 500); // 初始化位置为100,100
        // 初始化敌人坦克
        for (int i = 0; i < enemyTanksSize; i ++) {
            // 创建一个敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            // 将enemyTanks 设置给enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            // 设置方向
            enemyTank.setDirect(2);
            // 启动敌人坦克线程， 让他动起来
            new Thread(enemyTank).start();
            // 给该敌人坦克加入一棵子弹
            Shot enemyShot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            // 加入到enemyTank的vector成员
            enemyTank.shots.add(enemyShot);
            // 开启线程
            new Thread(enemyShot).start();
            // 加入敌人坦克到vector中
            enemyTanks.add(enemyTank);
        }
        // 初始化图片图像
        image1 = null;
        image2 = null;
        image3 = null;

    }

    public void paint(Graphics g) {
        // 继承父类
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // 填充矩形, 默认为黑色
        if (myTank != null && myTank.isLive) {
            // 绘制我的坦克
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }

        // 绘制我的坦克子弹
        for (int i = 0; i < myTank.shotVector.size(); i ++) {
            if (myTank.shotVector.get(i) != null && myTank.shotVector.get(i).isLive) {
                g.draw3DRect(myTank.shotVector.get(i).x, myTank.shotVector.get(i).y, 2, 2, false);
            } else {// 如果子弹碰到东西， 要删去
                myTank.shotVector.remove(myTank.shotVector.get(i));
            }
        }


        // 如果bombs 集合中有对象， 就画出
        for (int i = 0; i < bombs.size(); i ++) {
            // 取出炸弹
            Bomb bomb  = bombs.get(i);
            // 根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            // 让这个炸弹的生命值减少
            bomb.lifeDown();
            // 如果 bomb life 为0， 就从bombs 的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }


        }

        // 画出敌人的坦克， 遍历vector 来画出敌人坦克子弹
        for (int i = 0; i < enemyTanks.size(); i ++) {
            // 从vector取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            // 判断当前坦克是否还活着
            if(enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), i + 1);
                // 画出 enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    // 取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    // 绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        // 从vector 移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        // 速度提醒
        g.setColor(Color.RED);
        g.setFont(new Font("黑体", Font.BOLD, 25));
        g.drawString("当前速度为：" + myTank.getSpeed(), 750, 35);
        g.drawString("操作方法：WASD", 750, 65);
        g.drawString("shift+WASD进行加速", 750, 95);

    }

    // 编写方法,画出坦克
    /**
     *
      * @param x 坦克的横坐标位置
     * @param y  坦克的纵坐标位置
     * @param g  画笔
     * @param direct  坦克的方向(上右下左)
     * @param type  坦克的种类
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // 根据不同类型的坦克,设置不同颜色
        switch (type) {
            // 我的坦克
            case 0 :
                g.setColor(Color.cyan);
                break;
            // 敌人的坦克
            case 1 :
                g.setColor(Color.yellow);
                break;
            case 2 :
                g.setColor(Color.green);
                break;
            case 3 :
                g.setColor(Color.pink);
                break;
            default:
                g.setColor(Color.WHITE);
        }

        // 根据坦克的方向,来绘制坦克
        switch (direct) {
            case 0 : // 表示向右
                g.fill3DRect(x, y, 10, 60, false);// 左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);// 右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 画出圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);// 画出炮筒
                break;
            case 1 :  //表示向右
                g.fill3DRect(x, y, 60, 10, false);// 上轮子
                g.fill3DRect(x, y + 30, 60, 10, false);// 下轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);// 坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);// 画出圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);// 画出炮筒
                break;
            case 2 : // 表示向下
                g.fill3DRect(x, y, 10, 60, false);// 左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);// 右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 画出圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);// 画出炮筒
                break;
            case 3 : // 表示向左
                g.fill3DRect(x, y, 60, 10, false);// 上轮子
                g.fill3DRect(x, y + 30, 60, 10, false);// 下轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);// 坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);// 画出圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);// 画出炮筒
                break;
            default :
                System.out.println("暂时没有处理");
        }
    }


    // 发射多个子弹射中敌人
    public void hitEnemyTank() {
        // 遍历我们的子弹
        for (int j = 0; j < myTank.shotVector.size(); j ++) {
            Shot shot = myTank.shotVector.get(j);
            // 判断是否击中了敌人的坦克
            if (shot != null  && shot.isLive) { // 当我们的子弹还活着
                // 遍历敌人所有坦克
                for (int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }

            }
        }
    }



    //编写方法，判断我方的子弹是否击中敌人坦克.
    //什么时候判断 我方的子弹是否击中敌人坦克 ? run方法
    //后面我们将 enemyTank 改成 tank名称
    public void hitTank(Shot s, Tank tank) {
        //判断s 击中坦克
        switch (tank.getDirect()) {
            case 0: //坦克向上
            case 2: //坦克向下
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(tank);
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: //坦克向右
            case 3: //坦克向左
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                        && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(tank);
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }


    public void hitMyTank() {
        // 遍历敌人所有的坦克
        for (int i = 0; i < enemyTanks.size(); i ++) {
            // 取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            // 遍历enemyTank 对象所有子弹（是个vector）
            for (int j = 0; j < enemyTank.shots.size(); j ++) {
                // 取出子弹
                Shot shot = enemyTank.shots.get(j);
                // 判断shot是否击中我们的坦克
                if (myTank.isLive && shot.isLive) {
                    hitTank(shot, myTank);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 向上移动
        if (e.getKeyCode() == 87) {
            myTank.setDirect(0);
            myTank.moveUp();
        }

        // 向右移动
        if (e.getKeyCode() == 68) {
            myTank.setDirect(1);
            myTank.moveRight();
        }

        // 向下移动
        if (e.getKeyCode() == 83) {
            myTank.setDirect(2);
            myTank.moveDown();
        }

        // 向左移动
        if (e.getKeyCode() == 65) {
            myTank.setDirect(3);
            myTank.moveLeft();
        }
        // shift + 其他按键 == 加速度
        if (e.getKeyCode() == 16) {
            myTank.setSpeed(6);
        }

        // j 发射子弹
        if (e.getKeyCode() == 74) {
            myTank.shotEnemyTank();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // shift + 其他按键 == 加速度
        if (e.getKeyCode() == 16) {
            myTank.setSpeed(2);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 判断是否击中了敌人的坦克，多发子弹情况
            hitEnemyTank();
            // 判断敌人坦克是否击中坦克
            hitMyTank();
            this.repaint();
        }
    }
}
