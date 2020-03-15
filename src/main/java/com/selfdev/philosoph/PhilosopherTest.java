package com.selfdev.philosoph;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vovkv on 12/9/2018.
 */
public class PhilosopherTest {

    public static void main(String args[]){
        final ReentrantLock reentrantLock = new ReentrantLock();
        // try to change it to ReentrantLock

        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Philosoph p1 = new Philosoph(fork1, fork2, 1);
        Philosoph p2 = new Philosoph(fork2, fork3, 2);
        Philosoph p3 = new Philosoph(fork3, fork4, 3);
        Philosoph p4 = new Philosoph(fork4, fork5, 4);
        Philosoph p5 = new Philosoph(fork5, fork1, 5);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
