<%@ page import="java.util.*, model.Transaction, model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Transaction> borrowed = (List<Transaction>) request.getAttribute("borrowed");
    Map<Integer, String> userMap = (Map<Integer, String>) request.getAttribute("userMap");
    Map<Integer, String> bookMap = (Map<Integer, String>) request.getAttribute("bookMap");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Borrowed Books - Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Header -->
<header class="bg-primary text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
        <h1 class="mb-0"><i class="material-icons">task_alt</i> Borrowed Books</h1>
        <a href="LogoutServlet" class="btn btn-light btn-sm">Logout</a>
    </div>
</header>

<!-- Main Content -->
<main class="container my-5">
    <h2 class="mb-4">Approved Borrowed Books</h2>

    <% if (borrowed == null) { %>
        <div class="alert alert-warning">No 'borrowed' attribute found in request.</div>
    <% } else if (borrowed.isEmpty()) { %>
        <div class="alert alert-info">No approved borrowed transactions available.</div>
    <% } else { %>

    <div class="alert alert-success">
        Found <strong><%= borrowed.size() %></strong> approved borrowed transaction<%= borrowed.size() > 1 ? "s" : "" %>.
    </div>

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
            <% for (Transaction txn : borrowed) { %>
                <tr>
                    <td><%= userMap.get(txn.getUserId()) %></td>
                    <td><%= bookMap.get(txn.getBookId()) %></td>
                    <td><%= txn.getBorrowDate() %></td>
                    <td><%= txn.getDueDate() %></td>
                    <td>
                        <form action="ReturnBookServlet" method="get" class="d-inline">
                            <input type="hidden" name="id" value="<%= txn.getTransactionId() %>">
                            <button type="submit" class="btn btn-sm btn-success">Mark as Returned</button>
                        </form>
                    </td>
                </tr>
            <% } %>
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
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
