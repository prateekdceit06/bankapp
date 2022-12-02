package org.backend;

import java.util.List;

public class Customer extends Person {

    private int hasCollateral;
    private int hasLoan;


    public Customer(int id, String firstName, String lastName, String phone, String address, String email, String username, int isActive, int isAdmin, int isEmployee, String token) {
        super(id, firstName, lastName, phone, address, email, username, isActive, isAdmin, isEmployee, token);
        this.hasCollateral = 0;
        this.hasLoan = 0;
    }

    public int getHasLoan() {
        return hasLoan;
    }

    public void setHasLoan(int hasLoan) {
        this.hasLoan = hasLoan;
    }


    public int getHasCollateral() {
        return hasCollateral;
    }

    public void setHasCollateral(int hasCollateral) {
        this.hasCollateral = hasCollateral;
    }


    @Override
    public String toString() {
        return "Customer{" + "hasCollateral=" + hasCollateral + ", hasLoan=" + hasLoan + '}';
    }
}


