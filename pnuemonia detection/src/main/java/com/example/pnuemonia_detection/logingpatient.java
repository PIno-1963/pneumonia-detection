package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logingpatient", value = "/logingpatient")
public class logingpatient extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PatientDao patientDao = new PatientDao();
        if (patientDao.login(email, password)) {
            // Successful login, redirect to the patient information page with email parameter
            response.sendRedirect("patientInfoServlet?email=" + email);
        } else {
            // Failed login, redirect back to the login page with an error message
            response.sendRedirect("patient-login.jsp?error=1");
        }
    }
}
