import java.util.ArrayList;

/**
 * Represents a customer, inheriting from the {@code Person} class.
 * A customer can have multiple bank accounts and is identified by a unique ID.
 * 
 * <p>
 * The {@code Customer} class extends {@code Person} to include an ID 
 * and a list of accounts associated with the customer. It provides methods 
 * for managing accounts and retrieving customer information.
 * </p>
 * 
 * @see Person
 * @see Account
 * 
 * @author [Jose Luis Hernandez]
 * @version 1.3
 */
public class Customer extends Person {

    /**
     * The unique ID of the customer.
     */
    private int id;

    /**
     * A list of accounts associated with the customer.
     */
    private ArrayList<Account> accounts;

    /**
     * Constructs a new {@code Customer} with the specified ID and personal details.
     * 
     * @param id          The unique ID of the customer.
     * @param firstName   The first name of the customer.
     * @param lastName    The last name of the customer.
     * @param birthDate   The birth date of the customer in the format YYYY-MM-DD.
     * @param address     The address of the customer.
     * @param phoneNumber The phone number of the customer.
     */
    public Customer(int id, String firstName, String lastName, String birthDate, String address, String phoneNumber) {
        super(firstName, lastName, birthDate, address, phoneNumber);
        this.id = id;
        this.accounts = new ArrayList<>();
    }

    /**
     * Returns the ID of the customer.
     * 
     * @return The ID of the customer.
     */
    public int getId() {
        return id;
    }

    /**
     * Adds an account to the customer's list of accounts.
     * 
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Returns the list of accounts associated with the customer.
     * 
     * @return An {@code ArrayList} of accounts.
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Retrieves an account from the customer's list of accounts by its account number.
     * 
     * @param accountNumber The account number to search for.
     * @return The {@code Account} with the specified account number, or {@code null} 
     *         if no matching account is found.
     */
    public Account getAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;  // Account not found
    }
        /**
     * Finds an account by type.
     *
     * @param type The type of account to find (e.g., "Checking", "Saving", "Credit").
     * @return The account if found, otherwise null.
     */
    public Account getAccountByType(String type) {
        for (Account account : accounts) {
            if (account.getClass().getSimpleName().equalsIgnoreCase(type)) {
                return account;
            }
        }
        return null; // Account not found
    }
}
