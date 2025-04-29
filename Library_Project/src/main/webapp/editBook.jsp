<%@ page import="model.Book" %>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
    Book book = (Book) request.getAttribute("book");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<header class="bg-dark text-white py-3">
    <div class="container d-flex justify-content-between align-items-center">
        <h2 class="mb-0">Edit Book</h2>
        <a href="ViewBooksServlet" class="btn btn-outline-light btn-sm">Back to Book List</a>
    </div>
</header>

<main class="container my-5">
    <form action="UpdateBookServlet" method="post" class="p-4 border rounded bg-light shadow-sm">
        <input type="hidden" name="bookId" value="<%= book.getBookId() %>">

        <div class="mb-3">
            <label class="form-label">Title:</label>
            <input type="text" class="form-control" name="title" value="<%= book.getTitle() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Author:</label>
            <input type="text" class="form-control" name="author" value="<%= book.getAuthor() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">ISBN:</label>
            <input type="text" class="form-control" name="isbn" value="<%= book.getIsbn() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Publication Year:</label>
            <input type="number" class="form-control" name="year" value="<%= book.getPublicationYear() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Quantity:</label>
            <input type="number" class="form-control" name="quantity" value="<%= book.getQuantity() %>" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Update Book</button>
    </form>
</main>

<footer class="bg-dark text-white text-center py-3 mt-4">
    <p class="mb-0">&copy; 2025 Library Management System - Admin Panel</p>
</footer>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
