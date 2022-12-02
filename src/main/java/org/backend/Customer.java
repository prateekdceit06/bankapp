package org.backend;

import java.util.List;

public class Customer extends Person{

    private int isActive;
    private int isAdmin;
    private int isEmployee;
    private int hasCollateral;
    private int hasLoan;
    private String username;
    private String password;


    public Customer(String firstName, String lastName, String phone, String address, String email, String username,
                    String password, int isActive, int isAdmin, int isEmployee) {
        super(firstName, lastName, phone, address, email);
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isEmployee = isEmployee;
        this.username = username;
        this.password = password;
        this.hasCollateral = 0;
        this.hasLoan = 0;
    }

    public int getHasLoan() {
        return hasLoan;
    }

    public void setHasLoan(int hasLoan) {
        this.hasLoan = hasLoan;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(int isEmployee) {
        this.isEmployee = isEmployee;
    }

    public int getHasCollateral() {
        return hasCollateral;
    }

    public void setHasCollateral(int hasCollateral) {
        this.hasCollateral = hasCollateral;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "isActive=" + isActive +
                ", isAdmin=" + isAdmin +
                ", isEmployee=" + isEmployee +
                ", hasCollateral=" + hasCollateral +
                ", hasLoan=" + hasLoan +
                ", username='" + username + '\'';
    }
}


