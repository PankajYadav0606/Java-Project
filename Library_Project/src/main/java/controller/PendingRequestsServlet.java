// controller/PendingRequestsServlet.java

package controller;

import dao.TransactionDAO;
import dao.BookDAO;
import dao.UserDAO;
import model.Transaction;
import model.Book;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class PendingRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransactionDAO txnDao = new TransactionDAO();
        List<Transaction> requests = txnDao.getPendingRequests();

        // For showing usernames and book titles
        Map<Integer, String> userMap = new HashMap<>();
        Map<Integer, String> bookMap = new HashMap<>();
        UserDAO userDao = new UserDAO();
        BookDAO bookDao = new BookDAO();

        for (Transaction txn : requests) {
            userMap.put(txn.getUserId(), userDao.getUsernameById(txn.getUserId()));
            bookMap.put(txn.getBookId(), bookDao.getBookTitleById(txn.getBookId()));
        }

        request.setAttribute("requests", requests);
        request.setAttribute("userMap", userMap);
        request.setAttribute("bookMap", bookMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("pendingRequests.jsp");
        dispatcher.forward(request, response);
    }
}
