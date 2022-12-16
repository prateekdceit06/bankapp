/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.userInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.backend.controllers.user.StockTransaction;
import org.backend.models.Account;
import org.backend.models.AccountNewSecurity;
import org.backend.models.Customer;
import org.backend.models.Manager;
import org.backend.models.Stock;
import org.backend.models.User;
import org.backend.staticdata.Data;

/**
 *
 * @author praneshjayasundar
 */
public class SellStocks extends javax.swing.JDialog {

    static User loggedInUserGlobal;
    /**
     * Creates new form TransferFunds
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    public SellStocks(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
        addOpenPositions();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        formTitle = new javax.swing.JLabel();
        sellStocks = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        enterAmount = new javax.swing.JLabel();
        quantityValue = new javax.swing.JTextField();
        quantity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("Sell Stocks");

        sellStocks.setText("Sell");
        sellStocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellStocksActionPerformed(evt);
 
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Stock ID", "Account Number", "Quantity", "Status", "Buy Price", "Buy Amount", "Transaction Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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
        jScrollPane2.setViewportView(accountTable);
        accountTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        enterAmount.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        enterAmount.setText("Open Positions");

        quantityValue.setColumns(15);

        quantity.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        quantity.setText("Quantity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(formTitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enterAmount)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantity)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(quantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sellStocks)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(formTitle)
                .addGap(54, 54, 54)
                .addComponent(enterAmount)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quantity)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sellStocks))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void sellStocksActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if (loggedInUserGlobal != null) {
            boolean sellStockSuccess = false;
            System.out.println("Enter Stock Details");
            System.out.print("Stock Id: ");
            //int stockId = Integer.parseInt(br.readLine());
            System.out.print("Quantity: ");
            //int quantity = Integer.parseInt(br.readLine());
            System.out.print("Account Number: ");
            //String accountNumber = br.readLine();
            //find account in manager accounts

            if(accountTable.getSelectionModel().isSelectionEmpty()){
                System.out.println("Please select an account");
                JOptionPane.showMessageDialog(null, "Please select an account");
                return;
            }

            DefaultTableModel accountModel = (DefaultTableModel)accountTable.getModel();
            int selectedAcccountNumber = accountTable.getSelectedRow();

            String accountNumber = accountModel.getValueAt(selectedAcccountNumber, 2).toString();
            System.out.println("Account Number: "+accountNumber);
            int stockId = Integer.parseInt(accountModel.getValueAt(selectedAcccountNumber, 1).toString());

            System.out.println("Stock Id: "+stockId);

            int transactionId = Integer.parseInt(accountModel.getValueAt(selectedAcccountNumber, 0).toString());
            //find account in manager accounts

            int quantity = Integer.parseInt(quantityValue.getText().toString());
            System.out.println("Quantity: "+quantity);

            Manager manager = new Manager();
            
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            //find stock in manager buyableStocks
            Customer customer = new Customer();
            for (Customer cust : manager.getCustomers()) {
                if (cust.getId() == loggedInUserGlobal.getId()) {
                    customer = cust;
                    break;
                }
            }
            System.out.println("Pranesh");
            Stock tempStock = null;
            //find stockId in managers stocks
            for (Stock stock : manager.getStocks()) {
                if (stock.getStockId() == stockId) {
                    tempStock = stock;
                    break;
                }
            }

            System.out.println("gunner");
            if (tempStock != null) {
                System.out.println("Pranesh - 1");
                if(customer.getStockCount().containsKey(stockId)
                        && customer.getStockCount().get(stockId) >= quantity) {
                    for (Account account : customer.getAccounts()) {
                        if (account instanceof AccountNewSecurity) {
                            if (account.getAccountNumber().equals(accountNumber)) {
                                //buy stock
                                sellStockSuccess = ((AccountNewSecurity) account).sellStock(tempStock,
                                        account.getAccountNumber(), quantity, loggedInUserGlobal, transactionId);
                                if (sellStockSuccess) {
                                    System.out.println("Stock Sold");
                                    JOptionPane.showMessageDialog(null, "Stock sold: "+ tempStock.getStockName());
                                } else {
                                    System.out.println("Something went wrong. Stock Buying Failed.");
                                    JOptionPane.showMessageDialog(null, "Something went wrong. Stock Buying Failed.");
                                }
                                break;
                            }
                        }
                    }
                }
                else{
                    System.out.println("Something went wrong. Stock Buying Failed.");
                    JOptionPane.showMessageDialog(null, "Please enter the correct quantity of shares to be sold");
                }
            }
            else{
                System.out.println("Something went wrong. Stock Buying Failed.");
                JOptionPane.showMessageDialog(null, "All the shares are already sold");
            }
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
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
            java.util.logging.Logger.getLogger(SellStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SellStocks dialog;
                dialog = new SellStocks(new javax.swing.JDialog(), true, loggedInUserGlobal);
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
    private javax.swing.JTable accountTable;
    private javax.swing.JLabel enterAmount;
    private javax.swing.JLabel formTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel quantity;
    private javax.swing.JTextField quantityValue;
    private javax.swing.JButton sellStocks;

    // End of variables declaration
    
    public void addOpenPositions(){
        if (loggedInUserGlobal != null) {
            //boolean updateStocksSuccess = false;
            System.out.println("Enter Stock Details");
            System.out.print("Stock Id: ");
            
            
            String[] columns = new String [] {
                "Transaction ID", "Stock ID", "Account Number", "Quantity", "Status", "Buy Price", "Buy Amount", "Transaction Date"
            };

            DefaultTableModel model = (DefaultTableModel)accountTable.getModel();
            model.setColumnIdentifiers(columns);
            
            //find stock in manager stocks

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            for (StockTransaction stockTransaction : manager.getStockTransactions()) {
                //update stock
                if(stockTransaction.getCustomerId() == loggedInUserGlobal.getId()
                &&
                !stockTransaction.getStatus().equals(Data.StockStatus.SOLD.toString())){

                    Double buyPrice = stockTransaction.getBuyPrice()/stockTransaction.getQuantity();
                    Vector<Object> vector = new Vector<>();
                    vector.add(stockTransaction.getStockTransactionId());
                    vector.add(stockTransaction.getStockId());
                    vector.add(stockTransaction.getAccountNumber());
                    vector.add(stockTransaction.getQuantity());
                    vector.add(stockTransaction.getStatus());
                    vector.add(buyPrice);
                    vector.add(stockTransaction.getBuyPrice());
                    vector.add(stockTransaction.getTransactionDate().toString());
                    
                    model.addRow(vector);
                }
                
            }
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
        } else {
            System.out.println("Please login first");
        }
    }
}
