// Justin Marucci
// Assignment 4
// 08-28-2025

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTraversalBenchmark {

    // Make a LinkedList with numbers from 0 to n-1
    private static LinkedList<Integer> buildList(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    // Go through the list using an Iterator
    private static long traverseWithIterator(LinkedList<Integer> list) {
        long sum = 0;
        long start = System.nanoTime();

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sum += it.next();
        }

        long end = System.nanoTime();

        // Check result is correct
        long expected = expectedSum(list.size());
        if (sum != expected) {
            throw new IllegalStateException("Iterator sum wrong. Expected " + expected + " but got " + sum);
        }

        return end - start;
    }

    // Go through the list using get(index)
    private static long traverseWithGet(LinkedList<Integer> list) {
        long sum = 0;
        long start = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);  // Slow on LinkedList
        }

        long end = System.nanoTime();

        // Check result is correct
        long expected = expectedSum(list.size());
        if (sum != expected) {
            throw new IllegalStateException("get(index) sum wrong. Expected " + expected + " but got " + sum);
        }

        return end - start;
    }

    // Formula for sum of numbers 0 to n-1
    private static long expectedSum(int n) {
        return (long) n * (n - 1L) / 2L;
    }

    // Small test to be sure both ways work
    private static void selfTest() {
        LinkedList<Integer> tiny = buildList(10);
        long itTime = traverseWithIterator(tiny);
        long getTime = traverseWithGet(tiny);
        assert expectedSum(10) == 45 : "Check failed";
        System.out.println("[Test] Iterator ns=" + itTime + " | get(index) ns=" + getTime);
        System.out.println("[Test] Passed basic checks.");
    }

    // Run one test with n numbers
    private static void runScenario(int n) {
        System.out.println("\n=== Test with n = " + n + " ===");
        LinkedList<Integer> list = buildList(n);

        long iterTime = traverseWithIterator(list);
        long getTime  = traverseWithGet(list);

        double iterMs = iterTime / 1_000_000.0;
        double getMs  = getTime  / 1_000_000.0;

        System.out.printf("Iterator time:   %.3f ms%n", iterMs);
        System.out.printf("get(index) time: %.3f ms%n", getMs);
        System.out.printf("get(index) is %.2f× slower%n", (getMs / iterMs));
    }

    public static void main(String[] args) {
        // First, quick check
        selfTest();

        // Run with 50,000 and 500,000
        runScenario(50_000);
        runScenario(500_000);

        
    }
}
