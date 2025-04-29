// controller/BorrowBookServlet.java

package controller;

import dao.TransactionDAO;
import model.Transaction;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        User user = (User) request.getSession().getAttribute("user");

        if (user == null || !"user".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, 14); // 2 weeks from today

        Transaction txn = new Transaction();
        txn.setUserId(user.getUserId());
        txn.setBookId(bookId);
        txn.setBorrowDate(today);
        txn.setDueDate(cal.getTime());

        TransactionDAO dao = new TransactionDAO();
        boolean success = dao.requestBorrow(txn);

        if (success) {
            response.sendRedirect("ViewBooksServlet?msg=borrow-requested");
        } else {
            response.sendRedirect("ViewBooksServlet?error=borrow-failed");
        }
    }
}
