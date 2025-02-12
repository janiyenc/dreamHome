package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dreamhome"; // Database URL
    private static final String USER = "rasid"; // Replace with your username
    private static final String PASSWORD = "putanginamo"; // Replace with your password

    // Method to establish and return the database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC Driver (not required in recent JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found. Add the library to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed. Check the URL, username, or password.");
            e.printStackTrace();
        }
        return connection;
    }

    // Main method for testing the connection
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {  // Using try-with-resources for auto-close
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to establish or close the connection.");
            e.printStackTrace();
        }
    }
}
