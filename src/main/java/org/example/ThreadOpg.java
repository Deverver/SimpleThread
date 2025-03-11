package org.example;

import java.util.Random;
import java.util.Scanner;

// Lav et Java-program med én tråd (ikke Main-tråden), som 5 gange udskriver følgende linje på konsollen:
class Opg1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Java-trådning er nemt!");
            try {
                Thread.sleep(1000); // Pauses thread for 1 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Opg1() {
    }
}// Opg1 END

// Udvid programmet med en ekstra tråd, således at den ene tråd udskriver 5 gange:
class Opg2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Også med flere tråde...");
            try {
                Thread.sleep(1000); // Pauses thread for 1 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Opg2() {
    }
}// Opg2 END

// (ikke Main-tråden), som hvert andet sekund genererer en tilfældig temperatur mellem -20 og 120 C. Temperaturen udskrives på konsollen.
class Opg3 implements Runnable {
    @Override
    public void run() {
        int alarmCounter = 0;


        for (int i = 0; i < 100; i++) {
            while (alarmCounter < 3) {
                Random randomNumber = new Random();
                int minValue = -20;
                int maxValue = 120;
                int result = randomNumber.nextInt(maxValue - minValue) + minValue;

                if (result < 0 || result > 100) {
                    alarmCounter++;
                    System.out.println("Alarm! Temperatur: " + result + " is out of acceptable range!\nAdding +1 to Alarm Counter");
                }
                System.out.println("Temperatur is : " + result);
                try {
                    Thread.sleep(2000); // Pauses thread for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("Thread 3 Message: Thread has terminated");
    }

    public Opg3() {
    }
}// Opg3 END

// Input/output tråde. Metoder der håndterer bruger input er ofte ekskluderet i en separat tråd så input er muligt, mens data beregninger eller output finder sted.
class Opg4 implements Runnable {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        PrinterThread printerThread = new PrinterThread();
        Thread printer = new Thread(printerThread);
        printer.start();

        Thread reader = new Thread(new ReaderThread(scanner, printerThread));
        reader.start();
    }

    public Opg4() {
    }

    class PrinterThread implements Runnable {
        private char ch = '*';

        @Override
        public void run() {
            System.out.println("Printer: " + this.getClass().getName() + " is printing: ");
            while (true) {
                System.out.print(ch);
                try {
                    Thread.sleep(500); // Pauses thread for 0.5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public PrinterThread() {
        }

        public void setCh(char ch) {
            this.ch = ch;
        }

        public char getCh() {
            return ch;
        }
    }// printerThread END

    class ReaderThread implements Runnable {
        Scanner scanner;
        PrinterThread printerThread;

        public ReaderThread(Scanner scanner, PrinterThread printerThread) {
            this.scanner = scanner;
            this.printerThread = printerThread;
        }

        @Override
        public void run() {
            System.out.println("Reader: " + this.getClass().getName() + " is reading: ");
            while (true) {
                System.out.println("Enter a Char");
                char ch = scanner.next().charAt(0);
                printerThread.setCh(ch);
            }
        }
    }// readerThread END

}// Opg4 END


public class ThreadOpg {
    public static void main(String[] args) {

        //Thread t1 = new Thread(new Opg1());
        //t1.start();

        //Thread t2 = new Thread(new Opg2());
        //t2.start();

        /*
        Thread t3 = new Thread(new Opg3());
        t3.start();
        while (t3.isAlive()) {
            try {
                System.out.println("Thread 3: Opg3 is alive");
                Thread.sleep(5000); // Checks every 5 seconds to see if thread t3 is alive
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main Message: Thread 3 has terminated");
        */

        Thread t4 = new Thread(new Opg4());
        t4.start();

    }// main END

}// ThreadOpg END

