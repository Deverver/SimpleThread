package org.example;

class SharedRessource {
    private int resourceAmount = 1000;

    public SharedRessource() {
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    public synchronized void increaseResourceAmount(int amount) {
        resourceAmount += amount;
    }

    public synchronized void decreaseResourceAmount(int amount) {
        resourceAmount -= amount;
    }


}// SharedRessource END

// Class that adds to SharedRessources
class RessourceAdder implements Runnable {
    SharedRessource ss;

    public RessourceAdder(SharedRessource ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        System.out.println("RessourceAdder is running");
        while (true) {
            ss.increaseResourceAmount(2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}// RessourceAdder END

// Class that subtracts from SharedRessources
class RessourceRemover implements Runnable {
    SharedRessource ss;

    public RessourceRemover(SharedRessource ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        System.out.println("RessourceRemover is running");
        while (true) {
            ss.decreaseResourceAmount(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}// RessourceRemover END

// Class that souts current value of SharedRessource
class RessourceChecker implements Runnable {
    SharedRessource ss;

    public RessourceChecker(SharedRessource ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        System.out.println("RessourceChecker is running");
        while (true) {
            System.out.println("SharedRessources is currently set at: " + ss.getResourceAmount());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}// RessourceChecker END

class PrinterThread implements Runnable {
    private char ch;

    public PrinterThread(char ch) {
        this.ch = ch;
    }

    @Override
    public synchronized void run() {
        if (ch == '#') {
            pauseFor(25);// Pauses thread for 0.025 second to create an offset
        }

        while (true) {
            for (int i = 1; i < 60; i++) {
                System.out.print(ch);
            }
            System.out.println();
            pauseFor(50);// Pauses thread for 0.050 seconds to allow the offset
        }
    }

    public void pauseFor(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}// PrinterThread END


public class SynchroOpg {
    public static void main(String[] args) {
        // Opgave 1
        // Opret en Java-applikation, der demonstrerer et delt ressourceproblem med racebetingelser og løses dette ved hjælp af trådsynkroniseringsteknikker som synchronized-blokke eller metoder
        /*
        SharedRessource sharedRessources = new SharedRessource();
        Thread t0 = new Thread(new RessourceChecker(sharedRessources));
        t0.start();
        Thread t1 = new Thread(new RessourceAdder(sharedRessources));
        t1.start();
        Thread t2 = new Thread(new RessourceRemover(sharedRessources));
        t2.start();
        */

        // Opgave 2
        //Opret en Java-applikation med to tråde, hvor den ene tråd udskriver stjerner (*) og den anden udskriver havelåger (#) til konsollen.

        Thread t3 = new Thread(new PrinterThread('*'));
        Thread t4 = new Thread(new PrinterThread('#'));

        t3.start();
        t4.start();

    }// main END

}// SynchroOpg END
