/**
 * Represents a credit account, a type of bank account that allows withdrawals 
 * and payments up to a specified credit limit. This class extends the {@code Account} class.
 * 
 * <p>
 * The {@code Credit} class includes a credit limit, customized withdrawal behavior, 
 * and a payment method for transferring funds to a receiver's account.
 * </p>
 * 
 * @see Account
 * @see Customer
 * 
 * @author [Jose Luis Hernandez]
 * @version 1.3
 */
public class Credit extends Account {

    /**
     * The maximum credit limit for the account.
     */
    private double creditLimit;
    
    private double maxCredit;

    /**
     * Constructs a new {@code Credit} account with the specified account number, 
     * credit limit, and initial balance.
     * 
     * @param accountNumber The unique number associated with this credit account.
     * @param creditLimit   The maximum allowable negative balance for this account.
     * @param balance       The initial balance of the credit account.
     */
    public Credit(int accountNumber, double creditLimit, double balance) {
        super(accountNumber, balance);
        this.creditLimit = creditLimit;
    }
    /**
     * Gets the maximum credit limit of this account.
     *
     * @return The maximum credit limit.
     */
    public double getMaxCredit() {
        return maxCredit;
    }

    /**
     * Withdraws a specified amount from the credit account, considering the credit limit.
     * 
     * <p>
     * The withdrawal is allowed if the resulting balance does not exceed the negative 
     * credit limit. Otherwise, an error message is displayed.
     * </p>
     * 
     * @param amount The amount to be withdrawn. Must be greater than 0 and within the 
     *               allowable credit limit.
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= -creditLimit) {
            balance -= amount;
        } else {
            System.out.println("Credit limit exceeded or invalid withdrawal amount.");
        }
    }

    /**
     * Makes a payment to a receiver's account, reducing the credit account balance.
     * 
     * <p>
     * The payment is allowed if the resulting balance does not exceed the negative 
     * credit limit. The amount is deposited into the first account of the receiver.
     * </p>
     * 
     * @param amount   The amount to be paid. Must be greater than 0 and within the 
     *                 allowable credit limit.
     * @param receiver The customer receiving the payment.
     */
    public void pay(double amount, Customer receiver) {
        if (amount > 0 && balance - amount >= -creditLimit) {
            balance -= amount;
            receiver.getAccounts().get(0).deposit(amount);  // Assume deposit to the first account
        } else {
            System.out.println("Payment failed. Check the amount or credit limit.");
        }
    }

    /**
     * Returns the account details as a string, specifically indicating 
     * that this is a credit account and includes the credit limit.
     * 
     * @return A string containing the account details with the credit limit.
     */
    @Override
    public String getAccountDetails() {
        return "Credit Account - " + super.getAccountDetails() + ", Credit Limit: " + creditLimit;
    }
}
