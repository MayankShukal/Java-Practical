package practical8;


import java.io.IOException;

class FileConnection implements AutoCloseable {

    FileConnection() {
        System.out.println("File connection opened.");
    }

    public void readFile() {
        System.out.println("Reading file...");

        throw new IllegalArgumentException(
                "File contains invalid data!");
    }

    @Override
    public void close() throws IOException {
        System.out.println("File connection closed.");
        throw new IOException("Error while closing connection!");
    }
}

public class AutoCloseDemo {

    public static void main(String[] args) {

        try (FileConnection connection = new FileConnection()) {

            connection.readFile();

        } catch (Exception e) {

            System.out.println("\nOriginal Error: "
                    + e.getMessage());

            System.out.println("Exception Type: "
                    + e.getClass().getSimpleName());

            System.out.println("Suppressed Exceptions: "
                    + e.getSuppressed().length);

            for (Throwable t : e.getSuppressed()) {
                System.out.println("Close Error: " + t.getMessage());
            }
        }

        System.out.println("\nProgram completed.");
    }
}
