// Justin Marucci
// 08-29-25
// Assignment 3


import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    // Method to remove duplicates from an ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random rand = new Random();

        // Fill the list with 50 random integers from 1 to 20
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1);
        }

        // Remove duplicates
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // Print results
        System.out.println("Original List (with duplicates):");
        System.out.println(originalList);

        System.out.println("\nNew List (without duplicates):");
        System.out.println(noDuplicatesList);
    }
}
