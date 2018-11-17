package procon;

import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String args[]) {
        List<Integer> queue = new LinkedList<>();
        Consumer consumer = new Consumer(queue, 10, "Consumer");
        Producer producer = new Producer(queue, 10, "Producer");

        consumer.start();
        producer.start();
    }
}
