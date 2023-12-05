package com.heima.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStreamUse {
    public static void main(String[] args) throws IOException {
        // 创建文件
        File f1 = new File("src/static/FileInputStream");
        // System.out.println(f1.mkdir());
        File f2 = new File(f1, "/create.txt");
        // System.out.println(f2.createNewFile());

        // 使用文件进行书写
        // byte[] bytes = {97,98};
        // byte[] dataToAppend = "fgh".getBytes();
        // FileOutputStream fos = new FileOutputStream("src/static/FileInputStream/create.txt", true);
        // fos.write(bytes);
        // fos.write(dataToAppend);
        // fos.close();


        // 读出文件
        // byte[] bytes1 = new byte[1024];
        // FileInputStream fis = new FileInputStream(new File("src/static/FileInputStream/create.txt"));
        // int read = fis.read(bytes1);
        // System.out.println(read);
        // System.out.println(new String(bytes1));
        // // while((read = fis.read()) != -1) {
        // //     System.out.print((char)read);
        // // }
        // while((read = fis.read(bytes1)) != -1) {
        //     System.out.println(new String(bytes1, 0, read));
        // }
        // fis.close();

        // 复制文件
        // FileInputStream fis1 = new FileInputStream(new File("src/static/FileInputStream/create.txt"));
        //
        // FileOutputStream fos1 = new FileOutputStream(new File("src/static/FileInputStream/createcopy.txt"));
        //
        // // 读写数据
        // int by;
        // while((by = fis1.read()) != -1) {
        //     fos1.write(by);
        // }
        // // 释放资源
        // fos1.close();
        // fis1.close();

        // 利用字节数组赋值文件
        FileInputStream fis2 = new FileInputStream(new File("src/static/FileInputStream/create.txt"));

        FileOutputStream fos2 = new FileOutputStream(new File("src/static/FileInputStream/createcopy.txt"));

        byte[] bytes2 = new byte[1024];
        // 读写数据
        int by = 0;
        while((by = fis2.read(bytes2)) != -1) {
            fos2.write(bytes2, 0, by);
        }
        // 释放资源
        fos2.close();
        fis2.close();


    }
}
