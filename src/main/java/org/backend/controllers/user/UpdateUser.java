package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class UpdateUser {

    public boolean updateUser(int id, User loggedInUser) {
        boolean success = false;
        GetUser getUser = new GetUser();
        HashMap<String, String> response = getUser.getUser(id, loggedInUser);
        Connect c = new Connect();
        Connection connection = c.createConnection();
        User UpdatedUser = null;
        if(connection!=null){
            if (response.get("status").equals("success")) {
                UpdatedUser = new User(Integer.parseInt(response.get("id")), response.get("firstName"),
                        response.get("lastName"), response.get("phone"),
                        response.get("address"), response.get("email"),
                        response.get("userName"), Integer.parseInt(response.get("isActive")),
                        Integer.parseInt(response.get("isAdmin")),
                        Integer.parseInt(response.get("isEmployee")), response.get("token"),
                        ConvertDate.convertStringToDate(response.get("createdAt")),
                        ConvertDate.convertStringToDate(response.get("updatedAt")),
                        Integer.parseInt(response.get("hasCollateral")),
                        Integer.parseInt(response.get("hasLoan")),
                        Integer.parseInt(response.get("isCustomer")));

                try {
                    if (loggedInUser.getIsAdmin() == 1 || loggedInUser.getToken().equals(UpdatedUser.getToken())) {
                        //statement to update user
                        UpdatedUser.setUserName("prateekjain");
                        String query = "UPDATE user_details SET first_name =?, last_name=?, phone=?, address=?, " +
                                "email=?, username=?, is_active=?, is_employee=?, is_admin=?, updated_date=?, " +
                                "has_collateral=? WHERE id=?;";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, UpdatedUser.getFirstName());
                        pstmt.setString(2, UpdatedUser.getLastName());
                        pstmt.setString(3, UpdatedUser.getPhone());
                        pstmt.setString(4, UpdatedUser.getAddress());
                        pstmt.setString(5, UpdatedUser.getEmail());
                        pstmt.setString(6, UpdatedUser.getUserName());
                        pstmt.setInt(7, UpdatedUser.getIsActive());
                        pstmt.setInt(8, UpdatedUser.getIsEmployee());
                        pstmt.setInt(9, UpdatedUser.getIsAdmin());
                        String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                        pstmt.setString(10, ts);
                        pstmt.setInt(11, UpdatedUser.getHasCollateral());
                        pstmt.setString(12, String.valueOf(UpdatedUser.getId()));
                        pstmt.executeUpdate();
                        pstmt.close();
                        AddToAllEvents addToAllEvents = new AddToAllEvents();
                        addToAllEvents.addToAllEvents(connection, "User : " + UpdatedUser.getId()  + " has been updated to " + UpdatedUser);
                        success = true;
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                } finally {
                    try {
                            connection.close();
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
            } else {
                System.out.println(response.get("message"));
            }
        }

        return success;
    }
}
