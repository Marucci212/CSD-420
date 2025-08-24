// Justin Marucci
// Assignment 2
// 08/21/2025

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class WriteDataFile {
    public static void main(String[] args) {
        // Change this to your preferred filename if needed
        final String FILE_NAME = "Marucci_datafile.dat";

        Random rand = new Random();

        // 5 random integers (0–99)
        int[] ints = new int[5];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = rand.nextInt(100);
        }

        // 5 random doubles (0–100, rounded to 2 decimals)
        double[] doubles = new double[5];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Math.round(rand.nextDouble() * 10000) / 100.0;
        }

        // Append to the file and creates it if it doesn't exist
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            out.println("Integers: " + Arrays.toString(ints));
            out.println("Doubles:  " + Arrays.toString(doubles));
            out.println("---"); // separator between runs
            System.out.println("Data written to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
