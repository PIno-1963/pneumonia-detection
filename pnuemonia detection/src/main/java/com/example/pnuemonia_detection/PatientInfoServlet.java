package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet(name = "PatientInfoServlet", value = "/patientInfoServlet")
public class PatientInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("true".equals(request.getParameter("logout"))) {
            request.getRequestDispatcher("/logoutServlet").forward(request, response);
            return;
        }
        String email = request.getParameter("email");
        System.out.println(email);// Retrieve patient information based on the email
        Patient patient = getPatientInfo(email);

        // Set patient information as an attribute in the request
        request.setAttribute("patient", patient);

        request.getRequestDispatcher("/patient_dashboard.jsp").forward(request, response);
    }

    private Patient getPatientInfo(String email) {
        // Implement your patient information retrieval logic here
        // Use the email to query the database and retrieve the patient information

        // Replace these placeholder values with your actual database credentials


        try {
            // Use your jdbc_conn class to get a database connection
            Connection connection = jdbc_conn.getConnection();

            // Use a SQL query with a JOIN to retrieve patient and doctor information
            String sql = "SELECT patients.*, doctors.name AS doctor_name " +
                    "FROM pneumonia.patients " +
                    "INNER JOIN pneumonia.doctors ON patients.doctor_id = doctors.iddoctors " +
                    "WHERE patients.email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Patient patient = new Patient();
                        patient.setNom(resultSet.getString("nom"));
                        patient.setPrenom(resultSet.getString("prenom"));
                        patient.setDescription(resultSet.getString("description"));
                        patient.setXrayLink(resultSet.getString("google_drive_link"));
                        patient.setEmail(resultSet.getString("email"));
                        patient.setDoctor(resultSet.getString("doctor_name")); // Use the alias for the doctor's name
                        patient.setPrescription(resultSet.getString("prescription"));
                        patient.setChecking(resultSet.getInt("Checking"));
                        // Close resources
                        resultSet.close();

                        return patient;
                    }
                }
            }

            // Close resources
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }

        return null;
    }
}

