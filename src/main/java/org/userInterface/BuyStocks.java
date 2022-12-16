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

import org.backend.controllers.manager.LoadStockTransactions;
import org.backend.controllers.user.StockTransaction;
import org.backend.models.Account;
import org.backend.models.AccountNewSecurity;
import org.backend.models.Manager;
import org.backend.models.Stock;
import org.backend.models.User;
import org.backend.staticdata.Data;
import org.backend.staticdata.Data.AccountTypes;

/**
 *
 * @author praneshjayasundar
 */
public class BuyStocks extends javax.swing.JDialog {

    static User loggedInUserGlobal;
    /**
     * Creates new form TransferFunds
     */
    public BuyStocks(javax.swing.JDialog parent, boolean modal, User loggedInUser) {
        super(parent, modal);
        loggedInUserGlobal = loggedInUser;
        initComponents();
        setLocationRelativeTo(null);
        addStockData();
        addAccountData();
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
        accountNumber = new javax.swing.JLabel();
        withdrawFunds = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        openTableData = new javax.swing.JTable();
        enterAmount = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockTableData = new javax.swing.JTable();
        quantity = new javax.swing.JLabel();
        quantityValue = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        accountsTableData = new javax.swing.JTable();
        accountNumber3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        formTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        formTitle.setText("Buy Stocks");

        accountNumber.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        accountNumber.setText("Stock List");

        withdrawFunds.setText("Buy");
        withdrawFunds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    withdrawFundsActionPerformed(evt);
                } catch (SQLException | InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        openTableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Stock ID", "Account Number", "Quantity", "Status","Buy Price", "Buy Amount", "Transaction Date"
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
        jScrollPane2.setViewportView(openTableData);
        openTableData.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        enterAmount.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        enterAmount.setText("Open Positions");

        stockTableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Stock Name", "Current Price", "Tradable", "Last Update Price"
             }
         ) {
             Class[] types = new Class [] {
                 java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
             };
             boolean[] canEdit = new boolean [] {
                 true, true, true, true, false
             };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(stockTableData);
        stockTableData.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        quantity.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        quantity.setText("Quantity");

        quantityValue.setColumns(15);

        accountsTableData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(accountsTableData);

        accountNumber3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        accountNumber3.setText("Account Number");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(formTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(enterAmount)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane3)
                        .addComponent(accountNumber)
                        .addComponent(accountNumber3)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(quantity)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(quantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(withdrawFunds)))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(formTitle)
                .addGap(18, 18, 18)
                .addComponent(accountNumber)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(accountNumber3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quantity)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(withdrawFunds))
                .addGap(46, 46, 46)
                .addComponent(enterAmount)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void withdrawFundsActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, InterruptedException, IOException {                                              
        if (loggedInUserGlobal != null) {
            boolean buyStockSuccess = false;
            System.out.println("Enter Stock Details");
            
            //int stockId = Integer.parseInt(br.readLine());
            System.out.print("Quantity: ");
            //int quantity = Integer.parseInt(br.readLine());
            
            //String accountNumber = br.readLine();

            if(accountsTableData.getSelectionModel().isSelectionEmpty()){
                System.out.println("Please select an account");
                JOptionPane.showMessageDialog(null, "Please select an account");
                return;
            }

            if(stockTableData.getSelectionModel().isSelectionEmpty()){
                System.out.println("Please select a share");
                JOptionPane.showMessageDialog(null, "Please select a share");
                return;
            }

            DefaultTableModel accountModel = (DefaultTableModel)accountsTableData.getModel();
            int selectedAcccountNumber = accountsTableData.getSelectedRow();
            String accountNumber = accountModel.getValueAt(selectedAcccountNumber, 1).toString();
            System.out.println("Account Number: "+accountNumber);

            DefaultTableModel stockModel = (DefaultTableModel)stockTableData.getModel();
            int stockIdSelected = stockTableData.getSelectedRow();
            int stockId = Integer.parseInt(stockModel.getValueAt(stockIdSelected, 0).toString());

            System.out.println("Stock Id: "+stockId);

            //find account in manager accounts

            int quantity = Integer.parseInt(quantityValue.getText().toString());

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            //find stock in manager buyableStocks
            for (Stock buyableStock : manager.getBuyableStocks()) {
                if (buyableStock.getStockId() == stockId) {
                    //find account in manager accounts
                    for (Account account : manager.getAccounts()) {
                        if (account instanceof AccountNewSecurity) {
                            if (account.getAccountNumber().equals(accountNumber)) {
                                //buy stock
                                if (account.getAccountBalance() >= (buyableStock.getCurrentPrice() * quantity)) {
                                    buyStockSuccess = ((AccountNewSecurity) account).buyStock(buyableStock,
                                            account.getAccountNumber(), quantity, loggedInUserGlobal);
                                    if (buyStockSuccess) {
                                        System.out.println("Stock Bought");
                                        JOptionPane.showMessageDialog(null, "Stock Bought: "+buyableStock.getStockName());

                                        DefaultTableModel model = (DefaultTableModel)openTableData.getModel();
                                        model.setRowCount(0);
                                        addOpenPositions();
                                    } else {
                                        System.out.println("Something went wrong. Stock Buying Failed.");
                                        JOptionPane.showMessageDialog(null, "Something went wrong. Stock Buying Failed.");
                                        addOpenPositions();
                                    }
                                    break;

                                }
                                else {
                                    System.out.println("Insufficient Funds");
                                    JOptionPane.showMessageDialog(null, "Insufficient Funds");
                                }
                            }
                        }
                    }
                }
            }
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
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
            java.util.logging.Logger.getLogger(BuyStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyStocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuyStocks dialog = new BuyStocks(new javax.swing.JDialog(), true, loggedInUserGlobal);
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
    private javax.swing.JLabel accountNumber1;
    private javax.swing.JLabel accountNumber2;
    private javax.swing.JLabel accountNumber3;
    private javax.swing.JTable openTableData;
    private javax.swing.JTable stockTableData;
    private javax.swing.JTable accountsTableData;
    private javax.swing.JLabel enterAmount;
    private javax.swing.JLabel formTitle;
    private javax.swing.JLabel formTitle1;
    private javax.swing.JLabel formTitle2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel quantity;
    private javax.swing.JTextField quantityValue;
    private javax.swing.JButton withdrawFunds;
    private javax.swing.JButton withdrawFunds1;
    private javax.swing.JButton withdrawFunds2;
    // End of variables declaration
    
    public void addStockData(){
        if (loggedInUserGlobal != null) {
            //boolean updateStocksSuccess = false;
            System.out.println("Enter Stock Details");
            System.out.print("Stock Id: ");
            
            
            String[] columns = new String [] {
               "Stock ID", "Stock Name", "Current Price", "Tradable", "Last Update Price"
            };

            DefaultTableModel model = (DefaultTableModel)stockTableData.getModel();
            model.setColumnIdentifiers(columns);
            
            //find stock in manager stocks

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            for (Stock stock : manager.getStocks()) {
                //update stock
                if(stock.getTradable() == 1){
                    Vector<Object> vector = new Vector<>();
                    vector.add(stock.getStockId());
                    vector.add(stock.getStockName());
                    vector.add(stock.getCurrentPrice());
                    vector.add(stock.getTradable());
                    vector.add(stock.getStockPriceUpdationDate().toString());
                    
                model.addRow(vector);
                }
                
            }
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
        } else {
            System.out.println("Please login first");
        }
    }

    public void addAccountData(){

        //add senders account data
        if (loggedInUserGlobal != null) {

            String[] columns = new String [] {
                "Customer ID", "Account Number", "Account Type", "Balance"
            };

            DefaultTableModel model = (DefaultTableModel)accountsTableData.getModel();
            model.setColumnIdentifiers(columns);

            Manager manager = new Manager();

            //find account in manager accounts
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            if (manager.getAccounts() != null) {
                for (Account account : manager.getAccounts()) {
                    if ((account.getCustomerId() == loggedInUserGlobal.getId()) 
                        &&
                        account.getAccountStatus() == 1
                        &&
                        account.getAccountType().equals(Data.AccountTypes.NEW_SECURITY.toString())) {

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

    public void addOpenPositions(){
        if (loggedInUserGlobal != null) {
            //boolean updateStocksSuccess = false;
            System.out.println("Enter Stock Details");
            System.out.print("Stock Id: ");
            
            
            String[] columns = new String [] {
                "Transaction ID", "Stock ID", "Account Number", "Quantity", "Status","Buy Price", "Buy Amount", "Transaction Date"
            };

            DefaultTableModel model = (DefaultTableModel)openTableData.getModel();
            //model.setRowCount(0);
            model.setColumnIdentifiers(columns);
            
            //find stock in manager stocks

            Manager manager = new Manager();
            manager.loadAllData();
            loggedInUserGlobal = manager.getLoggedInUser(loggedInUserGlobal.getId());
            for (StockTransaction stockTransaction : manager.getStockTransactions()) {
                //update stock
                if(stockTransaction.getCustomerId() == loggedInUserGlobal.getId() && stockTransaction.getSold_flag() == 0){

                    //Double buyPrice = stockTransaction.getBuyPrice()/stockTransaction.getQuantity();
                    Vector<Object> vector = new Vector<>();
                    vector.add(stockTransaction.getStockTransactionId());
                    vector.add(stockTransaction.getStockId());
                    vector.add(stockTransaction.getAccountNumber());
                    vector.add(stockTransaction.getQuantity());
                    vector.add(stockTransaction.getStatus());
                    //vector.add(buyPrice);
                    vector.add(stockTransaction.getBuyPrice());
                    vector.add(stockTransaction.getBuyPrice()*stockTransaction.getQuantity());
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
