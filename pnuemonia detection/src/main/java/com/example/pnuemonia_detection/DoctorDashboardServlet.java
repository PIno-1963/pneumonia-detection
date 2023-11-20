package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DoctorDashboardServlet.java

// Import statements

@WebServlet(name = "DoctorDashboardServlet", value = "/doctorDashboardServlet")
public class DoctorDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the doctor's ID from the session
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("doctorId") != null) {
            int doctorId = (int) session.getAttribute("doctorId");

            // Retrieve the list of patients for the doctor
            List<Patient> patientList = getDoctorPatients(doctorId);

            // Set the patient list as an attribute in the request
            request.setAttribute("patientList", patientList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/doctor_dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to the login page if the doctor's ID is not found in the session
            response.sendRedirect("login.jsp");
        }
    }

    private List<Patient> getDoctorPatients(int doctorId) {
        // Implement your logic to retrieve the list of patients for the doctor
        // Use the doctorId to query the database and retrieve the patient information

        // Replace these placeholder values with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "hamza";

        List<Patient> patientList = new ArrayList<>();

        try {
            // Use your jdbc_conn class to get a database connection
            Connection connection = jdbc_conn.getConnection();

            // Use a SQL query to retrieve the list of patients for the doctor
            String sql = "SELECT patients.* " +
                    "FROM pneumonia.patients " +
                    "WHERE patients.doctor_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, doctorId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Patient patient = new Patient();
                        patient.setNom(resultSet.getString("nom"));
                        patient.setPrenom(resultSet.getString("prenom"));
                        patient.setDescription(resultSet.getString("description"));
                        patient.setXrayLink(resultSet.getString("google_drive_link"));
                        patient.setEmail(resultSet.getString("email"));

                        patientList.add(patient);
                        for (Patient patient1 : patientList) {
                            System.out.println("Patient: " + patient1.getNom() + " " + patient1.getPrenom() + " - " + patient1.getEmail());
                        }
                    }
                }
            }

            // Close resources
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }

        return patientList;
    }
}
