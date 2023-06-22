package com.mario.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Myframe extends JFrame implements KeyListener, Runnable {

    //用于存储所有背景
    private List<BackGround> allBg = new ArrayList<>();
    //用于存储当前背景
    private BackGround nowBg = new BackGround();
    //用于双缓存
    private Image offScreenImage = null;
    // 定义一个马里奥对象
    private Mario mario = new Mario();
    // 定义一个线程对象，实现马里奥的运动
    private Thread thread  = new Thread(this);


    public Myframe() throws IOException {
           // 窗口大小
        this.setSize(800, 600);
           // 设置窗口居中显示
        this.setLocationRelativeTo(null);
           // 设置窗口的可见性
        this.setVisible(true);
           // 设置点击窗口上的关闭键，结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // 设置窗口大小不可变
        this.setResizable(false);
           // 向窗口对象添加键盘监听器
        this.addKeyListener(this);
           // 设置窗口标题
        this.setTitle("超级玛丽");
        //初始化图片
        StaticValue.init();
        // 初始化马里奥对象
        mario = new Mario(10, 355);
        //创建全部场景
        for (int i = 1; i <= 3; i ++) {
            allBg.add(new BackGround(i, i == 3 ? true : false));
        }
        //将第一个场景设置为当前场景
        nowBg = allBg.get(0);
        // 将第一个场景的back给马里奥，来获取障碍物
        mario.setBackGround(nowBg);
        //绘制图片
        repaint();
        thread.start();
    }


    public void paint(Graphics g) {
        //判断是否为空
        if (offScreenImage == null) {
            //现在内存中创建一个图像类型的缓冲区
            offScreenImage = createImage(800, 600);
        }
        //缓冲区画笔
        Graphics graphics = offScreenImage.getGraphics();

        // 向缓冲区绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);

        // 绘制敌人
        for (Enemy e : nowBg.getEnemyLise()) {
            graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
        }

        // 向缓冲区绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }

        // 绘制城堡
        graphics.drawImage(nowBg.getTower(), 620, 270, this);

        // 绘制旗杆
        graphics.drawImage(nowBg.getGan(), 500, 220, this);

        // 绘制马里奥
        graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);

        // 把缓冲区的图片绘制到实际的窗口
        g.drawImage(offScreenImage,0, 0, this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 向右移动
        if (e.getKeyCode() == 68) {
            mario.rightMove();
        }
        // 向左移动
        if (e.getKeyCode() == 65) {
            mario.leftMove();
        }
        // 跳跃，w或者空格
        if (e.getKeyCode() == 87 || e.getKeyCode() == 32) {
            mario.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // 向左停止
        if (e.getKeyCode() == 65) {
            mario.leftStop();
        }
        // 向右停止
        if (e.getKeyCode() == 68) {
            mario.rightStop();
        }
        // 跳跃，w或者空格       马里奥遁地
        if (e.getKeyCode() == 87 || e.getKeyCode() == 32) {
            mario.fall();
        }
    }

    @Override
    public void run() {
        while (true) {
            // 绘画马里奥
            repaint();
            try {
                Thread.sleep(50);
                // 换地图
                if (mario.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(355);
                }

                if (mario.isDeath()) {
                    JOptionPane.showConfirmDialog(this, "Mario is dead");
                    System.exit(0);
                }

                // 判断游戏是否结束了
                if (mario.isReachTower()) {
                    JOptionPane.showConfirmDialog(this, "Game is over");
                    System.exit(0);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}