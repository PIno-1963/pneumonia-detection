package com.example.pnuemonia_detection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/PatientCreateAccountServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 5 * 5,  // 25 MB
        fileSizeThreshold = 1024 * 1024)
public class PatientCreateAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String plainTextPassword = request.getParameter("password"); // Get plain-text password
        String description = request.getParameter("description");
        String googleDriveLink = request.getParameter("google_drive_link");
        int doctorId = Integer.parseInt(request.getParameter("doctor_id"));
        int age = Integer.parseInt(request.getParameter("age")); // Add age parameter

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

        // Check if the email already exists
        if (isEmailExists(email)) {
            request.setAttribute("emailError", "Email already exists!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("create_account.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Retrieve selected symptoms from the hidden field
        String selectedSymptoms = request.getParameter("selectedSymptoms");
        System.out.println(selectedSymptoms);

        // Handle file upload for X-ray image
        Part xrayImagePart = request.getPart("xray_image");
        String xrayImageFileName = extractFileName(xrayImagePart);
        String uploadDirectory = "C:/Users/hamza/OneDrive/Bureau/saving_xray";
        String xrayImageSavePath = uploadDirectory + File.separator + xrayImageFileName;

        // Save the X-ray image to the server
        try (InputStream inputStream = xrayImagePart.getInputStream()) {
            Files.copy(inputStream, new File(xrayImageSavePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO patients (nom, prenom, email, password, description, google_drive_link, doctor_id, symptoms, age, xray_image_path) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, hashedPassword); // Store the hashed password
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, googleDriveLink);
            preparedStatement.setInt(7, doctorId);
            preparedStatement.setString(8, selectedSymptoms);
            preparedStatement.setInt(9, age); // Set the age parameter
            preparedStatement.setString(10, xrayImageSavePath); // Save the X-ray image path

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("patient.jsp");
            } else {
                response.sendRedirect("create-account.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("create-account.jsp?error=1");
        }
    }

    private boolean isEmailExists(String email) {
        String query = "SELECT email FROM patients WHERE email = ?";
        try (Connection connection = jdbc_conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
