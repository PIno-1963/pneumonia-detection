package com.example.pnuemonia_detection;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "GenerateReportServlet", value = "/GenerateReportServlet")
public class GenerateReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Retrieve patient information based on the email
        Patient patient = getPatientInfo(email);
        String full_name;
        full_name=patient.getNom()+"_"+patient.getPrenom();
        // Generate the medical report as HTML
        String report = generateMedicalReport(patient);

        // Convert HTML report to PDF
        byte[] pdfBytes = generatePdf(report);

        // Set the content type and headers for PDF response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=MedicalReport_"+full_name+".pdf");

        // Get the response output stream
        try (OutputStream out = response.getOutputStream()) {
            // Write PDF bytes to the response output stream
            out.write(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Patient getPatientInfo(String email) {

        Patient patient = new Patient();

        try {
            // Use your jdbc_conn class to get a database connection
            Connection connection = jdbc_conn.getConnection();

            // Use a SQL query to retrieve patient information
            String sql = "SELECT * FROM pneumonia.patients WHERE email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        patient.setNom(resultSet.getString("nom"));
                        patient.setPrenom(resultSet.getString("prenom"));
                        patient.setDescription(resultSet.getString("description"));
                        patient.setEmail(resultSet.getString("email"));
                        patient.setPrescription(resultSet.getString("prescription"));
                        patient.setSymptoms(resultSet.getString("symptoms"));



                        // Add more fields as needed

                        // Close resources
                        resultSet.close();
                    }
                }
            }

            // Close resources
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in a real-world application
        }

        return patient;
    }

    private String generateMedicalReport(Patient patient) {
        // Implement the logic to generate a medical report
        StringBuilder report = new StringBuilder();

        // Report header
        report.append("<h2>Medical Report</h2>");
        report.append("<p><strong>Date of Generation:</strong> ").append(getCurrentDate()).append("</p>");
        report.append("<p>le patient  ").append(patient.getNom()).append(" ").append(patient.getPrenom()).append("<p>il presente les symptoms suivant").append(patient.getSymptoms()).append("</p>");;
        report.append("<p><strong>Email:</strong> ").append(patient.getEmail()).append("</p>");
        report.append("<p><strong>Description:</strong> ").append(patient.getDescription()).append("</p>");



        // Add more sections as needed

        return report.toString();
    }

    private String getCurrentDate() {
        // Get the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    private byte[] generatePdf(String html) {
        try {
            // Create document and set up a writer for PDF
            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);

            // Open the document
            document.open();

            // Create HTMLWorker to convert HTML to PDF
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(html));

            // Close the document
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
