package com.SE342_10;

/**
 * File created by Joseph DeSimpliciis on 9/23/14.
 * Represents a fork to be picked up and used by
 * a philosopher
 */
public class Fork implements IFork {

    private boolean allocated = false ;

    /**
     * Try to pick up the fork
     */
    public synchronized void acquire() {
        try {
            while (allocated) {
                    wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        allocated = true ;
        notifyAll();
    }

    /**
     * Put down the fork
     */
    public synchronized void release() {
        try {
            while (!allocated) {
                    wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allocated = false ;
        notifyAll();
    }



}
