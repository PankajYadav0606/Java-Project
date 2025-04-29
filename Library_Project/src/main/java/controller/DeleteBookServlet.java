package controller;

import dao.BookDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        System.out.println("Trying to delete book ID: " + bookId);

        BookDAO dao = new BookDAO();
        boolean deleted = dao.deleteBook(bookId);
        System.out.println("Book deleted: " + deleted);

        if (deleted) {
            response.sendRedirect("ViewBooksServlet?msg=deleted");
        } else {
            response.sendRedirect("ViewBooksServlet?error=deletefail");
        }
    }
}
