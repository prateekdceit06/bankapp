package org.example;

public class Employees extends Person{
    private int employeeID;
    private String username;
    private String password;
    private int active;
    private int admin;

    public Employees(String firstName, String lastName, String username, String password, String phone, String address, String email, int employeeID, int active, int admin) {
        super(firstName, lastName, phone, address, email);
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.active = active;
        this.admin = admin;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    @Override
    public String toString() {
        return "Employees [employeeID=" + employeeID + ", username=" + username + ", password=" + password + ", active=" + active + ", admin=" + admin + "]";
    }

}
