package org.example;

public class ExtendedThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Running thread: " + Thread.currentThread().getName() + " -iteration- " + i);
            try {
                Thread.sleep(2000); // Pauses thread for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            ExtendedThread thread1 = new ExtendedThread();
            ExtendedThread thread2 = new ExtendedThread();
            thread1.start();
            thread2.start();
            System.out.println("thread pair " + i + " is running");
        }




    }// main END
}// ExtendedThread END