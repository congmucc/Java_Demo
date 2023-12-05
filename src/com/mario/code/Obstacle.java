package com.mario.code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Obstacle implements Runnable{
   // 用于坐标
   private int x;
   private int y;
   // 记录障碍物类型
   private int type;
   // 用于显示图像
   private BufferedImage show = null;
   // 定义当前的场景
   private BackGround bg = null;
   // 定义一个线程对象，用于完成旗子下落过程
   private Thread thread = new Thread(this);


    public Obstacle(int x, int y, int type, BackGround bg) {
       this.x = x;
       this.y = y;
       this.type = type;
       this.bg = bg;
       // 加载障碍物对应的图片
       show = StaticValue.obstacle.get(type);
       // 旗子载入后，进行开始线程
       if (type == 8) {
           thread.start();
       }
   }

   public int getX() {return x;}

   public int getY() {return y;}

   public int getType() {
        return type;
    }

   public BufferedImage getShow() {
        return show;
    }

    @Override
    public void run() {
        while (true) {
            // 如果是 isReach == true的话，说明马里奥到达了旗杆的位置
            if (this.bg.isReach()) {
                // 如果旗子没有落到地上，进行慢慢降落
                if (this.y < 374) {
                    this.y += 5;
                } else {
                    // 落到地上 设置为true
                    this.bg.setBases(true);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
