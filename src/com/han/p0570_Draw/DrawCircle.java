package com.han.p0570_Draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{ // JFrame 对应窗口,可以理解成一个画框
    // 定义一个面板:
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }

    public DrawCircle() {
        // 初始化面板
        mp = new MyPanel();
        // 把面板放入到窗口
        this.add(mp);
        // 设置窗口的大小
        this.setSize(400, 300);
        // 当点击叉号就退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 使窗口可以显示
        this.setVisible(true);

    }

}


// 1. 先定义一个MyPanel  继承JPanel类, 画图形, 就在面板上画
class MyPanel extends JPanel {
    // 说明：
    // 1. MyPanel  对象就是一个画板
    // 2. Graphics g  就是把 g 理解成一支画笔
    // 3. Graphics 提供了很多绘图方法
    @Override
    public void paint(Graphics g) {// 绘图方法
        // 调用父类方法完成初始化
        super.paint(g);
        System.out.println("paint方法被调用");
        System.out.println(MyPanel.class.getResource(""));
        System.out.println(MyPanel.class.getResource("."));
        System.out.println(MyPanel.class.getResource("/"));
        System.out.println(MyPanel.class.getResource("./"));
        System.out.println(MyPanel.class.getResource("../"));
        // Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("../"));

        // 画出一个圆形
        g.drawOval(10, 10, 100, 100);

    }
}



