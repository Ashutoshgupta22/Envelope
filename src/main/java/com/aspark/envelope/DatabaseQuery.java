package com.aspark.envelope;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {

   private Connection connection;
   private PreparedStatement preparedStatement;
   private ResultSet resultSet;
   private String query;

   public DatabaseQuery() throws SQLException, ClassNotFoundException {

       connection = DatabaseConnection.initializeDatabase();
   }

   public boolean ifUserExists(String username) throws SQLException {

       query = "select username from envelope.user where username = "+
               "'" +
               username +
               "'";

       preparedStatement = connection.prepareStatement(query);
       resultSet = preparedStatement.executeQuery();

       System.out.println("DatabaseQuery checking if user exist");

       return resultSet.next();
   }

   public boolean ifPasswordMatches(String username, String password) throws SQLException {

       query = "select username,password from envelope.user where username = " +
               "'" +
               username +
               "'"+
               " and " +
               "password = " +
               "'" +
               password +
               "'" ;

       resultSet = preparedStatement.executeQuery(query);

      System.out.println("Password matching check");
       return resultSet.next();

   }

   public void closeConnections() {

       try {

           preparedStatement.close();
           connection.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }


}
