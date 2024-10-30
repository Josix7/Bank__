/**
 * Represents a savings account, which is a type of bank account 
 * that earns interest over time. This class extends the {@code Account} class.
 * 
 * <p>
 * The {@code Saving} class includes a constant interest rate, methods to add interest
 * to the current balance, and overrides the account details retrieval method to provide
 * specific information about the savings account.
 * </p>
 * 
 * @author [Jose Luis Hernandez]
 * @version 1.4
 */
public class Saving extends Account {

    /**
     * The interest rate applied to the savings account. 
     * This is a constant value set to 2% (0.02).
     */
    private static final double INTEREST_RATE = 0.02; // Example interest rate

    /**
     * Constructs a new {@code Saving} account with the specified account number
     * and initial balance.
     * 
     * @param accountNumber The unique number associated with this account.
     * @param balance       The initial balance of the account.
     */
    public Saving(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Adds interest to the current balance of the savings account.
     * 
     * <p>
     * The interest is calculated based on the current balance and the interest rate,
     * and then added to the balance.
     * </p>
     */
    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }

    /**
     * Returns the account details as a string, specifically indicating 
     * that this is a savings account.
     * 
     * @return A string containing the account details, prefixed with "Saving Account - ".
     */
    @Override
    public String getAccountDetails() {
        return "Saving Account - " + super.getAccountDetails();
    }
}
