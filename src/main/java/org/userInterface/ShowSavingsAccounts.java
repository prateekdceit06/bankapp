/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.controllers.account.Transfer;
import org.backend.models.Account;
import org.backend.models.Manager;
import org.backend.models.User;
import org.backend.staticdata.Data;

/**
 *
 * @author praneshjayasundar
 */
public class ShowSavingsAccounts extends javax.swing.JDialog {

    // Variables declaration - do not modify                     
    private javax.swing.JLabel accountDetails;
    private javax.swing.JButton close;
    private javax.swing.JPanel content;
    private javax.swing.JLabel formTitle;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable savingAccounts;
    //private javax.swing.JButton update;
    // End of variables declaration 

    static User loggedInUserGlobal;
    
    /**
     * Creates new form GetUserByUserID
     */
    public ShowSavingsAccounts(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
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
        //update = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        savingAccounts = new javax.swing.JTable();
        accountDetails = new javax.swing.JLabel();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        content.setBackground(new java.awt.Color(255, 255, 255));

        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("Savings Accounts Information");

        // update.setText("Update");
        // update.addActionListener(new java.awt.event.ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         updateActionPerformed(evt);
        //     }
        // });

        savingAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Account Number", "Account Type", "Balance", "Active"
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

        savingAccounts.setColumnSelectionAllowed(true);
        jScrollPane4.setViewportView(savingAccounts);
        savingAccounts.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        accountDetails.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        accountDetails.setText("Account Details");

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(formTitle))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountDetails)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(459, 459, 459)
                        //.addComponent(update)
                        .addGap(60, 60, 60)
                        .addComponent(close)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(formTitle)
                .addGap(29, 29, 29)
                .addComponent(accountDetails)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    //.addComponent(update)
                    .addComponent(close))
                .addContainerGap(51, Short.MAX_VALUE))
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

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {                                       
    }                                      

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {                                      
        if (loggedInUserGlobal != null) {
            System.out.println("Enter Account Details");
            System.out.print("Account Number: ");
        
            DefaultTableModel model =(DefaultTableModel) savingAccounts.getModel();
            int selectedRow = savingAccounts.getSelectedRow();

            String accountNumber = model.getValueAt(selectedRow, 1).toString();

            System.out.println(selectedRow);
        
            System.out.println("Account Number selected: "+accountNumber);
        
            Manager manager = new Manager();
        
            //find account in manager accounts
            if (manager.getAccounts() != null) {
                for (Account account : manager.getAccounts()) {
                    if ((account.getCustomerId() == loggedInUserGlobal.getId() ||
                    loggedInUserGlobal.getIsAdmin() == 1) &&
                            account.getAccountNumber().equals(accountNumber) &&
                            account.getAccountStatus() == 1) {
                        //close account
                        boolean closeAccountSuccess = account.closeAccount();
                        if (closeAccountSuccess) {
                            System.out.println("Account closed successfully");
                            JOptionPane.showMessageDialog(null, "Account closed successfully");
                            if(account.getAccountType().equals(Data.AccountTypes.CHECKING.toString())){
                                Transfer transfer = new Transfer();
                                transfer.transfer(Data.accountClosingFees,
                                        0,
                                        account.getAccountNumber(), Data.TransactionTypes.ACCOUNT_CLOSING_FEE.toString(),
                                        manager.getBankAccountNumber(), loggedInUserGlobal);
                            }
                        }
                    }
                }
                manager.loadAllData();
                loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            } else {
                System.out.println("You are not authorized to perform this action");
                JOptionPane.showMessageDialog(null, "You are not authorized to perform this action");
            }
        } else {
            System.out.println("Please login first");
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
            java.util.logging.Logger.getLogger(ShowSavingsAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowSavingsAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowSavingsAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowSavingsAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShowSavingsAccounts dialog = new ShowSavingsAccounts(new javax.swing.JDialog(), true, loggedInUserGlobal);
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

    public void addData(){
        if (loggedInUserGlobal != null) {

            String[] columns = new String [] {
                "Customer ID", "Account Number", "Account Type", "Balance", "Active"
            };

            DefaultTableModel model = (DefaultTableModel)savingAccounts.getModel();
            model.setColumnIdentifiers(columns);

            Manager manager = new Manager();
            System.out.println("Enter Account Details");
            System.out.print("Account Number: ");
            String accountType = "SAVINGS";
            //find account in manager accounts
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            if (manager.getAccounts() != null) {
                for (Account account : manager.getAccounts()) {
                    if ((account.getCustomerId() == loggedInUserGlobal.getId() || loggedInUserGlobal.getIsAdmin() == 1) 
                        &&
                        account.getAccountType().equals(accountType)) {

                        System.out.println(account.getAccountType());
                        System.out.println(account.getAccountBalance());
                        System.out.println(account.getAccountNumber());
                        System.out.println(account.getCustomerId());
                        System.out.println(account.getAccountStatus());

                        //System.out.println(account);

                        Vector<Object> vector = new Vector<>();
                        vector.add(account.getCustomerId());
                        vector.add(account.getAccountNumber());
                        vector.add(account.getAccountType());
                        vector.add((Double)account.getAccountBalance());
                        vector.add((int)account.getAccountStatus());

                        model.addRow(vector);
                    }
                }
            } else {
                System.out.println("No Such Account Found");
            }
        } else {
            System.out.println("Please login first");
        }
    }
}
