package org.example;
/*
Producer/Consumer-mønsteret er et designmønster.
Det bruges til at håndtere synkronisering og kommunikation mellem tråde i en flertrådet applikation.

Mønsteret indebærer to roller:
    Producer: Genererer og tilføjer data til en delt ressource, ofte en buffer eller en kø.
    Consumer: Henter og forbruger data fra den delte ressource.

For at koordinere adgangen mellem producer og consumer anvendes typisk synkroniserings-mekanismer såsom wait/notify,
BlockingQueue, eller andre concurrent datastrukturer fra Java’s java.util.concurrent-pakke.
Dette sikrer, at consumer ikke forsøger at hente data fra en tom buffer, og at producer ikke overskriver data, hvis bufferen er fuld.

Mønsteret anvendes ofte i scenarier som logning, jobkøer, database-dispatching og streaming af data.
 */

import java.util.concurrent.*;

public class FlaskeAutomat {
    public static void main(String[] args) {












    }// main END

    public void pauseFor(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}// FlaskeAutomat END
