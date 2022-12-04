package org.backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    List<Account> accounts;

    public Customer(int id, String firstName, String lastName, String phone, String address,
                    String email, String userName, int isActive, int isAdmin, int isEmployee,
                    String token, LocalDateTime createdAt, LocalDateTime updatedAt, int hasCollateral,
                    int hasLoan, int isCustomer) {
        super(id, firstName, lastName, phone, address, email, userName, isActive,
                isAdmin, isEmployee, token, createdAt, updatedAt, hasCollateral, hasLoan, isCustomer);
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Customer{" +
                ", accounts=" + accounts +
                '}';
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }
}


