import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code transactionLogger} class is responsible for logging transaction messages to a log file.
 * It maintains a log file where each transaction message is appended.
 * This class uses a static method for logging, making it easily accessible.
 */
public class transactionLogger {
    
    /**
     * The path of the log file where transaction messages will be recorded.
     */
    private static final String LOG_FILE = "TransactionLog.txt";

    /**
     * Appends a transaction message to the log file.
     * 
     * This method opens the log file in append mode, writes the given message,
     * and adds a newline. In case of an I/O error, it prints an error message to the console.
     * 
     * @param message The transaction message to log. It should be a non-null, descriptive string
     *                representing the transaction details.
     */
    public static void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
