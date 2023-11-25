<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        .back-link {
            display: block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }

        /* Style for prescription input form */
        .prescription-form {
            margin-top: 20px;
        }

        .prescription-label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
        }

        .prescription-input {
            width: 100%;
            padding: 5px;
            margin-top: 5px;
            margin-bottom: 10px;
        }

        /* Style for report button */
        .report-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        /* Style for AI-Model checkboxes */
        .ai-model-checkboxes {
            margin-top: 10px;
        }

        /* Style for X-ray image download link */
        .xray-download-link {
            margin-top: 10px;
            display: block;
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

        <div class="label">AI-Model:</div>
        <form action="${pageContext.request.contextPath}/UpdateAIModelServlet" method="post" class="ai-model-checkboxes">
            <label>
                <input type="radio" name="aiModel" value="1" ${patient.modelValue == 1 ? 'checked' : ''}>
                Yes
                <input type="hidden" name="yesOption" value="1">
            </label>
            <label>
                <input type="radio" name="aiModel" value="0" ${patient.modelValue == 0 ? 'checked' : ''}>
                No
                <input type="hidden" name="noOption" value="0">
            </label>
            <br>
            <input type="hidden" name="email" value="${patient.email}">
            <input type="submit" value="Save AI-Model">
        </form>

        <div class="label">Doctor:</div>
        <div class="value">${patient.doctor}</div>

        <!-- Display existing prescription information -->
        <div class="label">Prescription:</div>
        <div class="value">${patient.prescription}</div>
    </div>

    <!-- Prescription input form -->
    <form class="prescription-form" action="${pageContext.request.contextPath}/InfoForDoctor" method="post">
        <input type="hidden" name="email" value="${patient.email}">
        <label class="prescription-label" for="prescription">Add or update Prescription:</label>
        <textarea id="prescription" name="prescription" class="prescription-input" rows="4" cols="50"></textarea>
        <br>
        <input type="submit" value="Save Prescription">
    </form>
    <div class="label">X-ray Image:</div>
    <div class="value">
        <!-- Button to trigger X-ray image download -->
        <form action="${pageContext.request.contextPath}/DownloadImageServlet" method="get">
            <input type="hidden" name="email" value="${patient.email}">
            <button type="submit">Download X-ray Image</button>
        </form>
    </div>
    <!-- Report button -->
    <button class="report-button" onclick="generateReport()">Generate Report</button>

    <!-- X-ray image download link -->

    <a class="back-link" href="javascript:history.back()">Back to Previous Page</a>
</div>

<!-- Report form -->
<form id="report-form" action="${pageContext.request.contextPath}/GenerateReportServlet" method="post">
    <input type="hidden" name="email" value="${patient.email}">
</form>

<!-- Script for submitting the report form -->
<script>
    function generateReport() {
        document.getElementById("report-form").submit();
    }
</script>

</body>
</html>