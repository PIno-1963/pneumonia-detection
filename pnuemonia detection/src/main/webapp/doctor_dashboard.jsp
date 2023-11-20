<%@ page import="com.example.pnuemonia_detection.Patient" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-top: 20px;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
        }

        li {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            margin-bottom: 10px;
            padding: 15px;
            transition: background-color 0.3s ease;
        }

        li:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<h2>Welcome to Your Dashboard, Doctor!</h2>
<ul>
    <%
        List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
        for (Patient patient : patientList) {
    %>
    <li><%= patient.getNom() %> <%= patient.getPrenom() %> - <%= patient.getEmail() %></li>
    <% } %>
</ul>

<!-- Add more content as needed -->

</body>
</html>
