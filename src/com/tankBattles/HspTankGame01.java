package com.tankBattles;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class HspTankGame01 extends JFrame {

    // 定义一个MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame01 hspTankGame01 = new HspTankGame01();
    }

    public HspTankGame01() {
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);// 把面板加入进去
        // 设置窗口大小
        this.setSize(1000, 750);
        // 点击叉号关闭程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 监听键盘事件
        this.addKeyListener(mp);
        // 设置窗口可见
        this.setVisible(true);

    }
}
