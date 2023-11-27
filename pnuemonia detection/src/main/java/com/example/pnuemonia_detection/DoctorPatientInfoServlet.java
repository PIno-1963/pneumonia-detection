package com.example.pnuemonia_detection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "DoctorPatientInfoServlet", value = "/DoctorPatientInfoServlet")
public class DoctorPatientInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Retrieve patient information based on the email
        Patient patient = getPatientInfo(email);

        // Set patient information as an attribute in the request
        request.setAttribute("patient", patient);

        // Forward the request to the doctor's patient information JSP page
        request.getRequestDispatcher("/DoctorPatientInfo.jsp").forward(request, response);
    }


    private Patient getPatientInfo(String email) {
        // Implement your patient information retrieval logic here
        // Use the email to query the database and retrieve the patient information

        // Replace these placeholder values with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "root";

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
                        patient.setEmail(resultSet.getString("email"));
                        patient.setDoctor(resultSet.getString("doctor_name"));
                        patient.setPrescription(resultSet.getString("prescription"));// Use the alias for the doctor's name
                        patient.setXrayImagePath(resultSet.getString("xray_image_path"));
                        patient.setAi_result(resultSet.getString("ai_result"));
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
    }}
