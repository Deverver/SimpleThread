package org.example;

public class PingPongExample implements Runnable {
    private String Word;

    public PingPongExample(String word) {
        Word = word;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("Running thread: " + Thread.currentThread().getName() + " -iteration- " + i + ": " + this.Word);
            try {
                Thread.sleep(1000); // Pauses thread for 1 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Thread pingThread = new Thread(new PingPongExample("Ping"));
        Thread zhongThread = new Thread(new PingPongExample("Zhong"));
        Thread gongThread = new Thread(new PingPongExample("Gong"));
        Thread pongThread = new Thread(new PingPongExample("Pong"));

        pingThread.setPriority(Thread.MAX_PRIORITY);
        zhongThread.setPriority(7);
        gongThread.setPriority(3);
        pongThread.setPriority(Thread.MIN_PRIORITY);

        pingThread.start();
        zhongThread.start();
        gongThread.start();
        pongThread.start();

        System.out.println("Main methods keeps on running...");

    }// main END
}// SimpleThread END


