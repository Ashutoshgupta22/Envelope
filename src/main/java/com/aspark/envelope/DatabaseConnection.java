package com.aspark.envelope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection initializeDatabase() throws ClassNotFoundException, SQLException {

        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql:// localhost:3306/";

        String dbName = "envelope";
        String dbUsername = "root";
        String dbPassword = "database";

        Class.forName(dbDriver);

        Connection connection = DriverManager.getConnection(dbUrl+dbName,dbUsername,dbPassword);

        return connection;

    }
}
