package practical11;


import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
import java.util.*;

public class FolderReport {

    public static void main(String[] args) {

        Path folder = Paths.get("mydata");
        Path report = Paths.get("report.txt");

        int[] fileCount = {0};
        long[] totalSize = {0};

        try {

            Files.createDirectories(folder.resolve("documents"));

            Files.write(folder.resolve("file1.txt"),
                    Arrays.asList("Hello Java"));

            Files.write(folder.resolve("file2.txt"),
                    Arrays.asList("File handling"));

            Files.write(folder.resolve("documents/notes.txt"),
                    Arrays.asList("Learning NIO"));

            Files.write(folder.resolve("documents/assignment.txt"),
                    Arrays.asList("Java practical"));

            try (BufferedWriter writer =
                    Files.newBufferedWriter(report)) {

                writer.write("FOLDER TREE REPORT");
                writer.newLine();

                Files.walkFileTree(folder,
                        new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(
                            Path file, BasicFileAttributes attrs)
                            throws IOException {

                        String name = file.getFileName().toString();

                        long size = attrs.size();

                        String modified =
                                attrs.lastModifiedTime().toString();

                        writer.write("File Name: " + name);
                        writer.newLine();

                        writer.write("Size: " + size + " bytes");
                        writer.newLine();

                        writer.write("Modified: " + modified);
                        writer.newLine();

                        writer.newLine();

                        fileCount[0]++;
                        totalSize[0] += size;

                        return FileVisitResult.CONTINUE;
                    }
                });

                writer.write("TOTAL FILES: " + fileCount[0]);
                writer.newLine();

                writer.write("TOTAL SIZE: "
                        + totalSize[0] + " bytes");
                writer.newLine();
            }

            System.out.println("Folder report created successfully.");
            System.out.println("Total Files: " + fileCount[0]);
            System.out.println("Total Size: " + totalSize[0] + " bytes");

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}

