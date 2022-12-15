# CS611-3 - Bank 611

Prateek Jain
Pranesh Jayakumar
Haniel Jacob
Abhinav Srivastava (sabhinav@bu.edu)

## Files

---------------------------------------------------------------------------

### Frontend 

**AccountDetails.java**: Displays the details of an account.
**GetUserByUserID.java**: Retrieves a user based on a given user ID.
**SellStocks.java**: Allows a user to sell stocks.
**ActiveCustomers.java**: Displays a list of active customers.
**GetUserByUserName.java**: Retrieves a user based on a given username.
**ShowCheckingsAccounts.java**: Displays a list of checkings accounts.
**ActiveUsers.java**: Displays a list of active users.
**InactiveCustomers.java**: Displays a list of inactive customers.
**ShowPoorCustomers.java**: Displays a list of customers with a low balance.
**ApplyLoan.java**: Allows a user to apply for a loan.
**BankerHome.java**: Home page for bankers.
**BankerTransactionDetails.java**: Displays the details of a banker transaction.
**BuyStocks.java**: Allows a user to buy stocks.
**ChangePasswordPrompt.java**: Prompts a user to change their password.

### Backend files

Plays music in the background.
Menu.java - Generates the main menu for the application.
ApprovedLoan.java - Model to store approved loan details.
AccountSavings.java - Model to store savings account details.
Manager.java - Model to store manager details.
Customer.java - Model to store customer details.
User.java - Model to store user details.
Loan.java - Model to store loan details.
Account.java - Model to store account details.
AccountNewSecurity.java - Model to store new security account details.
AccountLoan.java - Model to store loan account details.
Stock.java - Model to store stock details.
AccountChecking.java - Model to store checking account details.
AccountFactory.java - Factory to create an account instance.
Transaction.java - Model to store transaction details.
AddToAllEvents.java - Adds an event to the allEvents list.
Data.java - Stores static data for the application.
SHA256.java - Encrypts strings using SHA256.
ConvertDate.java - Converts a date from one format to another.
Main.java - The main application class.
ApplyForLoan.java - Controller to apply for a loan.
PayLoan.java - Controller to pay off a loan.
ApproveRejectLoan.java - Controller to approve/reject a loan.
UpdateUser.java - Controller to update user information.
GetUser.java - Controller to get user information.
StockTransaction.java - Controller to make a stock transaction.
ChangePassword.java - Controller to change a user's password.
GetToken.java - Controller to generate a user authentication token.
Logout.java - Controller to log a user out of their account.
GetAllUsers.java - Controller to get all users.
Signup.java - Controller to sign a user up.
Signin.java - Controller to sign a user in.
LoadApprovedLoansData.java - Controller to load approved loan data.
GetInterestDuration.java - Controller to get the interest duration.
InitializeBank.java - Controller to initialize the bank.
LoadStockData.java - Controller to load stock data.
UpdateInterestPaidDate.java - Controller to update the interest paid date.
LoadUserData.java - Controller to load user data.
UpdateStocks.java - Controller to update stocks.
LoadLoanData.java - Controller to load loan data.
LoadTransactions.java - Controller to load transaction data.
LoadStockTransactions.java - Controller to load stock transaction data.
LoadAccounts.java - Controller to load account data.
Deposit.java - Controller to deposit money into an account.
ViewAccount.java - Controller to view an account.
CreateAccount.java - Controller to create an account.
GetCustomerIdByAccountNumber.java - Controller to get a customer's ID by their account number.
Withdraw.java - Controller to withdraw money from an account.
CurrencyConversion.java - Controller to convert currencies.
Transfer.java - Controller to transfer money between accounts.
GenerateAccountNumber.java - Controller to generate an account number.
CloseAccount.java - Controller to close an account.
SellStock.java - Controller to sell stocks.
BuyStock.java - Controller to buy stocks.
UpdateStock.java - Controller to update stock information.
Connect.java - Connects to the database and returns a connection instance.


## Notes

---------------------------------------------------------------------------




## How to compile and run

---------------------------------------------------------------------------

1. Navigate to the directory "pa4/src" after unzipping the files
2. Run the following instructions:
   javac Main.java
   java Main
