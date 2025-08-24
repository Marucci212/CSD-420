// Justin Marucci 
// Assignment 2
// 08/22/2025

import java.io.BufferedReader;      // For efficient reading of text from a character input stream
import java.io.FileNotFoundException; // Exception for when the file cannot be found
import java.io.FileReader;          // Reads text files in the default encoding
import java.io.IOException;         // Exception for general input/output errors

public class ReadDataFile {
    public static void main(String[] args) {
        // Name of the file to read. This should match the file created by WriteDataFile.
        final String FILE_NAME = "Marucci_datafile.dat";

        // Try-with-resources ensures the BufferedReader is automatically closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("Contents of " + FILE_NAME + ":");

            String line; // Variable to hold each line of text read from the file

            // Read each line of the file until reaching the end (null)
            while ((line = br.readLine()) != null) {
                // Print the line to the console
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // This block runs if the file does not exist
            System.out.println("File not found. Run WriteDataFile first to create it.");
        } catch (IOException e) {
            // This block runs if there is a problem reading the file 
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

