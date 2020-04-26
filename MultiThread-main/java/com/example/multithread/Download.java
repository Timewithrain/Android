package com.example.multithread;

public class Download implements Runnable {

    @Override
    public void run() {
        System.out.println("I am downloading!");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("50% finished！");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("70% finished！");
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("98% finished！");
        System.out.println("Download complete!");
    }
}
