package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    public String role;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");

        if ("doctor".equals(role)) {
            // Forward to patient.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("doctor.jsp");
            dispatcher.forward(request, response);
        } else if ("patient".equals(role)) {
            // Forward to patient.jsp or any other page for patients
            RequestDispatcher dispatcher = request.getRequestDispatcher("patient.jsp");
            dispatcher.forward(request, response);
        } else {
            // Handle other roles or invalid cases
            response.getWriter().println("Invalid role");
        }
    }
}


