package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Updated Host to aws-1-ap-south-1 and Port to 5432
        String connectionUrl = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres.qybcpqnblziueklhpqyt", "OOP2508IT@@");
        } catch (Exception e) {
            System.out.println("Connection Failed! Error: " + e.getMessage());
            return null;
        }
    }
}