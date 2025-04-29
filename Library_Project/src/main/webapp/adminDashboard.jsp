<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User, dao.BookDAO, dao.TransactionDAO" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    BookDAO bookDAO = new BookDAO();
    TransactionDAO txnDAO = new TransactionDAO();

    int totalBooks = bookDAO.getTotalBooks();
    int borrowedBooks = txnDAO.getTotalBorrowed();
    int overdueBooks = txnDAO.getOverdueBooks();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard - Library System</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Google Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.min.css">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="css/style.css">

  <style>
    html, body {
      height: 100%;
      margin: 0;
      display: flex;
      flex-direction: column;
    }

    main {
      flex: 1;
    }
  </style>
</head>
<body>

  <!-- Header -->
  <header class="bg-primary text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
      <h1 class="mb-0"><i class="material-icons">admin_panel_settings</i> Admin Dashboard</h1>
      <div>
        <span class="me-3">Welcome, <strong><%= user.getUsername() %></strong></span>
        <a href="LogoutServlet" class="btn btn-light btn-sm">Logout</a>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <main class="container my-5">
    <div class="row g-4 mb-4">
      <div class="col-md-4">
        <div class="card text-white bg-primary shadow-sm">
          <div class="card-body text-center">
            <i class="material-icons fs-1">library_books</i>
            <h5 class="card-title mt-2">Total Books</h5>
            <p class="card-text fs-3"><%= totalBooks %></p>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-white bg-success shadow-sm">
          <div class="card-body text-center">
            <i class="material-icons fs-1">assignment_turned_in</i>
            <h5 class="card-title mt-2">Borrowed Books</h5>
            <p class="card-text fs-3"><%= borrowedBooks %></p>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-white bg-danger shadow-sm">
          <div class="card-body text-center">
            <i class="material-icons fs-1">warning</i>
            <h5 class="card-title mt-2">Overdue Books</h5>
            <p class="card-text fs-3"><%= overdueBooks %></p>
          </div>
        </div>
      </div>
    </div>

    <div class="list-group">
      <a href="ViewBooksServlet" class="list-group-item list-group-item-action">
        <i class="material-icons me-2">menu_book</i> Manage Books
      </a>
      <a href="PendingRequestsServlet" class="list-group-item list-group-item-action">
        <i class="material-icons me-2">hourglass_top</i> Pending Borrow Requests
      </a>
      <a href="BorrowedBooksServlet" class="list-group-item list-group-item-action">
        <i class="material-icons me-2">import_contacts</i> View Borrowed Books
      </a>
    </div>
  </main>

  <!-- Footer -->
  <footer class="bg-dark text-white text-center py-4 mt-auto">
    <div class="container">
      <p class="mb-0">&copy; 2025 Library Management System. All rights reserved.</p>
    </div>
  </footer>

  <!-- Bootstrap JS -->
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
