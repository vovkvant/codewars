package com.selfdev.philosoph;

/**
 * Created by vovkv on 12/9/2018.
 */
public class Philosoph implements Runnable {

    private Fork fork1;
    private Fork fork2;
    private int number;
    private int eatingTime;
    private int thinkingTime;


    public Philosoph(Fork fork1, Fork fork2, int number) {
        this.fork1 = fork1;
        this.fork2 = fork2;
        this.number = number;
    }

    public void run() {
        try {
            while (true) {
                synchronized (fork1) {
                    if (fork1.isBusy()) {
                        fork1.wait();
                        think();
                    } else {
                        fork1.setBusy(true);
                        if (fork2.isBusy()) {
                            fork1.setBusy(false);
                            think();
                        } else {
                            fork2.setBusy(true);
                            eat();
                            fork1.setBusy(false);
                            fork2.setBusy(false);
                            think();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        try {

            System.out.println("Philosoph number " + number + " eating for " + eatingTime + " thinking for " + thinkingTime);
            int delay = (int)(Math.random() * 1000d);
            eatingTime+=delay;
            Thread.sleep((int)delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() {
        try {
            System.out.println("Philosoph number " + number + " eating for " + eatingTime + " thinking for " + thinkingTime);
            int delay = (int)(Math.random() * 1000d);
            thinkingTime+=delay;
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
