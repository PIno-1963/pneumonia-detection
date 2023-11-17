<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil</title>
    <link rel="stylesheet" href="Styles.css">
</head>
<body>
<div class="container">
    <form action="Servlet" method="get">
        <div class="icon-container" id="doctor-container">
            <div class="icon-label-container">
                <div class="icon" id="doctor-icon">
                    <!-- You can replace the content inside the div with your own doctor icon -->
                    🩺
                </div>
            </div>
            <button class="button" name="role" value="doctor" id="doctor-button">Doctor</button>
        </div>

        <div class="icon-container" id="patient-container">
            <div class="icon-label-container">
                <div class="icon" id="patient-icon">
                    <!-- You can replace the content inside the div with your own patient icon -->
                    🏥
                </div>
            </div>
            <button class="button" name="role" value="patient" id="patient-button">Patient</button>
        </div>
    </form>
</div>
</body>
</html>
