// controller/ViewBooksServlet.java

package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("search");
        if (keyword == null) keyword = "";

        BookDAO dao = new BookDAO();
        List<Book> books = dao.getAllBooks(keyword);

        request.setAttribute("bookList", books);
        request.setAttribute("search", keyword);

        RequestDispatcher dispatcher = request.getRequestDispatcher("viewBooks.jsp");
        dispatcher.forward(request, response);
    }
}
