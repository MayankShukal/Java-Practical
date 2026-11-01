package practical11;


import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class LogAnalyzer {

    public static void main(String[] args) {

        Path file1 = Paths.get("log1.txt");
        Path file2 = Paths.get("log2.txt");
        Path file3 = Paths.get("log3.txt");

        try {

            Files.write(file1, Arrays.asList(
                    "User logged in",
                    "Error in database",
                    "Connection successful"
            ));

            Files.write(file2, Arrays.asList(
                    "Application started",
                    "Error in network",
                    "User logged out"
            ));

            Files.write(file3, Arrays.asList(
                    "Server running",
                    "Error in server",
                    "Backup completed"
            ));

            Path[] files = {file1, file2, file3};

            String keyword = "Error";

            int totalLines = 0;
            int keywordCount = 0;

            for (Path file : files) {

                System.out.println("\nFile: " + file.getFileName());

                long size = Files.size(file);

                System.out.println("Size: " + size + " bytes");

                List<String> lines = Files.readAllLines(file);

                for (String line : lines) {

                    totalLines++;

                    if (line.contains(keyword)) {
                        keywordCount++;
                    }
                }
            }

            System.out.println("\nLog Analysis Report");

            System.out.println("Total Lines: " + totalLines);

            System.out.println("Lines containing '"
                    + keyword + "': " + keywordCount);

        } catch (IOException e) {

            System.out.println("File Error: " + e.getMessage());
        }
    }
}

