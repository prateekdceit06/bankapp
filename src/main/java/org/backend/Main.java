package org.backend;


import org.backend.controllers.user.*;
import org.backend.models.*;
import org.backend.staticdata.Data;
import org.backend.staticdata.SHA256;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Menu menu = new Menu();
        User loggedInUser = null;
        while (true) {
            User tempUser = null;
            menu.mainMenu();
            int choice = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1: // Signin
                        Signin signin = new Signin();
                        System.out.print("Enter your username: ");
                        String username = br.readLine();
                        System.out.print("Enter your password: ");
                        String signinPassword = br.readLine();
                        String encryptedSigninPassword = SHA256.getSHA(signinPassword);
                        HashMap<String, String> response = signin.signin(username, encryptedSigninPassword);
                        if (response.containsKey("status")) {
                            if (response.get("status").equals("success")) {
                                manager.loadUserData();
                                //find the username in users list of the manager
                                loggedInUser = manager.getLoggedInUser(username);
                                System.out.println(response.get("message"));
                                System.out.println(loggedInUser);

                            } else {
                                System.out.println(response.get("message"));
                            }
                        } else {
                            System.out.println("Username does not exist");
                        }


                        break;
                    case 2: // register
                        Signup signup = new Signup();
                        System.out.println("Enter your details");
                        System.out.print("Enter your first name: ");
                        String firstName = br.readLine();
                        System.out.print("Enter your last name: ");
                        String lastName = br.readLine();
                        System.out.print("Enter your username: ");
                        String userName = br.readLine();
                        System.out.print("Enter your phone number: ");
                        String phone = br.readLine();
                        System.out.print("Enter your address: ");
                        String address = br.readLine();
                        System.out.print("Enter your email: ");
                        String email = br.readLine();
                        System.out.print("Enter your password: ");
                        String signUpPassword = br.readLine();
                        System.out.print("Confirm your password: ");
                        String confirmSignUpPassword = br.readLine();
                        System.out.print("Are you an employee? (1/0)");
                        int is_employee = Integer.parseInt(br.readLine());
                        System.out.print("Are you an admin? (1/0)");
                        int is_admin = Integer.parseInt(br.readLine());
                        System.out.println("Do you have collateral? (1/0)");
                        int hasCollateral = Integer.parseInt(br.readLine());
                        if (signUpPassword.equals(confirmSignUpPassword)) {
                            String encryptedSignUpPassword = SHA256.getSHA(signUpPassword);
                            boolean signUpSuccess = signup.signup(firstName, lastName, phone, address, email, userName,
                                    encryptedSignUpPassword, is_employee, is_admin, hasCollateral);
                            if (signUpSuccess) {
                                System.out.println("You have successfully signed up");
                                manager.loadUserData();
                            } else {
                                System.out.println("Something went wrong. Signup Failed.");
                            }
                        } else {
                            System.out.println("Invalid Password. Please try again.");
                        }

                        break;
                    case 3: //get user by id
                        if (loggedInUser != null) {
                            GetUser getUser = new GetUser();
                            int id = 0;
                            if (loggedInUser.getIsAdmin() == 1) {
                                System.out.print("Enter ID: ");
                                id = Integer.parseInt(br.readLine());
                            } else {
                                id = loggedInUser.getId();
                            }
                            response = getUser.getUser(id, loggedInUser);
                            if (response.containsKey("status")) {
                                if (response.get("status").equals("success")) {
                                    //find the id in the users list of the manager
                                    for (User user : manager.getUsers()) {
                                        if (user.getId() == id) {
                                            tempUser = user;
                                            break;
                                        }
                                    }
                                    System.out.println(response.get("message"));
                                    System.out.println(tempUser);
                                } else {
                                    System.out.println(response.get("message"));
                                }
                            } else {
                                System.out.println("Incorrect ID");
                            }
                        } else {
                            System.out.println("Please login first");
                        }

                        break;
                    case 4: //get user by username
                        if (loggedInUser != null) {
                            GetUser getUser = new GetUser();
                            String userNameToFind = null;
                            if (loggedInUser.getIsAdmin() == 1) {
                                System.out.print("Enter Username: ");
                                userNameToFind = br.readLine();
                            } else {
                                userNameToFind = loggedInUser.getUserName();
                            }
                            response = getUser.getUser(userNameToFind, loggedInUser);
                            if (response.containsKey("status")) {
                                if (response.get("status").equals("success")) {
                                    //find the user in users list of the manager
                                    for (User user : manager.getUsers()) {
                                        if (user.getUserName().equals(userNameToFind)) {
                                            tempUser = user;
                                            break;
                                        }
                                    }
                                    System.out.println(response.get("message"));
                                    System.out.println(tempUser);
                                } else {
                                    System.out.println(response.get("message"));
                                }
                            } else {
                                System.out.println("Incorrect ID");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 5: //update user
                        if (loggedInUser != null) {
                            int userId = 0;
                            if (loggedInUser.getIsAdmin() == 1) {
                                System.out.print("Enter ID: ");
                                userId = Integer.parseInt(br.readLine());
                            } else {
                                userId = loggedInUser.getId();
                            }
                            GetUser getUser = new GetUser();
                            response = getUser.getUser(userId, loggedInUser);
                            User updatedUser = null;
                            if (response.containsKey("status")) {
                                if (response.get("status").equals("success")) {
                                    updatedUser = new User(userId, "prateek",
                                            loggedInUser.getLastName(), loggedInUser.getPhone(), loggedInUser.getAddress(),
                                            loggedInUser.getEmail(), loggedInUser.getUserName(), loggedInUser.getIsActive(),
                                            loggedInUser.getIsAdmin(), loggedInUser.getIsEmployee(), loggedInUser.getToken(),
                                            loggedInUser.getCreatedAt(), loggedInUser.getUpdatedAt(), loggedInUser.getHasCollateral(),
                                            loggedInUser.getHasLoan(), loggedInUser.getIsCustomer());
                                } else {
                                    System.out.println(response.get("message"));
                                }
                            } else {
                                System.out.println("Incorrect ID");
                            }
                            UpdateUser updateUser = new UpdateUser();
                            boolean updateSuccess = updateUser.updateUser(loggedInUser, updatedUser);
                            if (updateSuccess) {
                                System.out.println("User updated successfully");
                                manager.loadUserData();
                                loggedInUser = manager.getLoggedInUser(loggedInUser.getId());
                            } else {
                                System.out.println("Something went wrong. User update failed.");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 6: //change password
                        if (loggedInUser != null) {
                            System.out.print("Enter old password: ");
                            String oldPassword = br.readLine();
                            System.out.print("Enter new password: ");
                            String newPassword = br.readLine();
                            System.out.print("Confirm new password: ");
                            String confirmNewPassword = br.readLine();

                            if (newPassword.equals(confirmNewPassword)) {
                                String encryptedNewPassword = SHA256.getSHA(newPassword);
                                String encryptedOldPassword = SHA256.getSHA(oldPassword);
                                ChangePassword changePassword = new ChangePassword();
                                boolean changePasswordSuccess = changePassword.changePassword(encryptedOldPassword,
                                        encryptedNewPassword, loggedInUser);

                                if (changePasswordSuccess) {
                                    System.out.println("Password changed successfully");
                                    manager.loadUserData();
                                    loggedInUser = manager.getLoggedInUser(loggedInUser.getId());
                                } else {
                                    System.out.println("Something went wrong. Password change failed.");
                                }
                            } else {
                                System.out.println("Invalid Password. Please try again.");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 7: //logout
                        if (loggedInUser != null) {
                            Logout logout = new Logout();
                            boolean logoutSuccess = logout.logout(loggedInUser);
                            if (logoutSuccess) {
                                System.out.println("You have successfully logged out");
                                loggedInUser = null;
                                manager.loadUserData();
                            } else {
                                System.out.println("Something went wrong. Logout failed.");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 8: //Get all users
                        if (loggedInUser != null) {
                            if (loggedInUser.getIsAdmin() == 1) {
                                GetAllUsers getAllUsers = new GetAllUsers();
                                List<User> allUsers = getAllUsers.getAllUsers(loggedInUser);
                                if (allUsers != null) {
                                    for (User user : allUsers) {
                                        System.out.println(user);
                                    }
                                } else {
                                    System.out.println("Something went wrong. Please try again.");
                                }
                            } else {
                                System.out.println("You are not authorized to perform this action");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 9: //create savings Account
                        if (loggedInUser != null) {
                            boolean createSavingsAccountSuccess = false;
                            System.out.println("Enter Account Details");
                            System.out.print("Account Balance: ");
                            double accountBalance = Double.parseDouble(Data.df.format(Double.parseDouble(br.readLine())));
                            Account savingsAccount = new AccountSavings(loggedInUser.getId(),
                                    Data.AccountTypes.SAVINGS.toString(), accountBalance, 1);
                            createSavingsAccountSuccess = savingsAccount.createAccount(loggedInUser);
                            if (createSavingsAccountSuccess) {
                                System.out.println("Account created successfully");
                                manager.loadAccounts();
                                manager.loadUserData();
                                loggedInUser = manager.getLoggedInUser(loggedInUser.getId());

                            } else {
                                System.out.println("Something went wrong. Account creation failed.");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 10: //create savings Account
                        if (loggedInUser != null) {
                            boolean createCheckingAccountSuccess = false;
                            System.out.println("Enter Account Details");
                            System.out.print("Account Balance: ");
                            double accountBalance = Double.parseDouble(Data.df.format(Double.parseDouble(br.readLine())));
                            Account checkingAccount = new AccountChecking(loggedInUser.getId(),
                                    Data.AccountTypes.CHECKING.toString(), accountBalance, 1);
                            createCheckingAccountSuccess = checkingAccount.createAccount(loggedInUser);
                            if (createCheckingAccountSuccess) {
                                System.out.println("Account created successfully");
                                manager.loadAccounts();
                                manager.loadUserData();
                                loggedInUser = manager.getLoggedInUser(loggedInUser.getId());
                            } else {
                                System.out.println("Something went wrong. Account creation failed.");
                            }
                        } else {
                            System.out.println("Please login first");
                        }


                        break;
                    case 11:
                        //print all users from manager
                        System.out.println("All Users");
                        if (loggedInUser != null) {
                            if (loggedInUser.getIsAdmin() == 1) {
                                List<User> allUsers = manager.getUsers();
                                if (allUsers != null) {
                                    for (User user : allUsers) {
                                        System.out.println(user);
                                    }
                                } else {
                                    System.out.println("Something went wrong. Please try again.");
                                }
                            } else {
                                System.out.println("You are not authorized to perform this action");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 12:
                        //print all customers from manager
                        System.out.println("All Customers");
                        if (loggedInUser != null) {
                            if (loggedInUser.getIsAdmin() == 1) {
                                List<Customer> allCustomers = manager.getCustomers();
                                if (allCustomers != null) {
                                    for (User user : allCustomers) {
                                        System.out.println(user);
                                    }
                                } else {
                                    System.out.println("Something went wrong. Please try again.");
                                }
                            } else {
                                System.out.println("You are not authorized to perform this action");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 13:
                        //print logged in user
                        if (loggedInUser != null) {
                            System.out.println(loggedInUser);
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 14: //close account
                        if (loggedInUser != null) {
                            System.out.println("Enter Account Details");
                            System.out.print("Account Number: ");
                            String accountNumber = br.readLine();
                            //find account in manager accounts
                            if (manager.getAccounts() != null) {
                                for (Account account : manager.getAccounts()) {
                                    if ((account.getCustomerId() == loggedInUser.getId() ||
                                            loggedInUser.getIsAdmin() == 1) &&
                                            account.getAccountNumber().equals(accountNumber) &&
                                            account.getAccountStatus() == 1) {
                                        //close account
                                        boolean closeAccountSuccess = account.closeAccount();
                                        if (closeAccountSuccess) {
                                            System.out.println("Account closed successfully");
                                            manager.loadAccounts();
                                            manager.loadUserData();
                                            loggedInUser = manager.getLoggedInUser(loggedInUser.getId());
                                        } else {
                                            System.out.println("Something went wrong. Account close failed.");
                                        }
                                    }
                                else{
                                    System.out.println("Something went wrong. Account closing failed.");
                                }
                            }
                        } else {
                            System.out.println("You are not authorized to perform this action");
                        }
                } else{
                    System.out.println("Please login first");
                }
                break;

                case 99: //exit
                    System.out.println("Thank you for using our application");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Invalid input");
        }
//            finally {
//                try {
//                    br.close();
//                } catch (Exception e) {
//                    System.out.println("Error closing BufferedReader");
//                }
//            }
    }


    //customer registration form invoke
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CustomerRegistration().setVisible(true);
//            }
//        });

    /* Create and display the login form */
    // java.awt.EventQueue.invokeLater(new Runnable() {
    //     public void run() {
    //         new CustomerLogin().setVisible(true);
    //     }
    // });
}


}
