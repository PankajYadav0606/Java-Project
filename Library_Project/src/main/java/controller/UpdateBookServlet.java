// controller/UpdateBookServlet.java

package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        int year = Integer.parseInt(request.getParameter("year"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublicationYear(year);
        book.setQuantity(quantity);

        BookDAO dao = new BookDAO();
        boolean updated = dao.updateBook(book);

        if (updated) {
            response.sendRedirect("viewBooks.jsp?msg=updated");
        } else {
            response.sendRedirect("editBook.jsp?id=" + bookId + "&error=1");
        }
    }
}
