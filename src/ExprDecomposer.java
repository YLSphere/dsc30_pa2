/*
    Name: Yin Lam Lai
    PID:  A15779757
 */


import sun.security.util.Length;

import java.util.EmptyStackException;

/**
 * Decomposes normal algorithm to special format
 * @author Yin Lam Lai
 * @since  4/10/2020
 */
public class ExprDecomposer {


    /**
     * Decomposes normal algorithm to special format
     * @param expr string expression to decompose
     */
    public char[] decompose(String expr) {

        char[] exprArray = expr.replaceAll("\\s+", "").toCharArray();
        char[] exprArrayNoBrackets = expr.replace(")", "").replace("(", "")
                .replaceAll("\\s+", "").toCharArray();
        int length = expr.length();


        CharStack charStack = new CharStack(length);
        int resultCountMatch = 0;
        int digitOrOperatorCount = 0;

        char[] result = new char[exprArrayNoBrackets.length];



        for (int n = 0; n < exprArray.length; n++) {
            if (exprArray[n] == ')') {
                if (charStack.isEmpty()) {
                    continue;
                } else if (n != exprArray.length - 1){
                    if (isOperator(exprArray[n + 1])) {
                        continue;
                    } else {
                        result[resultCountMatch] = charStack.pop();
                        resultCountMatch++;
                    }
                } else if (n == exprArray.length - 1) {
                    result[resultCountMatch] = charStack.pop();
                    resultCountMatch++;
                }
            }


            if (isDigit(exprArray[n])) {
                result[resultCountMatch] = exprArray[n];
                resultCountMatch++;
                digitOrOperatorCount++;
                if (resultCountMatch == 1) {
                    continue;
                } else if (isOperator(exprArray[n - 1])) {
                    result[resultCountMatch] = charStack.pop();
                    resultCountMatch++;
                }

            } else if (isOperator(exprArray[n])) {
                charStack.push(exprArrayNoBrackets[digitOrOperatorCount]);
                digitOrOperatorCount++;
            }
        }
        return result;
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents a digit
     * @param token to check
     * @return boolean true if token is a digit, false otherwise
     */
    private boolean isDigit(char token) {
        return (token >= '0') && (token <= '9');
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents an operator
     * @param token to check
     * @return boolean true if token is an operator, false otherwise
     */
    private boolean isOperator(char token) {
        return (token == '+') || (token == '-') || (token == '*') || (token == '/');
    }

    /**
     * Inner class CharStack.
     * Note: You can remove methods and variables that you will not use for
     * this question, but you must keep both push() and pop() methods and they
     * should function properly.
     */
    protected class CharStack {

        private static final int MIN_INIT_CAPACITY = 3;
        private static final int RESIZE_FACTOR = 2;
        private static final double DEF_LOAD_FACTOR = 0.75;

        private static final double DEF_SHRINK_FACTOR = 0.2;


        private char[] data;
        private int nElems;
        private double loadFactor;
        private double shrinkFactor;
        private int initialCap;


        /**
         * Creates an CharStack with a given capacity
         * @param capacity Capacity of intStack
         * @throws IllegalArgumentException if capacity is out of range
         */
        public CharStack(int capacity) {
            if (capacity < MIN_INIT_CAPACITY) {
                throw new IllegalArgumentException("Capacity out of range");
            } else {
                this.initialCap = capacity;
                this.data = new char[initialCap];
                this.nElems = 0;
                this.loadFactor = DEF_LOAD_FACTOR;
                this.shrinkFactor = DEF_SHRINK_FACTOR ;
            }
        }

        /**
         * Checks if the given Charstack is empty if yes return true, else, false
         */
        public boolean isEmpty() {
            if (nElems == 0){
                return true;
            }
            return false;
        }

        /**
         * returns the amount of stored items in the CharStack
         */
        public int size() {
            return nElems;
        }

        /**
         * Returns the current capacity of the CharStack
         */
        public int capacity() {
            return data.length;
        }

        /**
         * Pushes an element into the CharStack, if it exceeds the load factor, capacity is doubled
         * @param element element to be pushed
         */
        public void push(char element) {
            double sizeD = this.size();
            double capacityD = this.capacity();
            if (sizeD / capacityD >= loadFactor) {
                char[] temp = new char[RESIZE_FACTOR * capacity()];

                for(int n = 0; n < nElems; n++) {
                    temp[n] = data[n];
                }
                data = temp;
            }
            data[nElems] = element;
            nElems++;
        }

        /**
         * pops the first element in the CharStack if the shrink factor is exceeded after, reduce capacity by half
         * @throws EmptyStackException if stack is empty
         */
        public char pop() {
            if (nElems == 0) {
                throw new EmptyStackException();
            } else {
                char tempop = data[nElems - 1];
                data[nElems - 1] = 0;
                nElems--;
                double sizeD = this.size();
                double capacityD = this.capacity();
                if (sizeD / capacityD <= shrinkFactor) {


                    if (this.capacity() / RESIZE_FACTOR < initialCap) {
                        char[] temp = new char[capacity()];
                        for(int n = 0; n < nElems; n++) {
                            temp[n] = data[n];
                        }
                        data = temp;
                    } else {
                        char[] temp = new char[capacity() / RESIZE_FACTOR];

                        for(int n = 0; n < nElems; n++) {
                            temp[n] = data[n];
                        }
                        data = temp;
                    }
                }
                return tempop;
            }

        }
    }
}
