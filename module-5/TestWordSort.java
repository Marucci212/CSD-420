// Justin Marucci
// Assignment 5
// 09/05/2025

//  This program reads words from a text file and displays all non-duplicate words in ascending order and then in descending order.


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestWordSort {

    public static void main(String[] args) {
        // File reference (no command-line args needed)
        File file = new File("collection_of_words.txt");

        // Use a TreeSet to automatically sort and remove duplicates
        Set<String> words = new TreeSet<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", ""); // normalize
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
            return;
        }

        // Display words in ascending order
        System.out.println("Words in ascending order:");
        for (String word : words) {
            System.out.println(word);
        }

        // Display words in descending order
        System.out.println("\nWords in descending order:");
        List<String> descendingList = new ArrayList<>(words);
        Collections.reverse(descendingList);
        for (String word : descendingList) {
            System.out.println(word);
        }

        // Test code: check correctness
        testCode(words, descendingList);
    }

    // Simple test method to verify results
    private static void testCode(Set<String> ascending, List<String> descending) {
        System.out.println("\n--- Running Tests ---");

        // Test 1: No duplicates in ascending
        boolean noDuplicates = (ascending.size() == new HashSet<>(ascending).size());
        System.out.println("Test 1 - No Duplicates: " + (noDuplicates ? "PASSED" : "FAILED"));

        // Test 2: Descending list is reverse of ascending
        List<String> ascList = new ArrayList<>(ascending);
        List<String> reversedAsc = new ArrayList<>(ascList);
        Collections.reverse(reversedAsc);
        boolean isCorrectReverse = reversedAsc.equals(descending);
        System.out.println("Test 2 - Correct Descending Order: " + (isCorrectReverse ? "PASSED" : "FAILED"));

        // Test 3: At least one word was read
        System.out.println("Test 3 - File not empty: " + (!ascending.isEmpty() ? "PASSED" : "FAILED"));
    }
}
