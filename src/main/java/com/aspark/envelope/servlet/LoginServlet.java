package com.aspark.envelope.servlet;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    String userName,password;
    RequestDispatcher dispatcher;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("LoginServlet doGet called");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        userName = request.getParameter("username");
        password = request.getParameter("login_password");

        if (password.equals("qwe")) {

            dispatcher = request.getRequestDispatcher("home/home.html");
            dispatcher.forward(request,response);
        }
        else {

            out.println("Username or password incorrect");
            dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.include(request,response);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        doGet(req,resp);
    }

    public void destroy() {
    }
}