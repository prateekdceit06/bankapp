/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.models.Account;
import org.backend.models.AccountChecking;
import org.backend.models.AccountSavings;
import org.backend.models.Manager;
import org.backend.models.User;
import org.backend.music.Music;

/**
 *
 * A GUI for customers to deposit funds.
 */
public class DepositFunds extends javax.swing.JDialog {

    static User loggedInUserGlobal;
    /**
     * Creates new form TransferFunds
     */
    public DepositFunds(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
        addDepositData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        currencyGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        formTitle = new javax.swing.JLabel();
        accountNumber = new javax.swing.JLabel();
        depositFunds = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        enterAmount = new javax.swing.JLabel();
        depositAmount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        usd = new javax.swing.JRadioButton();
        euro = new javax.swing.JRadioButton();
        inr = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("Deposit Funds");

        accountNumber.setText("Account Number");

        depositFunds.setBackground(new java.awt.Color(204, 204, 204));
        depositFunds.setText("Deposit");
        depositFunds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    depositFundsActionPerformed(evt);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        accountTable.setBackground(new java.awt.Color(204, 204, 204));
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Account Number", "Account Type", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(accountTable);
        accountTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        enterAmount.setText("Enter Amount");

        depositAmount.setBackground(new java.awt.Color(204, 204, 204));
        depositAmount.setColumns(15);

        jLabel1.setText("Currency");

        currencyGroup.add(usd);
        usd.setText("USD");

        currencyGroup.add(euro);
        euro.setText("EUR");

        currencyGroup.add(inr);
        inr.setText("INR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formTitle)
                .addGap(344, 344, 344))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountNumber)
                    .addComponent(enterAmount)
                    .addComponent(jLabel1))
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(usd)
                        .addGap(18, 18, 18)
                        .addComponent(euro)
                        .addGap(18, 18, 18)
                        .addComponent(inr))
                    .addComponent(depositFunds)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depositAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(formTitle)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountNumber)
                                .addGap(60, 60, 60)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterAmount)
                            .addComponent(depositAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usd)
                            .addComponent(euro)
                            .addComponent(inr))))
                .addGap(46, 46, 46)
                .addComponent(depositFunds)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                         

    private void depositFundsActionPerformed(java.awt.event.ActionEvent evt) throws LineUnavailableException, IOException, UnsupportedAudioFileException { 
        
        String currencyUnit = "";
        if (loggedInUserGlobal != null) {
            
            Manager manager = new Manager();
            System.out.println("Enter Account Details");
            System.out.print("Account Number: ");

            if(accountTable.getSelectionModel().isSelectionEmpty()){
                System.out.println("Please select an account");
                JOptionPane.showMessageDialog(null, "Please select an account");
                return;
            }

            DefaultTableModel senderModel = (DefaultTableModel)accountTable.getModel();
            int selectedAccount1 = accountTable.getSelectedRow();
            String accountNumber = senderModel.getValueAt(selectedAccount1, 1).toString();

            if(!isDouble(depositAmount.getText().toString())){
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
                return;
            }

            if(usd.isSelected()){
                System.out.println("Currency: USD");
                currencyUnit = usd.getText().toString();
            }
            else if(euro.isSelected()){
                System.out.println("Currency: EUR");
                currencyUnit = euro.getText().toString();
            }
            else if(inr.isSelected()){
                System.out.println("Currency: INR");
                currencyUnit = inr.getText().toString();
            }
            else{
                System.out.println("Currency: Null");
                JOptionPane.showMessageDialog(null, "Please select a currency");
                return;
            }

            double amount = Double.parseDouble(depositAmount.getText());

            //find account in manager accounts
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            if (manager.getAccounts() != null && manager.getAccounts().size() > 0
                    && manager.getAccountNumbers().contains(accountNumber)) {
                for (AccountSavings accountSavings : manager.getSavingsAccounts()) {
                    if (accountSavings.getCustomerId() == loggedInUserGlobal.getId() &&
                            accountSavings.getAccountNumber().equals(accountNumber)) {
                        //deposit money
                        boolean depositMoneySuccess = accountSavings.deposit(amount, accountNumber, loggedInUserGlobal, currencyUnit);
                        if (depositMoneySuccess) {
                            System.out.println("Money deposited successfully");
                            Music.playSound("src/main/java/org/backend/music/success.wav");
                            JOptionPane.showMessageDialog(null, "Money deposited successfully");
                        } else {
                            System.out.println("Something went wrong. Money deposit failed.");
                            Music.playSound("src/main/java/org/backend/music/invalid.wav");
                            JOptionPane.showMessageDialog(null, "Something went wrong. Money deposit failed.");
                        }
                    }
                }
                for (AccountChecking checkingAccount : manager.getCheckingAccounts()) {
                    if (checkingAccount.getCustomerId() == loggedInUserGlobal.getId() &&
                            checkingAccount.getAccountNumber().equals(accountNumber)) {
                        //deposit money
                        boolean depositMoneySuccess = checkingAccount.deposit(amount, accountNumber,
                                manager.getBankAccountNumber(), loggedInUserGlobal, currencyUnit);
                        if (depositMoneySuccess) {
                            System.out.println("Money deposited successfully");
                            Music.playSound("src/main/java/org/backend/music/success.wav");
                            JOptionPane.showMessageDialog(null, "Money deposited successfully");
                        } else {
                            System.out.println("Something went wrong. Money deposit failed.");
                            Music.playSound("src/main/java/org/backend/music/invalid.wav");
                            JOptionPane.showMessageDialog(null, "Something went wrong. Money deposit failed.");
                        }
                    }
                }
                manager.loadAllData();
                loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            } else {
                System.out.println("No Such Account Found");
                JOptionPane.showMessageDialog(null, "No Such Account Found");
            }
        } else {
            System.out.println("Please login first");
        }
        this.dispose();
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
            java.util.logging.Logger.getLogger(DepositFunds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepositFunds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepositFunds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepositFunds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DepositFunds dialog = new DepositFunds(new javax.swing.JDialog(), true, loggedInUserGlobal);
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
    private javax.swing.JLabel accountNumber;
    private javax.swing.JTable accountTable;
    private javax.swing.ButtonGroup currencyGroup;
    private javax.swing.JTextField depositAmount;
    private javax.swing.JButton depositFunds;
    private javax.swing.JLabel enterAmount;
    private javax.swing.JRadioButton euro;
    private javax.swing.JLabel formTitle;
    private javax.swing.JRadioButton inr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton usd;
    // End of variables declaration
    
    public void addDepositData(){

        //add senders account data
        if (loggedInUserGlobal != null) {

            String[] columns = new String [] {
                "Customer ID", "Account Number", "Account Type", "Balance"
            };

            DefaultTableModel model = (DefaultTableModel)accountTable.getModel();
            model.setColumnIdentifiers(columns);

            Manager manager = new Manager();

            //find account in manager accounts
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            if (manager.getAccounts() != null) {
                for (Account account : manager.getAccounts()) {
                    if ((account.getCustomerId() == loggedInUserGlobal.getId() || loggedInUserGlobal.getIsAdmin() == 1) 
                        &&
                        account.getAccountStatus() == 1) {

                        //System.out.println(account);

                        Vector<Object> vector = new Vector<>();
                        vector.add(account.getCustomerId());
                        vector.add(account.getAccountNumber());
                        vector.add(account.getAccountType());
                        vector.add((Double)account.getAccountBalance());

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

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
