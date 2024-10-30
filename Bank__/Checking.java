/**
 * Represents a Checking account, which is a type of Account.
 * This class provides functionality specific to checking accounts.
 * Inherits general account functionality from the Account class.
 */
public class Checking extends Account {

    /**
     * Constructs a new Checking account with the specified account number and balance.
     *
     * @param accountNumber the unique identifier for the checking account
     * @param balance the initial balance of the checking account
     */
    public Checking(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Returns the details of the checking account.
     * This overrides the getAccountDetails method from the Account class.
     *
     * @return a string containing the type of account and account details
     */
    @Override
    public String getAccountDetails() {
        return "Checking Account - " + super.getAccountDetails();
    }
}
