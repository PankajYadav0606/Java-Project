<%@ page import="java.util.*, model.Transaction, model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Transaction> requests = (List<Transaction>) request.getAttribute("requests");
    Map<Integer, String> userMap = (Map<Integer, String>) request.getAttribute("userMap");
    Map<Integer, String> bookMap = (Map<Integer, String>) request.getAttribute("bookMap");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Pending Requests - Library System</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
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
      <h1 class="mb-0"><i class="material-icons">menu_book</i> Library Admin Panel</h1>
      <a href="LogoutServlet" class="btn btn-light">Logout</a>
    </div>
  </header>

  <!-- Main Section -->
  <main class="container my-5">
    <h2 class="mb-4">Pending Borrow Requests</h2>

    <%
        if (requests == null || requests.isEmpty()) {
    %>
        <div class="alert alert-info">No pending borrow requests available.</div>
    <%
        } else {
    %>
    <div class="table-responsive">
      <table class="table table-bordered table-hover align-middle">
        <thead class="table-dark">
          <tr>
            <th>User</th>
            <th>Book</th>
            <th>Borrow Date</th>
            <th>Due Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
        <%
            for (Transaction txn : requests) {
        %>
          <tr>
            <td><%= userMap.get(txn.getUserId()) %></td>
            <td><%= bookMap.get(txn.getBookId()) %></td>
            <td><%= txn.getBorrowDate() %></td>
            <td><%= txn.getDueDate() %></td>
            <td>
              <a href="ApproveRequestServlet?id=<%= txn.getTransactionId() %>&action=approve" class="btn btn-success btn-sm">Approve</a>
              <a href="ApproveRequestServlet?id=<%= txn.getTransactionId() %>&action=reject" class="btn btn-danger btn-sm">Reject</a>
            </td>
          </tr>
        <%
            }
        %>
        </tbody>
      </table>
    </div>
    <% } %>

    <div class="mt-4">
      <a href="adminDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
  </main>

  <!-- Footer -->
  <footer class="bg-dark text-white text-center py-4 mt-5">
    <div class="container">
      <p class="mb-0">&copy; 2025 Library Management System. All rights reserved.</p>
    </div>
  </footer>

  <!-- Scripts -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</body>
</html>
