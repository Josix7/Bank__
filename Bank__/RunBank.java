import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the banking system simulation.
 * Provides a menu for various banking operations such as balance inquiry,
 * deposit, withdrawal, transfer, and payment.
 */
public class RunBank {

    // ArrayList to store customers
    static ArrayList<Customer> customerList = new ArrayList<>();

    /**
     * Main method to run the banking system.
     * It initializes the customer data from a CSV file and presents a menu
     * for the user to perform various banking operations.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Parse the CSV file and create customer and account objects
        LoadCSV("Bank_users.csv");

        Scanner scanner = new Scanner(System.in);

        // Main loop to present the menu until the user chooses to exit
        while (true) {
            // Print the menu
            System.out.println("Welcome to the Bank System");
            System.out.println("Choose an option:");
            System.out.println("1. Inquire about a balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. Pay someone");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Handle user's choice
            switch (choice) {
                case 1:
                    inquireBalance(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    transferMoney(scanner);
                    break;
                case 5:
                    paySomeone(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    saveToCSV("Updated_Bank_users.csv");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Parses a CSV file and creates Customer and Account objects.
     * Populates the customer list with created objects.
     *
     * @param filePath The path to the CSV file.
     */
    public static void LoadCSV(String filePath) {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Handle quoted fields (addresses with commas)
                String[] values = parseCSVLine(line);

                // Extract customer data from the CSV fields
                String firstName = values[1].trim();
                String lastName = values[2].trim();
                String birthDate = values[3].trim();
                String address = values[4].trim();
                String phoneNumber = values[5].trim();
                int identificationNumber = parseInt(values[0].trim(), 0);

                // Convert account data from the CSV fields
                int checkingAccountNumber = parseInt(values[6].trim(), 0);
                double checkingStartingBalance = parseDouble(values[7].trim(), 0.0);
                int savingsAccountNumber = parseInt(values[8].trim(), 0);
                double savingsStartingBalance = parseDouble(values[9].trim(), 0.0);
                int creditAccountNumber = parseInt(values[10].trim(), 0);
                double creditMax = parseDouble(values[11].trim(), 0.0);
                double creditStartingBalance = parseDouble(values[12].trim(), 0.0);

                // Create the customer object
                Customer customer = new Customer(identificationNumber, firstName, lastName, birthDate, address, phoneNumber);

                // Create account objects for the customer
                Account checking = new Checking(checkingAccountNumber, checkingStartingBalance);
                Account savings = new Saving(savingsAccountNumber, savingsStartingBalance);
                Account credit = new Credit(creditAccountNumber, creditMax, creditStartingBalance);

                // Add accounts to the customer
                customer.addAccount(checking);
                customer.addAccount(savings);
                customer.addAccount(credit);

                // Add the customer to the list
                customerList.add(customer);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Handles balance inquiry for a customer.
     *
     * @param scanner The scanner object for user input.
     */
    private static void inquireBalance(Scanner scanner) {
        System.out.println("Enter your customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        // Find the customer by ID
        Customer customer = findCustomerById(customerID);
        if (customer != null) {
            System.out.println("Customer found: " + customer.getFirstName() + " " + customer.getLastName());

            // Prompt user to enter account number
            System.out.println("Enter account number to inquire: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            // Find the account by number
            Account account = customer.getAccountByNumber(accountNumber);
            if (account != null) {
                System.out.println(account.getAccountDetails() + "\n");

                String logMessage = customer.getFirstName() + " " + customer.getLastName() 
                + " made a balance inquiry on " + account.getAccountType() 
                + "-" + account.getAccountNumber() + ". " + customer.getFirstName() 
                + " " + customer.getLastName() + "'s Balance for " 
                + account.getAccountType() + "-" + account.getAccountNumber() + ": $" 
                + account.getBalance();
                transactionLogger.log(logMessage);

            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Customer with ID " + customerID + " not found.");
        }
    }

    /**
     * Handles money deposit for a customer.
     *
     * @param scanner The scanner object for user input.
     */
    private static void depositMoney(Scanner scanner) {
        System.out.println("Enter your customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        // Find the customer by ID
        Customer customer = findCustomerById(customerID);
        if (customer != null) {
            System.out.println("Enter account number to deposit: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            // Find the account by number
            Account account = customer.getAccountByNumber(accountNumber);
            if (account != null) {
                System.out.println("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                // Perform deposit operation
                account.deposit(amount);
                System.out.println("Deposit successful. New balance: " + account.getBalance());

                String logMessage = customer.getFirstName() + " " + customer.getLastName() 
                + " deposited $" + amount + " to " + account.getAccountType() + "-" 
                + account.getAccountNumber() + ". " + customer.getFirstName() 
                + " " + customer.getLastName() + "'s New Balance for " 
                + account.getAccountType() + "-" + account.getAccountNumber() + ": $" 
                + account.getBalance();
                transactionLogger.log(logMessage);  



            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Customer with ID " + customerID + " not found.");
        }
    }

    /**
     * Finds a customer by ID in the customer list.
     *
     * @param customerID The ID of the customer.
     * @return The customer object if found, otherwise null.
     */
    private static Customer findCustomerById(int customerID) {
        for (Customer customer : customerList) {
            if (customer.getId() == customerID) {
                return customer;
            }
        }
        return null;  // Customer not found
    }

    /**
     * Parses a string to an integer, returns a default value if parsing fails.
     *
     * @param value The string to parse.
     * @param defaultValue The default value to return in case of error.
     * @return The parsed integer or the default value.
     */
    private static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + value + ". Using default: " + defaultValue);
            return defaultValue;
        }
    }

    /**
     * Parses a string to a double, returns a default value if parsing fails.
     *
     * @param value The string to parse.
     * @param defaultValue The default value to return in case of error.
     * @return The parsed double or the default value.
     */
    private static double parseDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing double: " + value + ". Using default: " + defaultValue);
            return defaultValue;
        }
    }

    /**
     * Parses a CSV line handling commas inside quotes.
     *
     * @param line The CSV line to parse.
     * @return An array of tokens from the CSV line.
     */
    private static String[] parseCSVLine(String line) {
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        String[] tokens = new String[13];  // Adjust the size based on expected fields
        int tokenIndex = 0;

        // Iterate through the characters in the line
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;  // Toggle the inQuotes flag
            } else if (c == ',' && !inQuotes) {
                // If not inside quotes, treat the comma as a field separator
                tokens[tokenIndex++] = sb.toString();
                sb.setLength(0);  // Clear the StringBuilder for the next token
            } else {
                sb.append(c);
            }
        }

        // Add the last token
        tokens[tokenIndex] = sb.toString();
        return tokens;
    }

    /**
     * Handles money withdrawal for a customer.
     *
     * @param scanner The scanner object
         * Handles money withdrawal for a customer.
     *
     * @param scanner The scanner object for user input.
     */
    private static void withdrawMoney(Scanner scanner) {
        System.out.println("Enter your customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();
    
        // Find the customer by ID
        Customer customer = findCustomerById(customerID);
        if (customer != null) {
            System.out.println("Enter account number to withdraw from: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();
    
            // Find the account by number
            Account account = customer.getAccountByNumber(accountNumber);
            if (account != null) {
                System.out.println("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
    
                // Check if withdrawal is possible
                if (amount > account.getBalance()) {
                    System.out.println("Insufficient funds. Withdrawal amount exceeds current balance.");
                } else {
                    // Attempt to withdraw the amount
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful. New balance: " + account.getBalance());
    
                    String logMessage = customer.getFirstName() + " " + customer.getLastName() 
                    + " withdrew $" + amount + " in cash from " + account.getAccountType() 
                    + "-" + account.getAccountNumber() + ". " 
                    + customer.getFirstName() + " " + customer.getLastName() 
                    + "'s Balance for " + account.getAccountType() 
                    + "-" + account.getAccountNumber() + ": $" + account.getBalance();
                    transactionLogger.log(logMessage);
                }
            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Customer with ID " + customerID + " not found.");
        }
    }
    

    /**
     * Handles transferring money between customers' accounts.
     *
     * @param scanner The scanner object for user input.
     */
    private static void transferMoney(Scanner scanner) {
        System.out.println("Enter your customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();
    
        // Find the sender by ID
        Customer sender = findCustomerById(customerID);
        if (sender != null) {
            System.out.println("Enter source account number: ");
            int sourceAccountNumber = scanner.nextInt();
            scanner.nextLine();
    
            // Find the sender's source account
            Account sourceAccount = sender.getAccountByNumber(sourceAccountNumber);
            if (sourceAccount != null) {
                System.out.println("Enter target customer ID: ");
                int targetCustomerID = scanner.nextInt();
                scanner.nextLine();
    
                // Find the receiver by ID
                Customer receiver = findCustomerById(targetCustomerID);
                if (receiver != null) {
                    System.out.println("Enter target account number: ");
                    int targetAccountNumber = scanner.nextInt();
                    scanner.nextLine();
    
                    // Find the receiver's target account
                    Account targetAccount = receiver.getAccountByNumber(targetAccountNumber);
                    if (targetAccount != null) {
                        System.out.println("Enter transfer amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
    
                        // Check if transfer is possible
                        if (amount > sourceAccount.getBalance()) {
                            System.out.println("Insufficient funds. Transfer amount exceeds current balance.");
                        } else {
                            // Attempt to transfer the amount
                            sourceAccount.transfer(targetAccount, amount);
                            System.out.println("Transfer successful. New balance: " + sourceAccount.getBalance());
    
                            String logMessage = sender.getFirstName() + " " + sender.getLastName() 
                            + " transferred $" + amount + " from " + sourceAccount.getAccountType() 
                            + "-" + sourceAccount.getAccountNumber() + " to " 
                            + receiver.getFirstName() + " " + receiver.getLastName() 
                            + "'s " + targetAccount.getAccountType() + "-" + targetAccount.getAccountNumber() + ". "
                            + sender.getFirstName() + " " + sender.getLastName() + "'s New Balance for " 
                            + sourceAccount.getAccountType() + "-" + sourceAccount.getAccountNumber() + ": $" 
                            + sourceAccount.getBalance() + ". " + receiver.getFirstName() 
                            + " " + receiver.getLastName() + "'s New Balance for " 
                            + targetAccount.getAccountType() + "-" + targetAccount.getAccountNumber() + ": $" 
                            + targetAccount.getBalance();
                            transactionLogger.log(logMessage);
                        }
                    } else {
                        System.out.println("Target account not found.");
                    }
                } else {
                    System.out.println("Receiver not found.");
                }
            } else {
                System.out.println("Source account not found.");
            }
        } else {
            System.out.println("Sender not found.");
        }
    }
    

    /**
     * Handles paying someone using the credit account.
     *
     * @param scanner The scanner object for user input.
     */
    private static void paySomeone(Scanner scanner) {
        System.out.println("Enter your customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        // Find the payer by ID
        Customer payer = findCustomerById(customerID);
        if (payer != null) {
            System.out.println("Enter credit account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            // Find the payer's credit account
            Account creditAccount = payer.getAccountByNumber(accountNumber);
            if (creditAccount != null && creditAccount instanceof Credit) {
                System.out.println("Enter receiver's customer ID: ");
                int receiverID = scanner.nextInt();
                scanner.nextLine();

                // Find the receiver by ID
                Customer receiver = findCustomerById(receiverID);
                if (receiver != null) {
                    System.out.println("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    // Attempt to make the payment
                    ((Credit) creditAccount).pay(amount, receiver);
                    System.out.println("Payment successful. New credit balance: " + creditAccount.getBalance());

                    String logMessage = payer.getFirstName() + " " + payer.getLastName() 
                    + " paid " + receiver.getFirstName() + " " + receiver.getLastName() 
                    + " $" + amount + " from " + creditAccount.getAccountType() 
                    + "-" + creditAccount.getAccountNumber() + ". " 
                    + payer.getFirstName() + " " + payer.getLastName() 
                    + "'s New Balance for " + creditAccount.getAccountType() 
                    + "-" + creditAccount.getAccountNumber() + ": $" + creditAccount.getBalance();
                    transactionLogger.log(logMessage);
                } else {
                    System.out.println("Receiver not found.");
                }
            } else {
                System.out.println("Invalid credit account.");
            }
        } else {
            System.out.println("Payer not found.");
        }
    }
        /**
 * Saves the current customer and account information to a CSV file.
 *
 * @param filePath The path to save the CSV file.
 */
    public static void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write the CSV header
            bw.write("Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number,"
                    + "Checking Account Number,Checking Starting Balance,Savings Account Number,"
                    + "Savings Starting Balance,Credit Account Number,Credit Max,Credit Starting Balance\n");

            // Write each customer's information
            for (Customer customer : customerList) {
                StringBuilder line = new StringBuilder();

                // Extract customer information
                line.append(customer.getId()).append(",");
                line.append(customer.getFirstName()).append(",");
                line.append(customer.getLastName()).append(",");
                line.append(customer.getBirthDate()).append(",");
                line.append("\"").append(customer.getAddress()).append("\","); // Handle commas in addresses
                line.append(customer.getPhoneNumber()).append(",");

                // Extract checking account information
                Account checking = customer.getAccountByType("Checking");
                if (checking != null) {
                    line.append(checking.getAccountNumber()).append(",");
                    line.append(checking.getBalance()).append(",");
                } else {
                    line.append("0,0,"); // Default for missing account
                }

                // Extract savings account information
                Account savings = customer.getAccountByType("Saving");
                if (savings != null) {
                    line.append(savings.getAccountNumber()).append(",");
                    line.append(savings.getBalance()).append(",");
                } else {
                    line.append("0,0,"); // Default for missing account
                }

                // Extract credit account information
                Account credit = customer.getAccountByType("Credit");
                if (credit != null && credit instanceof Credit) {
                    Credit creditAccount = (Credit) credit;
                    line.append(creditAccount.getAccountNumber()).append(",");
                    line.append(creditAccount.getMaxCredit()).append(",");
                    line.append(creditAccount.getBalance()).append(",");
                } else {
                    line.append("0,0,0,"); // Default for missing account
                }

                // Write the line to the file
                bw.write(line.toString() + "\n");
            }

            System.out.println("Data saved to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }

    }
}



