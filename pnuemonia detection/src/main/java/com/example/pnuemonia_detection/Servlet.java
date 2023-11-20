package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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

        // Assuming your login method returns true on successful login
        if (doctorDAO.login(email, password)) {
            // Retrieve the doctor's ID using the new method
            int doctorId = doctorDAO.getDoctorIdByEmail(email);

            // Successful login, set the doctor's ID in the session
            HttpSession session = request.getSession(true);
            session.setAttribute("doctorId", doctorId);

            // Redirect to the doctor's dashboard servlet
            response.sendRedirect("doctorDashboardServlet");
        } else {
            // Failed login, redirect back to the login page with an error message
            response.sendRedirect("doctor-login.jsp?error=1");
        }
    }
}




