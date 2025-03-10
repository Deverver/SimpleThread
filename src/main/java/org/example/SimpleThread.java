package org.example;

public class SimpleThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Running thread: " + Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(2000); // Pauses thread for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Creates 5 threads running
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new SimpleThread());
            thread.start();
        }
        System.out.println("Main methods keeps on running...");
    }// main END
}// SimpleThread END
