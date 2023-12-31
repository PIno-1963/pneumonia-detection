<%@ page import="com.example.pnuemonia_detection.Patient" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
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
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .dashboard-container {
            width: 80%;
            background-color: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
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
            background-color: #f9f9f9;
            border-bottom: 1px solid #e0e0e0;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        li:last-child {
            border-bottom: none;
        }

        .checkbox {
            margin-right: 10px;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #2184b5;
        }
        input[type="submit"], input[type="button"], a {
            background: linear-gradient(to right, #3498db, #2980b9);
            color: #fff;
            padding: 1px 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
            margin-right: 10px;
            transition: background 0.3s ease, transform 0.2s ease;
        }

        input[type="submit"]:hover, input[type="button"]:hover, a:hover {
            background: linear-gradient(to right, #2184b5, #3498db);
            transform: scale(1.02);
        }
    </style>
    <script>
        function confirmLogout() {
            return confirm('Are you sure you want to logout?');
        }
    </script>
    </head>
<body>
<div class="dashboard-container">
    <h2>Welcome to Your Dashboard, Doctor!</h2>
    <form action="doctorDashboardServlet" method="post">
        <ul>
            <% List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
                for (Patient patient : patientList) { %>
            <li>
                <input type="checkbox" class="checkbox" name="checkedPatients" value="<%= patient.getEmail() %>"
                       <% if (patient.getChecking() == 1) { %>checked<% } %>>
                <div onclick="window.location.href='DoctorPatientInfoServlet?email=<%= patient.getEmail() %>'">
                   <strong> <%= patient.getNom() %> <%= patient.getPrenom() %></strong>
                </div>
            </li>
            <% } %>
        </ul>

        <!-- Add a hidden input field to handle the case when no checkbox is checked -->
        <input type="hidden" name="checkedPatients" value="">

        <input type="submit" value="Update Checked Patients">
    </form>

    <!-- Add the logout form -->
    <div class="logout-container">
        <a href="logoutServlet" onclick="return confirmLogout()">Logout</a>
    </div>
</div>
</div>
</body>
</html>

