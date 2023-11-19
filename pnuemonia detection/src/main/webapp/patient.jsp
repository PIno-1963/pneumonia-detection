<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style1.css">
  <title>Login as Patient</title>
</head>
<body>
<div id="container" class="container">
  <div class="header">
    <h1>Login as Patient</h1>
  </div>
  <div class="content">
    <!-- Add method and action attributes to the form -->
    <form action="logingpatient" method="post" class="log-in">
      <label for="email"><span>EMAIL</span><input type="text" name="email" id="email"></label>
      <!-- Change input type to "password" for password -->
      <label for="password"><span>PASSWORD</span><input type="password" name="password" id="password"></label>
      <button type="submit" class="login-button">Login</button>
    </form>
  </div>
  <!-- Login button with styling -->
  <!-- Add type="submit" to make the button submit the form -->

  <!-- Create account button with styling -->
  <!-- Add form action to redirect to create-account.jsp -->
  <form action="create_account.jsp" method="get">
    <button type="submit" class="create-account-button">CREATE AN ACCOUNT</button>
  </form>
</div>
</body>
</html>
