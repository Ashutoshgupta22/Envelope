package com.aspark.envelope.servlet;

import java.io.*;
import java.sql.SQLException;

import com.aspark.envelope.UserAuthentication;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    String userName,password;
    RequestDispatcher dispatcher;
    UserAuthentication userAuthentication;

    public void init() {

        try {

            userAuthentication = new UserAuthentication();

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("LoginServlet doGet called");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        userName = request.getParameter("username");
        password = request.getParameter("login_password");

        try {

            if (userAuthentication.ifUserExists(userName)) {

                System.out.println("user EXISTS");

                if (userAuthentication.ifPasswordMatches(userName, password)) {

                    System.out.println("Welcome back!");

                    dispatcher = request.getRequestDispatcher("home/home.html");
                    dispatcher.forward(request, response);
                } else {

                    out.println("Username or password incorrect");
                    dispatcher = request.getRequestDispatcher("login.html");
                    dispatcher.include(request, response);
                }
            } else {

                out.println("User does not exist");
                dispatcher = request.getRequestDispatcher("login.html");
                dispatcher.include(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        doGet(req,resp);
    }

    public void destroy() {
    }
}