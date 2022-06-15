package com.lab221.lab221asm6.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /**
     * database url.
     */
    static final String DB_URL = "jdbc:postgresql://localhost:5432/lab221asm6";
    /**
     * database username.
     */
    static final String DB_USERNAME = "lab221";
    /**
     * database password.
     */
    static final String DB_PASSWORD = "lab221";

    /**
     * database connection.
     */
    private static Connection connection;

    /**
     * get database connection instance.
     * @return a singleton object of {@link Connection}
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            if (connection == null) {
                DatabaseConnection.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
