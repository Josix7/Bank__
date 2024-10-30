Banking System Simulation
Overview
This Java-based banking system simulation provides a console interface for users to perform basic banking operations such as balance inquiry, deposits, withdrawals, transfers, and payments. It reads user information from a CSV file, supports multiple sessions, and logs all transactions in a persistent log file.

Features
Load Customer Data from CSV: Initializes customers and their accounts from a CSV file.
Account Types: Supports different types of bank accounts, including checking, savings, and credit.
Banking Operations:
Balance Inquiry
Deposit
Withdrawal
Transfer
Payment
Transaction Logging: Logs all transactions in a persistent log file that retains records across sessions.
Extensible Design: Designed using object-oriented principles, allowing for easy addition of new account types and features.
Project Structure
plaintext
Copy code
├── RunBank.java          # Main class for running the banking system
├── Customer.java         # Class representing a customer with multiple accounts
├── Account.java          # Abstract class for different account types
├── Checking.java         # Checking account class
├── Saving.java           # Savings account class
├── Credit.java           # Credit account class
├── TransactionLogger.java # Handles logging of transactions
├── Bank_users.csv        # Sample CSV file with customer data
└── TransactionLog.txt    # Persistent log file for transactions
Getting Started
Prerequisites
Java Development Kit (JDK): Ensure JDK 8 or later is installed.
IDE: Use any Java-supported IDE like Eclipse, IntelliJ IDEA, or Visual Studio Code.
Running the Project
Compile the Code: Navigate to the directory containing the .java files and run:

bash
Copy code
javac RunBank.java
Run the Application:

bash
Copy code
java RunBank
Load Customer Data: Ensure the Bank_users.csv file is in the project directory before starting. The application will read customer and account data from this file.

Use the Menu: The console menu will guide you through available banking operations. Select an option by entering the corresponding number.

CSV File Format
The CSV file should have the following format:

csv
Copy code
Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number,
Checking Account Number,Checking Starting Balance,
Savings Account Number,Savings Starting Balance,
Credit Account Number,Credit Max,Credit Starting Balance
Example row:

csv
Copy code
12345,Mickey,Mouse,01/01/1990,"123 Disney St, Magic City",5551234567,
67890,150.00,
11112,25.00,
22223,500.00,50.00
Transaction Log
Transactions are logged in a text file named TransactionLog.txt in the project directory. The log retains information across sessions and appends each new transaction.

Log Format
Example entries:

vbnet
Copy code
Mickey Mouse made a balance inquiry on Checking-67890. Mickey Mouse's Balance for Checking-67890: $150
Mickey Mouse deposited $50 to Checking-67890. Mickey Mouse's New Balance for Checking-67890: $200
Mickey Mouse transferred $20 from Checking-67890 to Savings-11112. Mickey Mouse's Balance for Checking-67890: $180. Mickey Mouse's Balance for Savings-11112: $45
Design and Implementation
Key Classes
RunBank: Main class that handles the user interface and menu operations.
Customer: Represents a bank customer with multiple accounts.
Account: Abstract base class providing core account functionalities.
Checking, Saving, Credit: Extend the Account class to implement specific account behaviors.
TransactionLogger: Handles appending transaction messages to the log file.
Core Functionalities
Object-Oriented Design: Utilizes inheritance, polymorphism, and encapsulation to organize and manage different account types.
Error Handling: Provides validation for input and transaction amounts, ensuring safe banking operations.
Transaction Logging: All transactions are logged with details such as the type of transaction, account type, and new balance.
Future Enhancements
GUI Interface: Develop a graphical user interface for easier interaction.
Authentication System: Implement user login and password protection for added security.
Database Integration: Migrate customer data and transaction history to a database for better scalability.
Additional Account Types: Add support for investment accounts, joint accounts, etc.
Author
[Jose Luis Hernandez]
Version
1.4
License
This project is open-source and available under the MIT License. Feel free to modify and distribute it.

Contact
For questions or issues, please reach out to [Your Email].

Let me know if you need any specific adjustments!
