/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import org.backend.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.controllers.user.GetUser;
import org.backend.controllers.user.UpdateUser;
import org.backend.models.Account;
import org.backend.models.AccountChecking;
import org.backend.models.AccountSavings;
import org.backend.models.ApprovedLoan;
import org.backend.models.Customer;
import org.backend.models.Manager;
import org.backend.staticdata.Data;

/**
 *
 * @author praneshjayasundar
 */
public class ShowPoorCustomers extends javax.swing.JDialog {

    static User loggedInUserGlobal;
    /**
     * Creates new form GetUserByUserID
     */
    public ShowPoorCustomers(javax.swing.JDialog  parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
        addData();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        customerDetails = new javax.swing.JTable();
        personalDetails = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        accountDetails = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        formTitle1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionDetails = new javax.swing.JTable();
        search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        content.setBackground(new java.awt.Color(255, 255, 255));

        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("Poor Customers");

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
                false, true, true, true, true, true, true, false, true, true, true, true, true, false
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

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Account Number", "Type", "Balance", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(accountsTable);

        accountDetails.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        accountDetails.setText("Account Details");

        loanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loan ID", "Customer ID", "Account Number", "Sanctioned Amount", "Loan Interest", "Loan Interest Unit", "Remaining Loan Amount", "Duration", "Monthly Payment Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(loanTable);

        formTitle1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        formTitle1.setText("Transaction Details");

        transactionDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Transaction Status", "Account Number", "Initiating Customer ID", "Receiving Customer ID", "Transaction Type", "Amount", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transactionDetails.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(transactionDetails);

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(contentLayout.createSequentialGroup()
                            .addGap(460, 460, 460)
                            .addComponent(formTitle))
                        .addGroup(contentLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(personalDetails)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(accountDetails)))
                        .addGroup(contentLayout.createSequentialGroup()
                            .addGap(487, 487, 487)
                            .addComponent(update)
                            .addGap(18, 18, 18)
                            .addComponent(search)))
                    .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(formTitle1)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(formTitle)
                .addGap(26, 26, 26)
                .addComponent(personalDetails)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(search))
                .addGap(18, 18, 18)
                .addComponent(accountDetails)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(formTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

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

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {                                       
        //clear the table
        DefaultTableModel accountModel = (DefaultTableModel)accountsTable.getModel();
        accountModel.setRowCount(0);                                     
        addAccountData();

        //clear the table
        DefaultTableModel modelTransaction = (DefaultTableModel)transactionDetails.getModel();
        modelTransaction.setRowCount(0);
        addTransactionData();

        //clear the table
        DefaultTableModel loanModel = (DefaultTableModel)loanTable.getModel();
        loanModel.setRowCount(0);
        addLoanData();
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
            java.util.logging.Logger.getLogger(ShowPoorCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowPoorCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowPoorCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowPoorCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShowPoorCustomers dialog = new ShowPoorCustomers(new javax.swing.JDialog(), true, loggedInUserGlobal);
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

    // Variables declaration - do not modify                     
    private javax.swing.JLabel accountDetails;
    private javax.swing.JTable accountsTable;
    private javax.swing.JPanel content;
    private javax.swing.JTable customerDetails;
    private javax.swing.JLabel formTitle;
    private javax.swing.JLabel formTitle1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable loanTable;
    private javax.swing.JLabel personalDetails;
    private javax.swing.JButton search;
    private javax.swing.JTable transactionDetails;
    private javax.swing.JButton update;
    // End of variables declaration
    
    public void addData(){
        if (loggedInUserGlobal != null) {
            if (loggedInUserGlobal.getIsAdmin() == 1) {

                String[] columns = new String [] {
                    "ID", "First Name", "Last Name", "Phone", "Address", "Email", "Username", "Password", "Active", "Employee", "Admin", "Customer", "Collateral", "Loan"
                };

                Manager manager = new Manager();
                //List<Customer> allCustomers = manager.getCustomers();

                DefaultTableModel model = (DefaultTableModel)customerDetails.getModel();
                model.setColumnIdentifiers(columns);

                

                Customer customer = new Customer();
                for (Customer cust : manager.getCustomers()) {
                    if (cust.getIsActive() == 1 && cust.getHasLoan() == 1) {
                        customer = cust;
                        System.out.println("Inside Gunner");
                        break;
                    }
                }

                if (customer.getApprovedLoans() != null) {
                    for (ApprovedLoan approvedLoan : customer.getApprovedLoans()) {
                        if(approvedLoan.getRemainingLoanAmount() > 0){
                            System.out.println("Poor customer");


                            int ID = customer.getId();
                            String firstName = customer.getFirstName();
                            String lastName = customer.getLastName();
                            String userName = customer.getUserName();
                            String password = "********";
                            String email = customer.getEmail();
                            String mobile = customer.getPhone();
                            int isActive = customer.getIsActive();
                            int isEmployee = customer.getIsEmployee();
                            int isAdmin = customer.getIsAdmin();

                            int hasCollateral = customer.getHasCollateral();
                            int hasLoan = customer.getHasLoan();
                            int isCustomer = customer.getIsCustomer();

                            String address = customer.getAddress();

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

                        }
                    }
                }
                else {
                System.out.println("Something went wrong. Please try again.");
                }
            } else {
                System.out.println("You are not authorized to perform this action");
            }
        } else {
            System.out.println("Please login first");
        }
    }

    public void addAccountData(){

        String[] columns = new String [] {
            "Customer ID", "Account Number", "Account Type", "Balance", "Active",
        };

        Manager manager = new Manager();

        DefaultTableModel userModel = (DefaultTableModel)customerDetails.getModel();
        int selectedUser = customerDetails.getSelectedRow();
        int selectedCustomerId = Integer.parseInt(userModel.getValueAt(selectedUser, 0).toString());

        DefaultTableModel model = (DefaultTableModel)accountsTable.getModel();
        model.setColumnIdentifiers(columns);

        //-----------------View Account Detail-----------------------//

        if (manager.getAccounts() != null) {
            for (Account account : manager.getAccounts()) {
                if ((account.getCustomerId() == selectedCustomerId) 
                    && 
                    !account.getAccountType().equals(Data.AccountTypes.LOAN.toString())) {
                    System.out.println("Pranesh");
                    System.out.println(account.getCustomerId());
                    System.out.println(account.getAccountType());
                    System.out.println(account.getAccountBalance());
                    System.out.println(account.getAccountNumber());
                    System.out.println(account.getAccountStatus());

                    Vector<Object> vector = new Vector<>();
                    vector.add(account.getCustomerId());
                    vector.add(account.getAccountNumber());
                    vector.add(account.getAccountType());
                    vector.add(account.getAccountBalance());
                    vector.add(account.getAccountStatus());

                    
                    model.addRow(vector);
                }
            }
        }
        
        //-----------------View Account Detail-----------------------//

    }

    public void addTransactionData(){

        String[] columns = new String [] {
            "Transaction ID", "Account Number", "Initiating Customer ID", "Transaction Type", "Debit Amount", "Credit Amount", "Transaction Status"
        };

        Manager manager = new Manager();

        DefaultTableModel userModel = (DefaultTableModel)customerDetails.getModel();
        int selectedUser = customerDetails.getSelectedRow();
        int selectedCustomerId = Integer.parseInt(userModel.getValueAt(selectedUser, 0).toString());


        DefaultTableModel modelTransaction = (DefaultTableModel)transactionDetails.getModel();
        modelTransaction.setColumnIdentifiers(columns);
        modelTransaction.setRowCount(0);

        //-----------------View Transaction Detail-----------------------//

        if (loggedInUserGlobal != null) {
            System.out.println("Enter Account Details");
            System.out.print("Account Number: ");
            //find account in manager accounts
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            if (manager.getAccounts() != null && manager.getAccounts().size() > 0) {

                for (AccountSavings accountSavings : manager.getSavingsAccounts()) {
                    if ((accountSavings.getCustomerId() == selectedCustomerId)) {
                        //view account transactions
                        System.out.println("Account Transactions: ");
                        if(accountSavings.getTransactions() != null &&
                                accountSavings.getTransactions().size() > 0) {
                            //accountSavings.viewTransactions();

                            for(int i=0; i<accountSavings.getTransactions().size(); i++){

                                Vector<Object> vector = new Vector<>();
                                vector.add(accountSavings.getTransactions().get(i).getTransactionId());
                                vector.add(accountSavings.getTransactions().get(i).getAccountNumber());
                                vector.add(accountSavings.getTransactions().get(i).getCustomerId());
                                vector.add(accountSavings.getTransactions().get(i).getTransactionType());
                                vector.add(accountSavings.getTransactions().get(i).getDebitAmount());
                                vector.add(accountSavings.getTransactions().get(i).getCreditAmount());
                                vector.add(accountSavings.getTransactions().get(i).getTransactionStatus());

                                modelTransaction.addRow(vector);
                            }

                        } else {
                            System.out.println("No Transactions Found");
                        }

                    }
                }
                for (AccountChecking checkingAccount : manager.getCheckingAccounts()) {
                    if ((checkingAccount.getCustomerId() == selectedCustomerId)) {
                        //view account transactions
                        System.out.println("Account Transactions: ");
                        if(checkingAccount.getTransactions()!=null &&
                                checkingAccount.getTransactions().size()>0) {
                            //checkingAccount.viewTransactions();

                            for(int i=0; i<checkingAccount.getTransactions().size(); i++){

                                Vector<Object> vector = new Vector<>();
                                vector.add(checkingAccount.getTransactions().get(i).getTransactionId());
                                vector.add(checkingAccount.getTransactions().get(i).getAccountNumber());
                                vector.add(checkingAccount.getTransactions().get(i).getCustomerId());
                                vector.add(checkingAccount.getTransactions().get(i).getTransactionType());
                                vector.add(checkingAccount.getTransactions().get(i).getDebitAmount());
                                vector.add(checkingAccount.getTransactions().get(i).getCreditAmount());
                                vector.add(checkingAccount.getTransactions().get(i).getTransactionStatus());

                                modelTransaction.addRow(vector);
                            }

                        } else{
                            System.out.println("No Transactions Found");
                        }
                    }
                }

            } else {
                System.out.println("No Such Account Found");
            }
        } else {
            System.out.println("Please login first");
        }
        //-----------------View Transaction Detail-----------------------//

    }


    public void addLoanData(){

        String[] columns = new String [] {
            "Loan ID", "Customer ID", "Account Number", "Sanctioned Amount", "Loan Interest", "Loan Interest Unit", "Remaining Loan Amount", "Duration", "Monthly Payment Amount"
        };

        DefaultTableModel userModel = (DefaultTableModel)customerDetails.getModel();
        int selectedUser = customerDetails.getSelectedRow();
        int selectedCustomerId = Integer.parseInt(userModel.getValueAt(selectedUser, 0).toString());

        DefaultTableModel model = (DefaultTableModel)loanTable.getModel();
        model.setColumnIdentifiers(columns);

        if (loggedInUserGlobal != null) {
            System.out.println("Enter Loan Details");
            //find loan in manager loans

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());

            Customer customer = new Customer();
            for (Customer cust : manager.getCustomers()) {
                if (cust.getId() ==  selectedCustomerId) {
                    customer = cust;
                    System.out.println("Inside Gunner");
                    break;
                }
            }
            if (customer.getApprovedLoans() != null) {
                for (ApprovedLoan approvedLoan : customer.getApprovedLoans()) {
                    if(approvedLoan.getCustomerId() == selectedCustomerId){
                        

                        Vector<Object> vector = new Vector<>();
                        vector.add(approvedLoan.getLoanId());
                        vector.add(approvedLoan.getCustomerId());
                        vector.add(approvedLoan.getAccountNumber());
                        vector.add(approvedLoan.getSanctionedAmount());
                        vector.add(approvedLoan.getLoanInterestRate());
                        vector.add(approvedLoan.getLoanInterestUnit());
                        vector.add(approvedLoan.getRemainingLoanAmount());
                        vector.add(approvedLoan.getLoanDurationInMonths());
                        vector.add(approvedLoan.getMonthlyPaymentAmount());

                        model.addRow(vector);

                    }
                }
                manager.loadAllData();
                loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            } else {
                System.out.println("No Such Loan Found");
            }
        }
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
