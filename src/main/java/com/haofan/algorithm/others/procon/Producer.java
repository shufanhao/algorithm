package com.haofan.algorithm.others.procon;

import java.util.List;

public class Producer extends Thread {

    private List<Integer> list;
    private int maxSize;

    public Producer(List<Integer> list, int maxSize, String name) {
        super(name);
        this.list = list;
        this.maxSize = maxSize;
    }

    public void run() {
        while (true) {
            synchronized (list) {
                if (list.size() == maxSize) {
                    try {
                        System.out.println("已经生产满了，stop produce");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int num = (int) (Math.random() * 100) + 1;
                    System.out.println("生产者生产了：" + num);
                    list.add(num);
                    list.notify();
                }
            }
        }
    }
}
