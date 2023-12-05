package com.heima.thread.test.packet;

import java.util.ArrayList;

public class Test4Packet {
    public static void main(String[] args) {
        Packet packet1 = new Packet();
        Packet packet2 = new Packet();
        Packet packet3 = new Packet();
        Packet packet4 = new Packet();
        Packet packet5 = new Packet();

        packet1.setName("线程1");
        packet2.setName("线程2");
        packet3.setName("线程3");
        packet4.setName("线程4");
        packet5.setName("线程5");

        packet1.start();
        packet2.start();
        packet3.start();
        packet4.start();
        packet5.start();
    }


}
