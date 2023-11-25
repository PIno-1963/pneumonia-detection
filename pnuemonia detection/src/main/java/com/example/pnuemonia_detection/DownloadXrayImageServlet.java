package com.example.pnuemonia_detection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "DownloadImageServlet", value = "/DownloadImageServlet")
public class DownloadXrayImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email parameter from the request
        String email = request.getParameter("email");

        // Retrieve the X-ray image path based on the email
        String xrayImagePath = getXrayImagePath(email);
        System.out.println(xrayImagePath);
        if (xrayImagePath != null) {
            // Set response content type
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"xray_image.jpg\"");

            try (OutputStream outputStream = response.getOutputStream()) {
                // Read the X-ray image file and write it to the response output stream
                Path imagePath = Paths.get(xrayImagePath);
                Files.copy(imagePath, outputStream);
            }
        } else {
            // Handle case where X-ray image path is not found
            response.getWriter().write("X-ray image not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String getXrayImagePath(String email) {
        // Replace these placeholder values with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/pneumonia";
        String username = "root";
        String password = "hamza";

        try {
            // Use your jdbc_conn class to get a database connection
            Connection connection = jdbc_conn.getConnection();

            // Use a SQL query to retrieve the X-ray image path based on the email
            String sql = "SELECT xray_image_path FROM pneumonia.patients WHERE email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String xrayImagePath = resultSet.getString("xray_image_path");

                        // Close resources
                        resultSet.close();
                        connection.close();

                        return xrayImagePath;
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
