<%@ page import="com.example.pnuemonia_detection.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Patient patient = (Patient) request.getAttribute("patient"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Information</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        h2 {
            color: #333;
        }

        .patient-info {
            margin-top: 20px;
        }

        .label {
            font-weight: bold;
        }

        .value {
            margin-bottom: 10px;
        }

        .back-link,
        .logout-link,
        .generate-report-button {
            display: block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Patient Information</h2>

    <div class="patient-info">
        <div class="label">Name:</div>
        <div class="value">${patient.nom} ${patient.prenom}</div>

        <div class="label">Email:</div>
        <div class="value">${patient.email}</div>

        <div class="label">Description:</div>
        <div class="value">${patient.description}</div>

        <div class="label">X-Ray Link:</div>
        <div class="value">${patient.xrayLink}</div>

        <div class="label">Doctor:</div>
        <div class="value">${patient.doctor}</div>

        <div class="label">Prescription from your doctor:</div>
        <div class="value">${patient.prescription}</div>
    </div>

    <% if (patient.checking == 1) { %>
    <!-- Button to generate and download the report -->
    <form action="${pageContext.request.contextPath}/GenerateReportServlet" method="post" target="_blank">
        <input type="hidden" name="email" value="${patient.email}">
        <button class="generate-report-button" type="submit">Generate and Download Report</button>
    </form>
    <% } %>

    <!-- Back link to the previous page -->
    <a class="back-link" href="javascript:history.back()">Back to Previous Page</a>

    <!-- Logout link/button -->
    <a class="logout-link" href="patientInfoServlet?email=${patient.email}&logout=true">Logout</a>
</div>

</body>
</html>