package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the checked patients' emails
        String[] checkedPatients = request.getParameterValues("checkedPatients");

        if (checkedPatients != null) {
            // Update the 'checking' column for each checked patient in the database
            Connection connection = null;

            try {
                connection = jdbc_conn.getConnection();

                for (String email : checkedPatients) {
                    updateCheckingColumn(connection, email, 1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                // Close the connection in a finally block to ensure it's always closed
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Redirect back to the doctor's dashboard
        response.sendRedirect("doctorDashboardServlet");
    }

    private List<Patient> getDoctorPatients(int doctorId) {
        // Implement your logic to retrieve the list of patients for the doctor
        // Use the doctorId to query the database and retrieve the patient information

        // Replace these placeholder values with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "hamza";

        List<Patient> patientList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = jdbc_conn.getConnection();

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
                        patient.setChecking(resultSet.getInt("checking"));

                        patientList.add(patient);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        } finally {
            // Close the connection in a finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return patientList;
    }

    private void updateCheckingColumn(Connection connection, String email, int checkValue) {
        // Implement your logic to update the 'checking' column in the database
        // Use the email to identify the patient

        // Replace these placeholder values with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "hamza";

        try {
            // Use a SQL update statement to set the 'checking' column
            String updateSql = "UPDATE pneumonia.patients SET checking = ? WHERE email = ?";

            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setInt(1, checkValue);
                updateStatement.setString(2, email);

                // Execute the update
                updateStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }
    }
}