package com.aspark.envelope.servlet;

import com.aspark.envelope.DatabaseConnection;
import com.aspark.envelope.DatabaseQuery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "signupServlet", value = "/signup/signup")
public class SignupServlet extends HttpServlet {

    private String name;
    private String username;
    private String password;
    DatabaseQuery databaseQuery;
    RequestDispatcher requestDispatcher;

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("signup servlet doGet called");

        name = request.getParameter("name");
        username = request.getParameter("username");
        password = request.getParameter("password");

        try {

            databaseQuery = new DatabaseQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {

            boolean check = databaseQuery.ifUserExists(username);
            System.out.println("if " + username +" already exist = "+ check);

            if ( check) {

                out.println("Username already exists");
                requestDispatcher = request.getRequestDispatcher("/signup/signup.html");
                requestDispatcher.include(request, response);

                databaseQuery.closeConnections();

                return;
            }

        }   catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }

        try {
                Connection connection = DatabaseConnection.initializeDatabase();

                PreparedStatement preparedStatement = connection
                        .prepareStatement("insert into user(name,username,password) values(?,?,?)");

                // make id column as primary key,auto increment in table

                preparedStatement.setString(1,request.getParameter("name"));
                preparedStatement.setString(2,request.getParameter("username"));
                preparedStatement.setString(3,request.getParameter("confirm_password"));

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                System.out.println("Database updated");

    //            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
    //            requestDispatcher.forward(request,response);
                response.sendRedirect("/envelope/login.html");


            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doGet(request,response);
    }
}
