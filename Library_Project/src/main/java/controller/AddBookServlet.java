// controller/AddBookServlet.java

package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        int year = Integer.parseInt(request.getParameter("year"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublicationYear(year);
        book.setQuantity(quantity);

        BookDAO dao = new BookDAO();
        boolean result = dao.addBook(book);

        if (result) {
            response.sendRedirect("viewBooks.jsp?msg=added");
        } else {
            response.sendRedirect("addBook.jsp?error=1");
        }
    }
}
