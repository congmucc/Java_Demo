package com.tankBattles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
    // 定义我的坦克
    MyTank myTank = null;
    // 定义敌人的坦克
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
    int enemyTanksSize = 3;
    public MyPanel() {
        myTank = new MyTank(100, 100); // 初始化位置为100,100
        // 初始化敌人坦克
        for (int i = 0; i < enemyTanksSize; i ++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }

    public void paint(Graphics g) {
        // 继承父类
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // 填充矩形, 默认为黑色
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0); // 绘制我的坦克
        for (int i = 0; i < enemyTanks.size(); i ++) {
            drawTank(enemyTanks.get(i).getX(),enemyTanks.get(i).getY(),g, enemyTanks.get(i).getDirect(), i + 1);
        }


        // 速度提醒
        g.setColor(Color.RED);
        g.setFont(new Font("黑体", Font.BOLD, 25));
        g.drawString("当前速度为：" + myTank.getSpeed(), 750, 35);
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
        // f2 加速度
        if (e.getKeyCode() == 113) {
            myTank.setSpeed(myTank.getSpeed() + 1);
        }
        // f1 减速度
        if (e.getKeyCode() == 112) {
            myTank.setSpeed(myTank.getSpeed() - 1);
        }
        // 让面板重绘
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
