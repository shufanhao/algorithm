package procon;

import java.util.List;

public class Consumer extends Thread {

    private List<Integer> list;
    private int maxSize;

    public Consumer(List<Integer> list, int maxSize, String name) {
        super(name);
        this.list = list;
        this.maxSize = maxSize;
    }

    public void run() {
        while (true) {
            synchronized (list) {
                if (list.isEmpty()) {
                    try {
                        System.out.println("仓库空了，等待.....");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("正在消费： " + list.get(list.size() - 1));
                    list.remove(list.size() - 1);
                    list.notify();
                }
            }
        }
    }
}
