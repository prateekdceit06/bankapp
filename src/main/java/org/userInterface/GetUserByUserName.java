/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.controllers.user.GetUser;
import org.backend.controllers.user.UpdateUser;
import org.backend.models.Manager;
import org.backend.models.User;

/**
 *
 * @author praneshjayasundar
 */
public class GetUserByUserName extends javax.swing.JDialog {

    // Variables declaration - do not modify                     
    private javax.swing.JPanel content;
    private javax.swing.JTable customerDetails;
    private javax.swing.JTextField customerIdValue;
    private javax.swing.JLabel formTitle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel personalDetails;
    private javax.swing.JButton reset;
    private javax.swing.JButton search;
    private javax.swing.JButton update;
    private javax.swing.JLabel userName;

    static User loggedInUserGlobal;
    // End of variables declaration   
    
    /**
     * Creates new form GetUserByUserID
     */
    public GetUserByUserName(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        content = new javax.swing.JPanel();
        formTitle = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        customerIdValue = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerDetails = new javax.swing.JTable();
        personalDetails = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        content.setBackground(new java.awt.Color(0, 153, 153));
        
        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("User Search");

        userName.setText("Username");

        customerIdValue.setColumns(15);

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        customerDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Phone", "Address", "Email", "Username", "Password", "Active", "Employee", "Admin", "Customer", "Collateral", "Loan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerDetails.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(customerDetails);
        customerDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        personalDetails.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        personalDetails.setText("Personal Details");

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(personalDetails)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(contentLayout.createSequentialGroup()
                                    .addComponent(userName)
                                    .addGap(18, 18, 18)
                                    .addComponent(customerIdValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentLayout.createSequentialGroup()
                                    .addComponent(search)
                                    .addGap(45, 45, 45)
                                    .addComponent(reset)))
                            .addGroup(contentLayout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(formTitle))))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(482, 482, 482)
                        .addComponent(update)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(formTitle)
                .addGap(31, 31, 31)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName)
                    .addComponent(customerIdValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search)
                    .addComponent(reset))
                .addGap(43, 43, 43)
                .addComponent(personalDetails)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(update)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {                                       
        Manager manager = new Manager();
        User tempUser = null;
        String userNameSearch;
        boolean found = false;

        if (loggedInUserGlobal != null) {
            if (loggedInUserGlobal.getIsAdmin() == 1) {
                userNameSearch = customerIdValue.getText();
            } else {
                userNameSearch = customerIdValue.getText();
            }
            
            //find the id in the users list of the manager
            for (User user : manager.getUsers()) {
                if (user.getUserName().equals(userNameSearch)) {
                    tempUser = user;
                    found = true;
                    break;
                }
            }

            if(!found){
                JOptionPane.showMessageDialog(null, "No user found with this username");
            }

            //Delete the first record in the table
            DefaultTableModel model = (DefaultTableModel)customerDetails.getModel();
            int a = model.getRowCount();
            if(a > 0){
                System.out.println("Delete first row");
                model.setRowCount(0);
            }

            addData(tempUser);
            System.out.println("Success");
            System.out.println(tempUser);
        }
    }                                      

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {                                      
        DefaultTableModel model = (DefaultTableModel)customerDetails.getModel();                                     
        model.setRowCount(0);
    }                                     

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
        DefaultTableModel model =(DefaultTableModel) customerDetails.getModel();
        int selectedRow = customerDetails.getSelectedRow();

        //get the data from the customer home page
        int id = (int)model.getValueAt(selectedRow, 0);
        String firstName = model.getValueAt(selectedRow, 1).toString();
        String lastName = model.getValueAt(selectedRow, 2).toString();
        String mobile = model.getValueAt(selectedRow, 3).toString();
        String address = model.getValueAt(selectedRow, 4).toString();
        String email = model.getValueAt(selectedRow, 5).toString();
        String username = model.getValueAt(selectedRow, 6).toString();
        int is_active = (int)model.getValueAt(selectedRow, 8);
        int is_employee = (int)model.getValueAt(selectedRow, 9);
        int is_admin = (int)model.getValueAt(selectedRow, 10);
        int isCustomer = (int)model.getValueAt(selectedRow, 11);
        int hasCollateral = (int)model.getValueAt(selectedRow, 12);
        int hasLoan = (int)model.getValueAt(selectedRow, 13);
        
        User updatedUserDetails;
        Manager manager = new Manager();

        //update user details
        if (loggedInUserGlobal != null) {
            int userId = 0;
            if (loggedInUserGlobal.getIsAdmin() == 1) {
                System.out.print("Enter ID: ");
                userId = id;
            } else {
                userId = id;
            }
            GetUser getUser = new GetUser();
            HashMap<String, String>  response = getUser.getUser(userId, loggedInUserGlobal);

            if (response.containsKey("status")) {
                if (response.get("status").equals("success")) {
                    updatedUserDetails = new User(id, firstName, lastName, mobile, address, email,
                    username, is_active, is_admin, is_employee, loggedInUserGlobal.getToken(), loggedInUserGlobal.getCreatedAt(),
                    loggedInUserGlobal.getUpdatedAt(), hasCollateral, hasLoan, isCustomer);
                        
                    
                    System.out.println(updatedUserDetails.toString());
                    UpdateUser updateUser = new UpdateUser();
                    boolean updateSuccess = updateUser.updateUser(loggedInUserGlobal, updatedUserDetails);

                    if (updateSuccess) {
                        System.out.println("User updated successfully");
                        manager.loadAllData();
                        loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
                        JOptionPane.showMessageDialog(null, "User updated successfully");
                    } else {
                        System.out.println("Something went wrong. User update failed.");
                        JOptionPane.showMessageDialog(null, "Something went wrong. User update failed.");
                    }

                } else {
                    System.out.println(response.get("message"));
                    JOptionPane.showMessageDialog(null, response.get("message"));
                }
            } else {
                System.out.println("Incorrect ID");
                JOptionPane.showMessageDialog(null, "Incorrect ID");
            }  
            
        } else {
            System.out.println("Please login first");
            JOptionPane.showMessageDialog(null, "Please login first");
        }
        
    }                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetUserByUserName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetUserByUserName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetUserByUserName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetUserByUserName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetUserByUserName dialog = new GetUserByUserName(new javax.swing.JDialog(), true, loggedInUserGlobal);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void addData(User searchUser){

        String[] columns = new String [] {
            "ID", "First Name", "Last Name", "Phone", "Address", "Email", "Username", "Password", "Active", "Employee", "Admin", "Customer", "Collateral", "Loan"
        };

        DefaultTableModel model = (DefaultTableModel)customerDetails.getModel();
        model.setColumnIdentifiers(columns);

        int ID = searchUser.getId();
        String firstName = searchUser.getFirstName();
        String lastName = searchUser.getLastName();
        String userName = searchUser.getUserName();
        String password = "********";
        String email = searchUser.getEmail();
        String mobile = searchUser.getPhone();
        int isActive = searchUser.getIsActive();
        int isEmployee = searchUser.getIsEmployee();
        int isAdmin = searchUser.getIsAdmin();

        int hasCollateral = searchUser.getHasCollateral();
        int hasLoan = searchUser.getHasLoan();
        int isCustomer = searchUser.getIsCustomer();

        String address = searchUser.getAddress();

        Vector<Object> vector = new Vector<>();
        vector.add(ID);
        vector.add(firstName);
        vector.add(lastName);
        vector.add(mobile);
        vector.add(address);
        vector.add(email);
        vector.add(userName);
        vector.add(password);
        vector.add(isActive);
        vector.add(isEmployee);
        vector.add(isAdmin);
        vector.add(isCustomer);
        vector.add(hasCollateral);
        vector.add(hasLoan);

        model.addRow(vector);
        System.out.println("Pranesh: "+ID);
    }
          
    public void removeRow(){
        DefaultTableModel model = (DefaultTableModel)customerDetails.getModel();
        if(model.getRowCount()>0){
            System.out.println("Remove");
            model.removeRow(0);
        }
    }
}