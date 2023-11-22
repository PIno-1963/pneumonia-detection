<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style_create_account.css">
  <title>Create Account</title>
  <script>
    function updateSymptoms() {
      var symptomCheckboxes = document.querySelectorAll('input[name="symptoms"]:checked');
      var selectedSymptoms = Array.from(symptomCheckboxes).map(function (checkbox) {
        return checkbox.value;
      }).join(',');

      document.getElementById('selectedSymptoms').value = selectedSymptoms;

    }
    function validateEmail(email) {
      const regex = /^[\w-]+@[\w-]+\.[a-zA-Z]+$/;
      const input = document.getElementById('email');
      const errorElement = document.getElementById('emailError');
      if (regex.test(email)){
        input.style.border = '1px solid green';
        errorElement.innerHTML = '';
      } else {
        input.style.border = '1px solid red';
        errorElement.innerHTML = 'Invalid email address';
      }
    }
  </script>
</head>
<body>
<div id="container" class="container">
  <div class="header">
    <h1>Create an Account</h1>
  </div>
  <div class="content">
    <style>
      #emailError {
        color: red;
        font-size: 12px;
      }
    </style>
    <form action="PatientCreateAccountServlet" method="post" class="create-account-form" onsubmit="updateSymptoms()">
      <label for="nom">Nom:</label><input type="text" name="nom" id="nom" required>
      <label for="prenom">Prenom:</label><input type="text" name="prenom" id="prenom" required>
      <label for="email">Email:</label><input type="text" name="email" id="email" onfocusout="validateEmail(this.value)" required>
      <p id="emailError"></p>
      <% String emailError = (String)request.getAttribute("emailError"); %>
      <span style="color: red; font-size: 12px">
        ${empty requestScope.emailError ? '' : requestScope.emailError}
      </span>
      <label for="password">Password:</label><input type="password" name="password" id="password" required>
      <label for="description">Description:</label><textarea name="description" id="description" required></textarea>
      <label for="google_drive_link">Google Drive Link:</label><input type="text" name="google_drive_link" id="google_drive_link" required>
      <label for="doctor_id">Choose a Doctor:</label>
      <select name="doctor_id" id="doctor_id" required>
        <option value="1">Doctor 1</option>
        <option value="2">Doctor 2</option>
        <option value="3">Doctor 3</option>
      </select>

      <label>Symptoms:</label>
      <input type="checkbox" name="symptoms" value="frissons"> Frissons
      <input type="checkbox" name="symptoms" value="toux"> Toux
      <input type="checkbox" name="symptoms" value="difficultes_respirer"> Difficultés à respirer
      <input type="checkbox" name="symptoms" value="antecedents_familiaux"> Antécédents familiaux
      <input type="checkbox" name="symptoms" value="fievre"> Fièvre

      <!-- Add a hidden input field to store selected symptoms -->
      <input type="hidden" id="selectedSymptoms" name="selectedSymptoms" value="">

      <input type="submit" value="Create Account">
    </form>
  </div>
</div>
</body>
</html>
