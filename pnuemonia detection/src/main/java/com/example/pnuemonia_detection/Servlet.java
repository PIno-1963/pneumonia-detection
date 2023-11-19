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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DoctorDao doctorDAO = new DoctorDao();
        if (doctorDAO.login(email, password)) {
            // Successful login, redirect to a doctor's dashboard or another page
            response.sendRedirect("doctor_dashboard.jsp");
        } else {
            // Failed login, redirect back to the login page with an error message
            response.sendRedirect("doctor-login.jsp?error=1");
        }
    }
}



