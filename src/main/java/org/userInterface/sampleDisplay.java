package org.userInterface;
import javax.swing.*;
import javax.swing.table.*;

public class sampleDisplay extends JFrame {
    // The table that will display the data
    JTable table;

    // The constructor
    public sampleDisplay() {
        // Set the frame title
        setTitle("Swing UI Table Example");

        // Create the table
        table = new JTable();

        // Set the table model
        table.setModel(new DefaultTableModel(getData(), getColumnNames()));

        // Add the table to the frame
        getContentPane().add(new JScrollPane(table));

        // Set the size of the frame
        setSize(500, 300);

        // Show the frame
        setVisible(true);
    }

    // Returns the data for the table
    private Object[][] getData() {
        // TODO: Query the database and return the data
        Object[][] data = {{"John", "Doe", "johndoe@gmail.com"}, {"Jane", "Doe", "janedoe@gmail.com"}};
        return data;
    }

    // Returns the column names for the table
    private String[] getColumnNames() {
        String[] columns = {"First Name", "Last Name", "Email"};
        return columns;
    }

    // The main method
    public static void main(String[] args) {
        // Create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new sampleDisplay();
            }
        });
    }
}
