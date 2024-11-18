package com.example.sdaprojectsocialmediaapp.utils;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/fastbook";  // Change to your DB name
    private static final String USER = "root";  // Change to your DB username
    private static final String PASSWORD = "amna308";  // Change to your DB password

    // Method to establish a connection
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Step 1: Load the MySQL JDBC driver (optional, not required in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful!");

        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
