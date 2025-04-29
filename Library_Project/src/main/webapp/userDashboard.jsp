<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.User, model.Transaction, dao.TransactionDAO, dao.BookDAO" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"user".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    TransactionDAO txnDAO = new TransactionDAO();
    List<Transaction> borrowed = txnDAO.getUserBorrowedBooks(user.getUserId());

    BookDAO bookDAO = new BookDAO();
    Map<Integer, String> bookMap = new HashMap<>();
    for (Transaction txn : borrowed) {
        bookMap.put(txn.getBookId(), bookDAO.getBookTitleById(txn.getBookId()));
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>User Dashboard - Education Portal</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Favicon -->
  <link rel="shortcut icon" href="images/favicon.ico">
  <!-- Google Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <!-- Style CSS -->
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

  <!-- Header -->
  <header class="bg-primary text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
      <h1 class="mb-0"><i class="material-icons">menu_book</i> Library Management System</h1>
      <div>
        <span class="me-3">Welcome, <strong><%= user.getUsername() %></strong></span>
        <a href="LogoutServlet" class="btn btn-light btn-sm">Logout</a>
      </div>
    </div>
  </header>

  <!-- Dashboard Section -->
  <section class="py-5" style="min-height: 80vh; background-color: #f8f9fa;">
    <div class="container">
      <h3 class="mb-4">Your Borrowed Books</h3>
      <div class="table-responsive">
        <table class="table table-bordered table-striped">
          <thead class="table-dark">
            <tr>
              <th>Book</th>
              <th>Borrow Date</th>
              <th>Due Date</th>
            </tr>
          </thead>
          <tbody>
            <%
              if (borrowed != null && !borrowed.isEmpty()) {
                  for (Transaction txn : borrowed) {
            %>
            <tr>
              <td><%= bookMap.get(txn.getBookId()) %></td>
              <td><%= txn.getBorrowDate() %></td>
              <td><%= txn.getDueDate() %></td>
            </tr>
            <%
                  }
              } else {
            %>
            <tr>
              <td colspan="3" class="text-center">You have no borrowed books.</td>
            </tr>
            <%
              }
            %>
          </tbody>
        </table>
      </div>
      <div class="mt-4">
        <a href="ViewBooksServlet" class="btn btn-primary me-2">Browse Books</a>
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
