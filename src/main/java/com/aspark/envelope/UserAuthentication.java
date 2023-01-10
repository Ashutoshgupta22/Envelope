package com.aspark.envelope;

import java.sql.SQLException;

public class UserAuthentication {

    DatabaseQuery databaseQuery;

    public UserAuthentication() throws SQLException, ClassNotFoundException {

        databaseQuery = new DatabaseQuery();
    }

    public boolean ifUserExists(String username) throws SQLException {

       return databaseQuery.ifUserExists(username);
    }

    public boolean ifPasswordMatches(String username, String password) throws SQLException {

        return databaseQuery.ifPasswordMatches(username,password);
    }
}
