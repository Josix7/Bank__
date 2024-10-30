/**
 * An abstract representation of a bank account, providing core functionalities 
 * such as deposits, withdrawals, transfers, and account information retrieval.
 * 
 * <p>
 * The {@code Account} class includes fields for the account number and balance,
 * as well as methods for managing account transactions. This class is intended to be 
 * extended by specific account types such as checking and savings accounts.
 * </p>
 * 
 * @author [Jose Luis Hernandez]
 * @version 1.4
 */
public abstract class Account {

    /**
     * The unique account number associated with this account.
     */
    protected int accountNumber;

    /**
     * The current balance of the account.
     */
    protected double balance;

    /**
     * Constructs a new {@code Account} with the specified account number 
     * and initial balance.
     * 
     * @param accountNumber The unique number associated with this account.
     * @param balance       The initial balance of the account.
     */
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * Returns the account number of this account.
     * 
     * @return The account number.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the current balance of this account.
     * 
     * @return The balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits a specified amount into the account.
     * 
     * @param amount The amount to be deposited. Must be greater than 0.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Withdraws a specified amount from the account if it is valid.
     * 
     * @param amount The amount to be withdrawn. Must be greater than 0 and 
     *               less than or equal to the current balance.
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    /**
     * Transfers a specified amount from this account to a target account if valid.
     * 
     * @param targetAccount The account to which the money is to be transferred.
     * @param amount        The amount to be transferred. Must be greater than 0 
     *                      and less than or equal to the current balance.
     */
    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            withdraw(amount);
            targetAccount.deposit(amount);
        } else {
            System.out.println("Transfer failed. Check the amount.");
        }
    }

    /**
     * Returns a string containing the account details.
     * 
     * @return A string with the account number and current balance.
     */
    public String getAccountDetails() {
        return "Account Number: " + accountNumber + ", Balance: " + balance;
    }

    /**
     * Returns a string containing account type
     * 
     * @return A string with account type 
     */

    public String getAccountType(){
       return this.getClass().getSimpleName();
    }
}

