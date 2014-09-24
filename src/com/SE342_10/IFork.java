package com.SE342_10;

/**
 * File created by Joseph DeSimpliciis on 9/23/14.
 */
public interface IFork {
    /*
     * A philosopher (attempts to) acquire the fork.
     */
    public void acquire() ;

    /*
     * A philosopher releases the fork.
     */
    public void release() ;
}
