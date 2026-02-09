package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDatabase implements Database {

    private static PostgresDatabase instance;

    private PostgresDatabase() {}

    public static PostgresDatabase getInstance() {
        if (instance == null) {
            instance = new PostgresDatabase();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            DatabaseConfig config = DatabaseConfig.getInstance();
            return DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed");
        }
    }

}
