package org.example;

// Class simulating a banking account
class BankAccount {
    // The account starts with a private value of 100.
    // it is important to note that private makes it so that only the bank class is able to change this value
    private int balance = 400;


    // withdraw is just a method to remove a value amount from the account
    // If run without the synchronized keyword, the account will sometimes reach into the negatives.
    // try removing the synchronized keyword, and watch what happens.
    public synchronized void withdraw(int amount) {
        // Tjekker om der er nok penge på kontoen til at tage "amount" ud.
        if (balance >= amount) {
            // Says which thread that is taking money out of the account.
            System.out.println(Thread.currentThread().getName() + " hæver " + amount);
            balance -= amount; // Withdraws 'Amount'.
            System.out.println("Ny saldo: " + balance); // Show account saldo after transaction.
        } else {
            // Sout's an error message if there is not enough money on the account.
            System.out.println("Ikke nok penge på kontoen!");
        }
    }
}


public class RaceConditionExample {
    public static void main(String[] args) {
        // Creates a banking account, with x Balance amount
        BankAccount account = new BankAccount();

        // Opretter en opgave, som trådene skal udføre. Det er som en to-do-liste.
        Runnable withdrawTask = () -> {
            // Gentag 5 gange: Tag 30 kr ud af kontoen.
            for (int i = 0; i < 5; i++) {
                // Kalder withdraw-metoden for at hæve 30 kr.
                account.withdraw(30);
                try {
                    // Vent 500 millisekunder (0,5 sekund) før næste hævning
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // Hvis noget afbryder pausen, viser vi en fejl (det sker sjældent her).
                    e.printStackTrace();
                }
            }
        };

        // Creates threads with the same task: (withdrawTask).
        Thread t1 = new Thread(withdrawTask, "Thread: 1");
        Thread t2 = new Thread(withdrawTask, "Thread: 2");
        Thread t3 = new Thread(withdrawTask, "Thread: 3");
        Thread t4 = new Thread(withdrawTask, "Thread: 4");

        // Starts multiple threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Bemærk: Fordi trådene kører samtidig, kan de begge tjekke saldoen på samme tid.
        // Det kan føre til fejl, hvor de tager flere penge ud, end der er på kontoen!
        // Dette kaldes en "race condition" - som et løb, hvor trådene "kæmper" om at komme først.
    }
}