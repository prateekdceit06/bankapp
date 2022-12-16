package org;

import org.userInterface.MainMenu;
import org.tests.testConnection;

import java.sql.SQLException;

/**
 * This is the main class for the program. It is used to launch the program.
 * It also tests all databases are maintained correctly.
 */
public class Launcher {
    MainMenu menu = new MainMenu();
    testConnection test = new testConnection();

    public static void main(String[] args) throws SQLException {
        Launcher launcher = new Launcher();
        boolean testPass = launcher.test.testAll("jdbc:sqlite:src/bank.db");
        System.out.println("Starting application");
        System.out.println("Database test: " + testPass);
        if (testPass) {
            MainMenu.main(args);
        } else {
            System.out.println("Database test failed, please check database");
        }
    }
}
