// controller/BorrowedBooksServlet.java

package controller;

import dao.TransactionDAO;
import dao.BookDAO;
import dao.UserDAO;
import model.Transaction;
import util.ConnectionDB;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BorrowedBooksServlet extends HttpServlet {

    // Method to get approved borrowed books
    public List<Transaction> getAllApprovedBorrowedBooks() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE status = 'approved'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction txn = new Transaction();
                txn.setTransactionId(rs.getInt("transaction_id"));
                txn.setUserId(rs.getInt("user_id"));
                txn.setBookId(rs.getInt("book_id"));
                txn.setBorrowDate(rs.getDate("borrow_date"));
                txn.setDueDate(rs.getDate("due_date"));
                txn.setReturnDate(rs.getDate("return_date"));
                txn.setStatus(rs.getString("status"));
                list.add(txn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🚨 This was missing: Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Transaction> borrowed = getAllApprovedBorrowedBooks();

        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();

        Map<Integer, String> bookMap = new HashMap<>();
        Map<Integer, String> userMap = new HashMap<>();

        for (Transaction txn : borrowed) {
            bookMap.put(txn.getBookId(), bookDAO.getBookTitleById(txn.getBookId()));
            userMap.put(txn.getUserId(), userDAO.getUsernameById(txn.getUserId()));
        }

        request.setAttribute("borrowed", borrowed);
        request.setAttribute("bookMap", bookMap);
        request.setAttribute("userMap", userMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("borrowedBooks.jsp");
        dispatcher.forward(request, response);
    }
}
