/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.models.Account;
import org.backend.models.AccountLoan;
import org.backend.models.ApprovedLoan;
import org.backend.models.Customer;
import org.backend.models.Manager;
import org.backend.models.User;

/**
 *
 * @author praneshjayasundar
 */
public class LoanPaymentDeposit extends javax.swing.JDialog {

    static User loggedInUserGlobal;
    /**
     * Creates new form LoanPaymentDeposit
     */
    public LoanPaymentDeposit(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
        addLoanData();
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
        loanAmount = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loanAmountValue = new javax.swing.JTextField();
        pay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        inr = new javax.swing.JRadioButton();
        euro = new javax.swing.JRadioButton();
        usd = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        loanAmount.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Loan Payment By Deposit");

        jLabel2.setText("Pay Loan Amount ");

        loanAmountValue.setColumns(15);

        pay.setText("Pay");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

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
        loanTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(loanTable);
        loanTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel3.setText("Loan Account");

        currencyGroup.add(inr);
        inr.setText("INR");

        currencyGroup.add(euro);
        euro.setText("EUR");

        currencyGroup.add(usd);
        usd.setText("USD");

        jLabel4.setText("Currency");

        javax.swing.GroupLayout loanAmountLayout = new javax.swing.GroupLayout(loanAmount);
        loanAmount.setLayout(loanAmountLayout);
        loanAmountLayout.setHorizontalGroup(
            loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanAmountLayout.createSequentialGroup()
                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loanAmountLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel1))
                    .addGroup(loanAmountLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loanAmountLayout.createSequentialGroup()
                                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(29, 29, 29)
                                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pay)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(loanAmountLayout.createSequentialGroup()
                                            .addComponent(usd)
                                            .addGap(18, 18, 18)
                                            .addComponent(euro)
                                            .addGap(18, 18, 18)
                                            .addComponent(inr))
                                        .addComponent(loanAmountValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel4))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        loanAmountLayout.setVerticalGroup(
            loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanAmountLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(69, 69, 69)
                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loanAmountLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(52, 52, 52)))
                .addGap(55, 55, 55)
                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(loanAmountValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addGroup(loanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usd)
                        .addComponent(euro)
                        .addComponent(inr)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(pay)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void payActionPerformed(java.awt.event.ActionEvent evt) { 
        String currencyUnit = "";                                   
        if (loggedInUserGlobal != null) {
            System.out.println("Enter Loan Details");
            //find loan in manager loans

            DefaultTableModel model = (DefaultTableModel)loanTable.getModel();
            int selectedRow = loanTable.getSelectedRow();
            int loanId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            if(!isDouble(loanAmountValue.getText().toString())){
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

            double amount = Double.parseDouble(loanAmountValue.getText().toString());

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            Customer customer = new Customer();
            for (Customer cust : manager.getCustomers()) {
                if (cust.getId() == loggedInUserGlobal.getId()) {
                    customer = cust;
                    break;
                }

            }
            if (customer.getApprovedLoans() != null) {
                for (ApprovedLoan approvedLoan : customer.getApprovedLoans()) {
                    if (approvedLoan.getLoanId() == loanId) {
                        for (AccountLoan loanAccount : manager.getLoanAccounts()) {
                            if (loanAccount.getAccountNumber().equals(approvedLoan.getAccountNumber())) {
                                //pay loan
                                boolean loanPaid = loanAccount.payLoan(loanId, amount,
                                        manager.getBankAccountNumber(), customer, currencyUnit);
                                if (loanPaid) {
                                    System.out.println("Loan Repayment Paid: "+amount);
                                    JOptionPane.showMessageDialog(null, "Loan Repayment Paid: "+amount);
                                } else {
                                    System.out.println("Something went wrong. Loan Payment Failed.");
                                    JOptionPane.showMessageDialog(null, "Something went wrong. Loan Payment Failed.");
                                }
                                break;
                            }
                        }
                    }
                }
                manager.loadAllData();
                loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            } else {
                System.out.println("No Such Loan Found");
            }

            this.dispose();
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
            java.util.logging.Logger.getLogger(LoanPaymentDeposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoanPaymentDeposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoanPaymentDeposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoanPaymentDeposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoanPaymentDeposit dialog = new LoanPaymentDeposit(new javax.swing.JDialog(), true, loggedInUserGlobal);
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
    private javax.swing.ButtonGroup currencyGroup;
    private javax.swing.JRadioButton euro;
    private javax.swing.JRadioButton inr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel loanAmount;
    private javax.swing.JTextField loanAmountValue;
    private javax.swing.JTable loanTable;
    private javax.swing.JButton pay;
    private javax.swing.JRadioButton usd;
    // End of variables declaration
    
    public void addLoanData(){

        String[] columns = new String [] {
            "Loan ID", "Customer ID", "Account Number", "Sanctioned Amount", "Loan Interest", "Loan Interest Unit", "Remaining Loan Amount", "Duration", "Monthly Payment Amount"
        };

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
                if (cust.getId() == loggedInUserGlobal.getId()) {
                    customer = cust;
                    break;
                }

            }
            if (customer.getApprovedLoans() != null) {
                for (ApprovedLoan approvedLoan : customer.getApprovedLoans()) {
                    if(approvedLoan.getCustomerId() == loggedInUserGlobal.getId()){
                        

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
