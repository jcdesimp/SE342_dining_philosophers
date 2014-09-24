package com.SE342_10;

/**
 * File created by Joseph DeSimpliciis on 9/23/14.
 */
public class Philosopher extends Thread {

    private int id ;
    private Fork left_fork ;
    private Fork right_fork ;
    private boolean right_handed ;
    private int nTimes ;
    private long thinkMillis ;
    private long eatMillis ;

    /**
     * Constructor for Philosopher class
     * @param id of philosopher
     * @param left_fork : fork ro left of philosopher
     * @param right_fork : fork to right of philosopher
     * @param right_handed : is philosopher right handed?
     * @param nTimes : number of eat/think cycled
     * @param thinkMillis : max think time
     * @param eatMillis : max eat time
     */
    public Philosopher(int id, Fork left_fork, Fork right_fork, boolean right_handed,
                       int nTimes, long thinkMillis, long eatMillis) {
        this.id = id;
        this.left_fork = left_fork;
        this.right_fork = right_fork;
        this.right_handed = right_handed;
        this.nTimes = nTimes;
        this.thinkMillis = thinkMillis;
        this.eatMillis = eatMillis;
    }

    /**
     * Run method for thread.
     */
    @Override
    public void run() {
        long t ;
        System.out.println("Starting philosopher "+ id);
        for (int i = 0; (i < nTimes || nTimes == 0); i++) {
            t = (long) (Math.random()*thinkMillis) ;
            System.out.println("Philosopher "+ id + " thinks for " + t + " milliseconds.");

            // Think for t millis
            try {
                sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (right_handed) {
                System.out.println("Philosopher " + id + " goes for right fork.") ;
                right_fork.acquire();
                System.out.println("Philosopher " + id + " has right fork.");
                Thread.yield();
            }

            System.out.println("Philosopher " + id + " goes for left fork.") ;
            left_fork.acquire();
            System.out.println("Philosopher " + id + " has left fork.");
            Thread.yield();

            if (!right_handed) {
                System.out.println("Philosopher " + id + " goes for right fork.") ;
                right_fork.acquire();
                System.out.println("Philosopher " + id + " has right fork.");
                Thread.yield();
            }

            t = (long) (Math.random()*eatMillis);
            System.out.println("Philosopher "+ id + " eats for " + t + " milliseconds.");

            // Eat for t millis
            try {
                sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            right_fork.release();
            System.out.println("Philosopher " + id + " releases right fork.");
            left_fork.release();
            System.out.println("Philosopher " + id + " releases left fork.");
            //System.out.println("Philosopher " + id + " finished " + i);
        }
        System.out.println("Philosopher " + id + " is full!");
    }
}
