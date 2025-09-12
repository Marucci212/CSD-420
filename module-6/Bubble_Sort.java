// Justin Marucci
// Module 6 Programming Assignment
// Date: 09/09/2025
//
// This program demonstrates two generic bubble sort methods:
// 1. Using the Comparable interface.
// 2. Using the Comparator interface.
// The program includes test cases to verify correctness.

import java.util.Comparator;

public class Bubble_Sort {

    // Generic bubble sort method using Comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    // Swap elements
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps occurred, array is sorted
            if (!swapped) break;
        }
    }

    // Generic bubble sort method using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - i - 1; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    // Swap elements
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps occurred, array is sorted
            if (!swapped) break;
        }
    }

    // Test code to demonstrate both bubble sort methods
    public static void main(String[] args) {
        // Test Comparable bubble sort (Strings)
        String[] names = {"Justin", "Madison", "Alex", "Taylor", "Chris"};
        System.out.println("Before (Comparable): ");
        for (String name : names) System.out.print(name + " ");
        
        bubbleSort(names);
        System.out.println("\nAfter (Comparable): ");
        for (String name : names) System.out.print(name + " ");
        
        System.out.println("\n");

        // Test Comparator bubble sort (Integers descending order)
        Integer[] numbers = {42, 17, 8, 99, 23};
        System.out.println("Before (Comparator): ");
        for (Integer num : numbers) System.out.print(num + " ");

        bubbleSort(numbers, (a, b) -> b - a); // Sort in descending order
        System.out.println("\nAfter (Comparator - Descending): ");
        for (Integer num : numbers) System.out.print(num + " ");
    }
}
