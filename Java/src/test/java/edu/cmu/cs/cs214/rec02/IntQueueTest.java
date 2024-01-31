package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
       mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(10);
        // TODO: write your own unit test
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        // TODO: write your own unit test
        assertNull(mQueue.peek());

    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        mQueue.enqueue(10);  // First element
        mQueue.enqueue(20);  // Second element

        Integer headElement = mQueue.peek();

        assertEquals(Integer.valueOf(10), headElement);

        assertEquals( 2, mQueue.size());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        // TODO: write your own unit test
        if (mQueue.isEmpty()){
            assertNull(mQueue.dequeue());
        }
        else{
            mQueue.enqueue(10);
            mQueue.enqueue(20);
            assertEquals(Integer.valueOf(10), mQueue.dequeue()); // Verify the first element is dequeued
            assertEquals(Integer.valueOf(20), mQueue.dequeue()); // Verify the next element is dequeued
            assertTrue(mQueue.isEmpty()); // Verify the queue is empty after dequeuing all elements
        }
    }

    @Test
    public void testClear (){
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testEnqueDequeueWithCapacityIncrease(){
        for (int i = 0; i <15; i ++){
            mQueue.enqueue(i);
        }
        // for (int i = 0; i <15; i ++){
        //     assertEquals(Integer.valueOf(i), mQueue.dequeue());
        // }
        // assertTrue(mQueue.isEmpty());
        assertEquals(mQueue.size(),15);
    }

    @Test
    public void testEnsureCapacityWithHeadShift() {
        // Step 1: Enqueue elements to fill the queue to its initial capacity
        for (int i = 0; i < 10; i++) {
            mQueue.enqueue(i);
        }

        // Step 2: Dequeue a few elements to move the head forward
        mQueue.dequeue(); // Dequeue one element, for example
        mQueue.dequeue(); // Dequeue another element

        // Step 3: Enqueue more elements to trigger ensureCapacity with head > 0
        for (int i = 0; i < 5; i++) { // Enqueue additional elements
            mQueue.enqueue(10 + i);
        }

        // Verify the queue's behavior to ensure it's correct after capacity increase
        // The first element should now be the third one enqueued since two were dequeued
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }




}
