package org.example;

// Classes for opg1
class ArrayReaderCounter {
    private int[] customOrder = new int[]{2, 1, 4, 3};
    private int arrayPointer = 0;

    public int getArrayPointer() {
        return arrayPointer;
    }

    public void setArrayPointer(int pointer) {
        this.arrayPointer = pointer;
    }

    public int getCustomArrayValue() {
        return customOrder[arrayPointer];
    }

    public int getLength() {
        return customOrder.length;
    }

}

class ArrayOrderedPrinter implements Runnable {
    final ArrayReaderCounter arrayOrder;

    private final int intToPrint;
    private final int myTurn; // Thread's assigned turn

    public ArrayOrderedPrinter(ArrayReaderCounter arrayOrder, int intToPrint, int myTurn) {
        this.arrayOrder = arrayOrder;
        this.intToPrint = intToPrint;
        this.myTurn = myTurn;
    }

    @Override
    public void run() {
        synchronized (arrayOrder) {
            while (true) {
                try {
                    if (arrayOrder.getCustomArrayValue() == myTurn) {
                        System.out.println(intToPrint);
                        arrayOrder.setArrayPointer((arrayOrder.getArrayPointer() + 1) % arrayOrder.getLength());

                        Thread.sleep(3000);

                        arrayOrder.notifyAll();
                        arrayOrder.wait();

                    } else {
                        arrayOrder.wait();// If it is not the thread turn yet, simply wait
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ThreadCreator{

}

// Classes for opg2

public class TechOpg {
    public static void main(String[] args) {
        // Opg 1: Lav et program, der har 4 tråde, ved hjælp af interfacet Runnable. Hver tråd har til opgave at udskrive et specifikt tal. Tråd 1 skal printe tallet ‘8’, tråd 2 tallet ‘1’, tråd 3 tallet ‘2’ og tråd 4 tallet ‘5’. Skriv skriv en
        //metode, der eksekverer trådene i sådan en rækkefølge, så der udlæses hvor mange kilometre der går på 1000 sømil (international nautical mile).
        /*
        ArrayReaderCounter arrayOrder = new ArrayReaderCounter();
        Thread t1 = new Thread(new ArrayOrderedPrinter(arrayOrder, 8, 1));
        Thread t2 = new Thread(new ArrayOrderedPrinter(arrayOrder, 1, 2));
        Thread t3 = new Thread(new ArrayOrderedPrinter(arrayOrder, 2, 3));
        Thread t4 = new Thread(new ArrayOrderedPrinter(arrayOrder, 5, 4));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        */

        // Opg 2: Skriv et program, der tager en positiv integer n som input, and opretter n tråde der udlæser deres eget navn. Output kunne se sådan ud: Hallo, Jeg hedder Thread #1
        // Skipped

        // Opg 3


        // Opg 4


        // Opg 5

    }
}
