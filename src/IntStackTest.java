/*
    Name: Yin Lam Lai
    PID:  A15779757
 */

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for IntStack class
 * @author Yin Lam lai
 * @since  4/4/2020
 */

public class IntStackTest {
    IntStack test1;
    IntStack test2;
    IntStack test3;

    public IntStackTest() {
    }

    @Before
    public void setup() throws Exception {
        test1 = new IntStack(4, 0.7, 0.25);
        test2 = new IntStack(4, 0.7);
        test3 = new IntStack(4);
    }

    @Test
    public void isEmpty() {
        assertTrue(test1.isEmpty());
        test2.push(1);
        assertFalse(test2.isEmpty());
        assertTrue(test3.isEmpty());
    }

    @Test
    public void clear() {
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        test1.multiPush(pushed);
        test1.clear();
        assertTrue(test1.isEmpty());
        test2.push(1);
        test2.clear();
        assertTrue(test2.isEmpty());
        test3.clear();
        assertTrue(test2.isEmpty());
    }

    @Test
    public void size() {
        test1.push(1);
        test1.push(1);
        test1.push(1);
        assertEquals(3L, (long)test1.size());
        assertEquals(0L, (long)test2.size());
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        test3.push(1);
        assertEquals(9L, (long)test3.size());
    }

    @Test
    public void capacity() {
        assertEquals(4L, (long)test1.capacity());
        test2.push(1);
        test2.push(1);
        test2.push(1);
        test2.push(1);
        assertEquals(8L, (long)test2.capacity());
        test3.push(1);
        assertEquals(4L, (long)test3.capacity());
    }

    @Test
    public void peek() {
        try {
            assertEquals(4, (long)test1.peek());
        } catch (EmptyStackException var2) {
            System.out.println("Stack is empty!");
        }

        test2.push(1);
        test2.push(2);
        test2.push(3);
        test2.push(4);
        assertEquals(4, test2.peek());
        test3.push(1);
        assertEquals(1, test3.peek());
    }

    @Test
    public void push() {
        test1.push(1);
        assertEquals(1, test1.peek());
        test2.push(1);
        test2.push(2);
        test2.push(3);
        test2.push(4);
        assertEquals(4, test2.peek());
        test3.push(-3);
        assertEquals(-3, test3.peek());
    }

    @Test
    public void pop() {
        try {
            test1.pop();
        } catch (EmptyStackException var2) {
            System.out.println("Stack is empty!");
        }

        test2.push(1);
        test2.pop();
        assertTrue(test2.isEmpty());
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        test3.multiPush(pushed);
        test3.pop();
        test3.pop();
        test3.pop();
        test3.pop();
        test3.pop();
        assertTrue(test3.isEmpty());
    }

    @Test
    public void multiPush() {
        int[] pushed1 = new int[]{1, 2, 3, 4, 5};
        int[] pushed2 = null;
        test1.multiPush(pushed1);
        assertEquals(5, test1.peek());

        try {
            test2.multiPush(pushed2);
        } catch (IllegalArgumentException var5) {
            System.out.println(var5.getMessage());
        }

        test3.multiPush(pushed1);
        test3.multiPush(pushed1);
        assertEquals(5, test3.peek());
    }

    @Test
    public void multiPop() {
        try {
            test1.multiPop(-1);
        } catch (IllegalArgumentException var3) {
            System.out.println("Number is negative!");
        }

        test2.push(1);
        test2.push(2);
        test2.push(3);
        test2.push(4);
        test2.push(5);
        int[] expected = new int[]{5, 4, 3, 2, 1};
        assertArrayEquals(expected, test2.multiPop(5));

        test3.push(1);
        test3.push(2);
        test3.push(3);
        int[] expected1 = new int[]{3, 2, 1};
        assertArrayEquals(expected1, test3.multiPop(6));
    }
}