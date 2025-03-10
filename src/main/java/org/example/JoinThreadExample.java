package org.example;

class WorkerRunnable implements Runnable {
    private String name;

    public WorkerRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " has started...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is done working!");
    }
}

public class JoinThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new WorkerRunnable("Thread 1"));
        Thread t2 = new Thread(new WorkerRunnable("Thread 2"));

        t1.start();
        try {
            t1.join(); // Makes the Main-thread wait for t1 to finnish before moving on...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start(); // t2 is only able to start after t1 has finished

        System.out.println("Main is done");
    }// Main END

}// JoinThreadExample END
