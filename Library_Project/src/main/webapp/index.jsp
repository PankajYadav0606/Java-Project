<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Welcome - Library Management System</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- Favicon -->
  <link rel="shortcut icon" href="images/favicon.ico">

  <!-- Google Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.min.css">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/responsive.css">
</head>
<body>

  <!-- Header -->
  <header class="bg-primary text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
      <h1 class="mb-0"><i class="material-icons">menu_book</i> Library Management System</h1>
      <a href="login.jsp" class="btn btn-light">Login</a>
    </div>
  </header>

  <!-- Hero Section -->
  <section class="hero-section text-center d-flex align-items-center" style="height: 80vh; background: url('images/rg-bg.jpg') no-repeat center center/cover;">
    <div class="container text-white">
      <h2 class="display-4 fw-bold">Welcome to Your Digital Library</h2>
      <p class="lead mt-3">Access books, manage borrowing, and track your activity — all in one place.</p>
      <a href="register.jsp" class="btn btn-light btn-lg mt-4">Get Started</a>
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
