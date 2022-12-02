package org.backend;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.userInterface.*;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.SHA256;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Bank bank = new Bank();
        menu.mainMenu();
        int choice = 0;
        System.out.print("Enter your choice: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        try {
            choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    Signin signin = new Signin();
                    System.out.print("Enter your username: ");
                    String username = br.readLine();
                    System.out.print("Enter your password: ");
                    String signinPassword = br.readLine();
                    String encryptedSigninPassword = SHA256.getSHA(signinPassword);
                    HashMap<String, String> response = signin.signin(username, encryptedSigninPassword);
                    if (response.get("status").equals("success")) {
                        person = new Person(Integer.parseInt(response.get("id")), response.get("firstName"),
                                response.get("lastName"), response.get("phone"),
                                response.get("address"), response.get("email"),
                                response.get("username"), Integer.parseInt(response.get("isActive")),
                                Integer.parseInt(response.get("isAdmin")),
                                Integer.parseInt(response.get("isEmployee")), response.get("token"));
                        System.out.println(person);
                        System.out.println(response.get("message"));

                    } else {
                        System.out.println(response.get("message"));
                    }
                    break;
                case 2:
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
                    if (signUpPassword.equals(confirmSignUpPassword)) {
                        String encryptedSignUpPassword = SHA256.getSHA(signUpPassword);
                        boolean signUpSuccess = signup.signup(firstName, lastName, phone, address, email, userName,
                                encryptedSignUpPassword, is_employee, is_admin);
                        if(signUpSuccess){
                            System.out.println("You have successfully signed up");
                        } else {
                            System.out.println("Something went wrong. Signup Failed.");
                        }
                    } else {
                        System.out.println("Invalid Password. Please try again.");
                    }

                    break;
                case 3:
                    System.out.println("Thank you for using our services.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally
        {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("Error closing BufferedReader");
            }
        }

        //customer registration form invoke
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegistration().setVisible(true);
            }
        });

        /* Create and display the login form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new CustomerLogin().setVisible(true);
        //     }
        // });
    }


}
