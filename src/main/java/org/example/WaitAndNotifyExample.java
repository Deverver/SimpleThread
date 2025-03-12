package org.example;

import java.util.LinkedList;
import java.util.Queue;


class SharedResource {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue is full. Producer waiting...");
            wait();  // Releases lock and waits
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // Notify consumer that new item is available
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Consumer waiting...");
            wait(); // Releases lock and waits
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notify(); // Notify producer that space is available
        return value;
    }
}

class Producer extends Thread {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        int value = 1;
        while (true) {
            try {
                resource.produce(value++);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                resource.consume();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class WaitAndNotifyExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        new Producer(resource).start();
        new Consumer(resource).start();
    }


}// WaitAndNotifyExample END
