package org.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;

    //default Constructor
    public Person(){}

    //constructor
    public Person(String firstName, String lastName, String phone, String address, String email) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        try{
            String query = "SELECT MAX(id) AS LAST FROM person";
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
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
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
        return "Person :" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
