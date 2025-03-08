package org.example;

public class SimpleThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Kører tråd: " + Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(1000); // Pauser tråden i 1 sekund
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SimpleThread());
        thread.start();
        System.out.println("Main-metoden kører videre...");
    }
}
