package com.han;

import java.util.Scanner;
public class P0158Array {
    public static void main(String[] args) {
        int arr01[] = new int[10];
        Scanner Sc = new Scanner(System.in);
        for(int i = 0;i < 10;i++) {
            System.out.println("请输入第" + (i+1) + "个数");
            arr01[i] = Sc.nextInt();
        }
        for(int i = 0;i < 10;i++) {
            System.out.println(arr01[i]);
        }

    }
}
