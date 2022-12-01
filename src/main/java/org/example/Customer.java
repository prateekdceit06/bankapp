package org.example;

public class Customer extends Person{
    private int customerID;
    private int employeeID;
    private int accountID;
    private int active;
    private int admin;
    private int employee;

    public Customer(String firstName, String lastName, String phone, String address, String email,
                    int customerID, int employeeID, int accountID, int active, int admin, int employee) {
        super(firstName, lastName, phone, address, email);
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.accountID = accountID;
        this.active = active;
        this.admin = admin;
        this.employee = employee;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", employeeID=" + employeeID +
                ", accountID=" + accountID +
                ", active=" + active +
                ", admin=" + admin +
                '}';
    }
}


