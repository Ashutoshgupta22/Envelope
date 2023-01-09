package com.aspark.envelope.servlet;

import com.aspark.envelope.DatabaseConnection;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("signup servlet doGet called");

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

            System.out.println("Updated database");

//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
//            requestDispatcher.forward(request,response);
            response.sendRedirect("/envelope/login.html");


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
