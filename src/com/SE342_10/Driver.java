package com.SE342_10;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

    /**
     * Main method
     * @param args given at commandline
     */
    public static void main(String[] args) {

        // Default Args
        boolean l = false ;
        int np    = 4 ;
        int nt    = 10 ;
        int tm    = 0 ;
        int em    = 0 ;

        ArrayList<String> arg_data = new ArrayList<String>(Arrays.asList(args)) ;

        if (arg_data.contains("-l")) {
            l = true ;
            arg_data.remove(0) ;
        }


        /*
         * *********************
         *  Process Arguments
         * *********************
         */
        for (int i = 0; i < 4; i++) {

            if (i == arg_data.size()){
                break ;
            }

            int curr_arg;
            try {

                curr_arg = Integer.parseInt(arg_data.get(i)) ;

            } catch (NumberFormatException e) {
                System.out.println("Arguments should be numbers.");
                return;
            }
                switch (i) {

                    case 0:
                        np = curr_arg ;
                        break;
                    case 1:
                        nt = curr_arg ;
                    case 2:
                        tm = curr_arg ;
                    case 3:
                        em = curr_arg ;
                }


        }

        /*
         * ****************************
         *  Set up Philosophers/Forks
         * ****************************
         */

        ArrayList<Fork> forks = new ArrayList<Fork>() ;
        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>() ;

        // Create Forks
        for (int i = 0; i < np; i++) {
            forks.add(new Fork());
        }


        // Create the Philosophers
        for (int i = 0; i < np; i++) {

            boolean right_handed = true ;

            if (l && (i%2 == 0)) {
                right_handed = false;
            }

            philosophers.add(
                    new Philosopher(i, forks.get((np+i-1) % np), forks.get(i), right_handed, nt, tm, em)
            ) ;
        }


        /*
         * ******************************
         *   Start Philosopher Threads
         * ******************************
         */
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }


    }
}
