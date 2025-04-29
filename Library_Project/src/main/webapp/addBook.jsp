<!-- view/addBook.jsp -->

<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Add New Book</h2>
    <form action="AddBookServlet" method="post">
        <label>Title:</label><input type="text" name="title" required><br>
        <label>Author:</label><input type="text" name="author" required><br>
        <label>ISBN:</label><input type="text" name="isbn" required><br>
        <label>Publication Year:</label><input type="number" name="year" required><br>
        <label>Quantity:</label><input type="number" name="quantity" required><br>
        <input type="submit" value="Add Book">
    </form>
    <br>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
