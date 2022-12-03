package org.backend.models;

import org.backend.Connect;

import java.sql.*;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private String userName;
    private String token;
    private int isActive;
    private int isAdmin;
    private int isEmployee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    //default Constructor
    public User(){}

    public User(int id, String firstName, String lastName, String phone, String address, String email,
                String username, int isActive, int isAdmin, int isEmployee, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = username;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isEmployee = isEmployee;
        this.token = token;
    }

    public User(int id, String firstName, String lastName, String phone, String address, String email,
                String userName, int isActive, int isAdmin, int isEmployee, String token, LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isEmployee = isEmployee;
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    //constructor
    public User(String firstName, String lastName, String phone, String address, String email,
                String username, int isActive, int isAdmin, int isEmployee) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        try{
            String query = "SELECT COALESCE(MAX(id),0) AS LAST FROM customer_details";
            PreparedStatement pst1 = connection.prepareStatement(query);
            ResultSet rs1 = pst1.executeQuery();
            String maxId=  rs1.getString("LAST");
            int intMaxId =(Integer.parseInt(maxId))+1;
            this.id = intMaxId;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = username;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isEmployee = isEmployee;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "phone=" + phone + ", " +
                "address=" + address + ", " +
                "email=" + email + ", " +
                "userName=" + userName + ", " +
                "token=" + token + ", " +
                "isActive=" + isActive + ", " +
                "isAdmin=" + isAdmin + ", " +
                "isEmployee=" + isEmployee + '}';
    }


}
