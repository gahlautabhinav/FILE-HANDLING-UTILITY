// File Handling Utility

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlingUtility {
    /**
     * Method to write text to a file.
     * 
     * @param filePath Path of the file where content will be written.
     * @param content  Content to be written to the file.
    */
    public static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Content written to file: " + filePath);
        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Method to read text from a file.
     * 
     * @param filePath Path of the file to be read.
    */
    public static void readFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Reading from file: " + filePath);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Method to modify text in a file by replacing old content with new content.
     * 
     * @param filePath   Path of the file to be modified.
     * @param oldContent The content to be replaced.
     * @param newContent The new content to be replace the old content.
    */
    public static void modifyFile(String filePath, String oldContent, String newContent) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        try {
            // Read the file content
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append(System.lineSeparator());
                }
            }

            // Replace old content with new content
            String updatedContent = fileContent.toString().replace(oldContent, newContent);

            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(updatedContent);
                System.out.println("File modified: " + filePath);
            }
        }
        catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Scanner sn = new Scanner(System.in)) {
            System.out.print("Enter file name: ");
            String filePath = sn.nextLine();

            while (true) {
                // File Handling Utility Menu
                System.out.println("\nFile Handling Utility");
                System.out.println("1. Write to file");
                System.out.println("2. Read from file");
                System.out.println("3. Modify file");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = sn.nextInt();
                sn.nextLine(); // Consumes newline

                switch (choice) {
                    case 1: {
                        System.out.print("Enter content to write to file: ");
                        String content = sn.nextLine();
                        writeToFile(filePath, content);
                        break;
                    }
                    case 2: {
                        readFromFile(filePath);
                        break;
                    }
                    case 3: {
                        System.out.print("Enter the old content to modify: ");
                        String oldContent = sn.nextLine();
                        System.out.print("Enter the new content: ");
                        String newContent = sn.nextLine();
                        modifyFile(filePath, oldContent, newContent);
                        break;
                    }
                    case 4: {
                        System.out.println("Exiting...");
                        return;
                    }
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}