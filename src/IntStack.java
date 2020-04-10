/*
    Name: Yin Lam Lai
    PID:  A15779757
 */

import java.util.EmptyStackException;

/**
 * TODO
 * @author TODO
 * @since  TODO
 */
public class IntStack {

    private static final int MIN_INIT_CAPACITY = 3;
    private static final int RESIZE_FACTOR = 2;
    private static final double DEF_LOAD_FACTOR = 0.75;
    private static final double MIN_LOAD_FACTOR = 0.6;
    private static final double DEF_SHRINK_FACTOR = 0.2;
    private static final double MAX_SHRINK_FACTOR = 0.4;

    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int initialCap;

    public IntStack(int capacity, double loadF, double shrinkF) {
        if (capacity < MIN_INIT_CAPACITY) {
            throw new IllegalArgumentException("Capacity out of range");
        } else if (MIN_LOAD_FACTOR > loadF) {
            throw new IllegalArgumentException("Load Factor out of range");
        } else if (loadF > 1) {
            throw new IllegalArgumentException("Load Factor out of range");
        } else if (0 > shrinkF) {
            throw new IllegalArgumentException("Shrink Factor out of range");
        } else if (shrinkF > MAX_SHRINK_FACTOR) {
            throw new IllegalArgumentException("Shrink Factor out of range");
        } else {
            this.initialCap = capacity;
            this.nElems = 0;
            this.data = new int[initialCap];
            this.loadFactor = loadF;
            this.shrinkFactor = shrinkF;
        }
    }

    public IntStack(int capacity, double loadF) {
        if (capacity < MIN_INIT_CAPACITY) {
            throw new IllegalArgumentException("Capacity out of range");
        } else if (MIN_LOAD_FACTOR > loadF) {
            throw new IllegalArgumentException("Load Factor out of range");
        } else if (loadF > 1) {
            throw new IllegalArgumentException("Load Factor out of range");
        } else {
            this.initialCap = capacity;
            this.data = new int[initialCap];
            this.nElems = 0;
            this.loadFactor = loadF;
            this.shrinkFactor = DEF_SHRINK_FACTOR ;
        }
    }

    public IntStack(int capacity) {
        if (capacity < MIN_INIT_CAPACITY) {
            throw new IllegalArgumentException("Capacity out of range");
        } else {
            this.initialCap = capacity;
            this.data = new int[this.initialCap];
            this.nElems = 0;
            this.loadFactor = DEF_LOAD_FACTOR;
            this.shrinkFactor = DEF_SHRINK_FACTOR ;
        }
    }

    public boolean isEmpty() {
        if (nElems == 0){
            return true;
        }
        return false;
    }

    public void clear() {
        data = new int[initialCap];
        this.nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        return data.length;
    }

    public int peek() {
        if (nElems == 0) {
            throw new EmptyStackException();
        }
        return data[nElems - 1];
    }

    public void push(int element) {
        double sizeD = this.size();
        double capacityD = this.capacity();
        if (sizeD / capacityD >= loadFactor) {
            int[] temp = new int[RESIZE_FACTOR * capacity()];

            for(int n = 0; n < nElems; n++) {
                temp[n] = data[n];
            }
            data = temp;
        }
        data[nElems] = element;
        nElems++;
    }

    public int pop() {
        if (nElems == 0) {
            throw new EmptyStackException();
        } else {
            int tempop = data[nElems - 1];
            data[nElems - 1] = 0;
            nElems--;
            double sizeD = this.size();
            double capacityD = this.capacity();
            if (sizeD / capacityD <= shrinkFactor) {


                if (this.capacity() / RESIZE_FACTOR < initialCap) {
                    int[] temp = new int[capacity()];
                    for(int n = 0; n < nElems; n++) {
                        temp[n] = data[n];
                    }
                    data = temp;
                } else {
                    int[] temp = new int[capacity() / RESIZE_FACTOR];

                    for(int n = 0; n < nElems; n++) {
                        temp[n] = data[n];
                    }
                    data = temp;
                }
            }

            return tempop;
        }
    }

    public void multiPush(int[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Elements is null!");
        }
        for(int n : elements) {
            this.push(n);
        }


    }

    public int[] multiPop(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            int temp = nElems;

            int[] results;
            if (temp >= amount) {
                results = new int[amount];
                for(int n = 0; n < amount; n++) {
                    results[n] = this.pop();
                }
                return results;
            } else {
                results = new int[temp];
                for(int n = 0; n < temp; n++) {
                    results[n] = this.pop();
                }
                return results;
            }
        }
    }
}
