// controller/EditBookServlet.java

package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));

        BookDAO dao = new BookDAO();
        Book book = dao.getBookById(bookId);

        if (book != null) {
            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editBook.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("viewBooks.jsp?error=notfound");
        }
    }
}
