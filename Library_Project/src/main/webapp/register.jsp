<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Register - Education Portal</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Favicon -->
  <link rel="shortcut icon" href="images/favicon.ico">
  <!-- Google Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <!-- Style CSS -->
  <link rel="stylesheet" href="css/style.css">
  <!-- Responsive CSS -->
  <link rel="stylesheet" href="css/responsive.css">
</head>
<body>

  <!-- Header -->
  <header class="bg-primary text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
      <h1 class="mb-0"><i class="material-icons">menu_book</i> Library Management System</h1>
      <a href="index.jsp" class="btn btn-light">Home</a>
    </div>
  </header>

  <!-- Register Section -->
  <section class="hero-section d-flex align-items-center justify-content-center" style="min-height: 80vh; background: url('images/rg-bg.jpg') no-repeat center center/cover;">
    <div class="container">
      <div class="card shadow-lg p-4 mx-auto bg-white" style="max-width: 400px;">
        <h3 class="text-center mb-4">Register</h3>
        <form action="RegisterServlet" method="post">
          <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="username" id="username" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" id="password" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" required>
          </div>
          <button type="submit" class="btn btn-primary w-100">Register</button>
        </form>
        <p class="mt-3 text-center">Already registered? <a href="login.jsp">Login here</a></p>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="bg-dark text-white text-center py-4">
    <div class="container">
      <p class="mb-0">&copy; 2025 Library Management System. All rights reserved.</p>
    </div>
  </footer>

  <!-- Scripts -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</body>
</html>
