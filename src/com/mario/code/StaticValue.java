package com.mario.code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    // 第一关背景图片
    public static BufferedImage bg = null;
    // 最后一关背景图片
    public static BufferedImage bg2 = null;
    // 全部状态马里奥图片
    public static List<BufferedImage> allMarioImage = new ArrayList<>();
    // 所有障碍物图片
    public static List<BufferedImage> obstacle = new ArrayList<>();
    // 蘑菇敌人
    public static List<BufferedImage> mogu = new ArrayList<>();
    // 食人花敌人
    public static List<BufferedImage> flower = new ArrayList<>();
    // 城堡图片
    public static BufferedImage tower = null;
    // 旗杆图片
    public static BufferedImage gan = null;

    // 路径前缀，方便后续调用
    public static String imagePath = System.getProperty("user.dir") + "/src/com/mario/images/";
    public static void init() throws IOException {
        // 导入马里奥的状态图片

        try {
            // 马里奥小跑0-1
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_run1_L.png")));
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_run1_R.png")));
            // 马里奥大跑2-3
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_run2_L.png")));
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_run2_R.png")));
            // 马里奥跳4-5
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_jump1_L.png")));
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_jump1_R.png")));
            // 马里奥站立图片6-7
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_stand_L.png")));
            allMarioImage.add(ImageIO.read(new File(imagePath + "s_mario_stand_R.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 导入背景图片
        try{
            bg = ImageIO.read(new File(imagePath + "bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            bg2 = ImageIO.read(new File(imagePath + "bg2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 导入所有障碍物图片
        try{
            // 普通的砖块 0
            obstacle.add(ImageIO.read(new File(imagePath + "brick.png")));
            // 上地面图片 1
            obstacle.add(ImageIO.read(new File(imagePath + "soil_up.png")));
            // 下地面图片 2
            obstacle.add(ImageIO.read(new File(imagePath + "soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //导入水管图片 3-6
        for (int i = 1; i <= 4; i ++) {
                try {
                    obstacle.add(ImageIO.read(new File(imagePath + "pipe" + i + ".png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        // 导入不可破坏的砖块和旗子 7-8
        try{
            // 不可破坏的砖块
            obstacle.add(ImageIO.read(new File(imagePath + "brick2.png")));
            obstacle.add(ImageIO.read(new File(imagePath + "flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载蘑菇敌人
        for (int i = 1; i <= 3; i ++) {
            try {
                mogu.add(ImageIO.read(new File(imagePath + "fungus" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 加载食人花敌人
        for (int i = 1; i <= 2; i ++) {
            try {
                flower.add(ImageIO.read(new File(imagePath + "flower" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 导入旗杆
        gan = ImageIO.read(new File(imagePath + "gan.png"));

        // 导入城堡
        tower = ImageIO.read(new File(imagePath + "tower.png"));
    }
}
