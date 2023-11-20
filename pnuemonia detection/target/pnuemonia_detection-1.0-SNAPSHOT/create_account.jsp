<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style_create_account.css">
  <title>Create Account</title>
</head>
<body>
<div id="container" class="container">
  <div class="header">
    <h1>Create an Account</h1>
  </div>
  <div class="content">
    <form action="PatientCreateAccountServlet" method="post" class="create-account-form">
      <label for="nom">Nom:</label><input type="text" name="nom" id="nom" required>
      <label for="prenom">Prenom:</label><input type="text" name="prenom" id="prenom" required>
      <label for="email">Email:</label><input type="text" name="email" id="email" required>
      <label for="password">Password:</label><input type="password" name="password" id="password" required>
      <label for="description">Description:</label><textarea name="description" id="description" required></textarea>
      <label for="google_drive_link">Google Drive Link:</label><input type="text" name="google_drive_link" id="google_drive_link" required>
      <label for="doctor_id">Choose a Doctor:</label>
      <!-- Add a dropdown or radio buttons for choosing a doctor -->
      <select name="doctor_id" id="doctor_id" required>
        <option value="1">Doctor 1</option>
        <option value="2">Doctor 2</option>
        <option value="3">Doctor 3</option>
      </select>
      <input type="submit" value="Create Account">
    </form>
  </div>
</div>
</body>
</html>
