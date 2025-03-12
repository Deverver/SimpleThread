package org.example;

// Classes for SynchroOpg1
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

// Classes for SynchroOpg2
class PrinterCounter {
    private int countValue = 0;

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }
}

class PrinterThread implements Runnable {
    final PrinterCounter counter;
    private final char ch;

    public PrinterThread(char ch, PrinterCounter counter) {
        this.ch = ch;
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (counter) {
            while (true) {
                try {
                    for (int i = 1; i <= 60; i++) {
                        System.out.print(ch);
                        counter.setCountValue(counter.getCountValue() + 1);
                    }
                    System.out.print(" " + counter.getCountValue());
                    System.out.println();

                    Thread.sleep(1000);

                    counter.notify(); // Notify the other thread
                    counter.wait(); // Wait for the other thread to finish

                    // Since notify() is called before wait(), deadlocks are avoided
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}// PrinterThread END

// Classes for Selvstudie
class SharedThreadCounter {
    private int turnCounter = 0;

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }
}

class TurnOrderedPrinter implements Runnable {
    final SharedThreadCounter counter;
    private final char ch;
    private final int myTurn; // Thread's assigned turn

    public TurnOrderedPrinter(char ch, int myTurn, SharedThreadCounter counter) {
        this.ch = ch;
        this.myTurn = myTurn;
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (counter) {
            while (true) {
                try {
                    if (counter.getTurnCounter() == myTurn) {

                        for (int i = 0; i < 60; i++) {
                            System.out.print(ch);
                        }
                        System.out.println();
                        counter.setTurnCounter((myTurn + 1) % 4); // Move to the next thread, becomes 0 via modulus if divided by 4
                        counter.notifyAll(); // Wake up all threads, which is now necessary since we use more threads
                        counter.wait(); // Wait until it's this thread's turn again
                    } else {
                        counter.wait(); // If it is not the thread turn yet, simply wait
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


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
        /*
        PrinterCounter charCounter = new PrinterCounter();
        Thread t3 = new Thread(new PrinterThread('*', charCounter));
        Thread t4 = new Thread(new PrinterThread('#', charCounter));
        t3.start();
        t4.start();
        */

        // Opgave 3 Selvstudie med flere tråde samt kontrol af threads kørselsorden
        SharedThreadCounter counter = new SharedThreadCounter();
        Thread t5 = new Thread(new TurnOrderedPrinter('*', 0, counter));
        Thread t6 = new Thread(new TurnOrderedPrinter('#', 1, counter));
        Thread t7 = new Thread(new TurnOrderedPrinter('%', 2, counter));
        Thread t8 = new Thread(new TurnOrderedPrinter('¤', 3, counter));

        t5.start();
        t6.start();
        t7.start();
        t8.start();


    }// main END

}// SynchroOpg END
