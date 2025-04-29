<%@ page import="java.util.*, model.Book, model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Book> bookList = (List<Book>) request.getAttribute("bookList");
    String search = (String) request.getAttribute("search");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Book Catalog - Education Portal</title>
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
      <h1 class="mb-0"><i class="material-icons">admin_panel_settings</i> Books</h1>
      <div>
        <span class="me-3">Welcome, <strong><%= user.getUsername() %></strong></span>
        <a href="LogoutServlet" class="btn btn-light btn-sm">Logout</a>
      </div>
    </div>
  </header>

  <!-- Main Section -->
  <section class="py-5" style="background-color: #f8f9fa;">
    <div class="container">
      <h3 class="mb-4">Library Book Catalog</h3>

      <form method="get" action="ViewBooksServlet" class="row g-3 mb-4">
        <div class="col-md-10">
          <input type="text" name="search" class="form-control" placeholder="Search by title or author" value="<%= search != null ? search : "" %>">
        </div>
        <div class="col-md-2">
          <input type="submit" class="btn btn-primary w-100" value="Search">
        </div>
      </form>

      <div class="table-responsive">
        <table class="table table-bordered table-striped align-middle">
          <thead class="table-dark">
            <tr>
              <th>Title</th>
              <th>Author</th>
              <th>ISBN</th>
              <th>Year</th>
              <th>Quantity</th>
              <% if ("admin".equals(user.getRole())) { %><th>Actions</th><% } %>
              <% if ("user".equals(user.getRole())) { %><th>Borrow</th><% } %>
            </tr>
          </thead>
          <tbody>
          <%
            if (bookList != null && !bookList.isEmpty()) {
                for (Book b : bookList) {
          %>
            <tr>
              <td><%= b.getTitle() %></td>
              <td><%= b.getAuthor() %></td>
              <td><%= b.getIsbn() %></td>
              <td><%= b.getPublicationYear() %></td>
              <td><%= b.getQuantity() %></td>
              <% if ("admin".equals(user.getRole())) { %>
              <td>
                <a href="EditBookServlet?id=<%= b.getBookId() %>" class="btn btn-sm btn-warning">Edit</a>
                <a href="DeleteBookServlet?id=<%= b.getBookId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
              </td>
              <% } %>
              <% if ("user".equals(user.getRole())) { %>
              <td>
                <form action="BorrowBookServlet" method="post">
                  <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                  <input type="submit" class="btn btn-sm btn-success" value="Request Borrow" <%= b.getQuantity() == 0 ? "disabled" : "" %>>
                </form>
              </td>
              <% } %>
            </tr>
          <%
                }
            } else {
          %>
            <tr>
              <td colspan="7" class="text-center">No books found. Try a different search or add new books.</td>
            </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>

      <div class="mt-4">
        <% if ("admin".equals(user.getRole())) { %>
          <a href="addBook.jsp" class="btn btn-primary me-2">Add New Book</a>
        <% } %>
        <a href="<%= "admin".equals(user.getRole()) ? "adminDashboard.jsp" : "userDashboard.jsp" %>" class="btn btn-secondary">Back to Dashboard</a>
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
