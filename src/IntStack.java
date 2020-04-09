/*
    Name: TODO
    PID:  TODO
 */

import java.util.EmptyStackException;

/**
 * TODO
 * @author TODO
 * @since  TODO
 */
public class IntStack {

    /* static constants, feel free to add more if you need */
    private static final int    MIN_INIT_CAPACITY = 3;
    private static final int    RESIZE_FACTOR     = 2;
    private static final double DEF_LOAD_FACTOR   = 0.75;
    private static final double MIN_LOAD_FACTOR   = 0.6;
    private static final double DEF_SHRINK_FACTOR = 0.2;
    private static final double MAX_SHRINK_FACTOR = 0.4;

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;

    public IntStack(int capacity, double loadF, double shrinkF) {
        /* TODO */
    }

    public IntStack(int capacity, double loadF) {
        /* TODO */
    }

    public IntStack(int capacity) {
        /* TODO */
    }

    public boolean isEmpty() {
        /* TODO */
        return false;
    }

    public void clear() {
        /* TODO */
    }

    public int size() {
        /* TODO */
        return 0;
    }

    public int capacity() {
        /* TODO */
        return 0;
    }

    public int peek() {
        /* TODO */
        return 0;
    }

    public void push(int element) {
        /* TODO */
    }

    public int pop() {
        /* TODO */
        return 0;
    }

    public void multiPush(int[] elements) {
        /* TODO */
    }

    public int[] multiPop(int amount) {
        /* TODO */
        return null;
    }
}
