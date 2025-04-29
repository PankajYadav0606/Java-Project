// controller/ReturnBookServlet.java

package controller;

import dao.TransactionDAO;
import util.ConnectionDB;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int txnId = Integer.parseInt(request.getParameter("id"));
        TransactionDAO dao = new TransactionDAO();

        boolean result = dao.returnBook(txnId);
        if (result) {
            response.sendRedirect("BorrowedBooksServlet?msg=returned");
        } else {
            response.sendRedirect("BorrowedBooksServlet?error=returnfail");
        }
    }
    
    public boolean updateTransactionStatus(int txnId, String status) {
        String updateTxnSql = "UPDATE transactions SET status = ? WHERE transaction_id = ?";
        String getBookSql = "SELECT book_id FROM transactions WHERE transaction_id = ?";
        String decrementBookSql = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ? AND quantity > 0";

        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false); // transaction start

            int bookId = 0;
            try (PreparedStatement stmt = conn.prepareStatement(getBookSql)) {
                stmt.setInt(1, txnId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bookId = rs.getInt("book_id");
                } else {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement updateTxnStmt = conn.prepareStatement(updateTxnSql)) {
                updateTxnStmt.setString(1, status);
                updateTxnStmt.setInt(2, txnId);
                updateTxnStmt.executeUpdate();
            }

            if ("approved".equals(status)) {
                try (PreparedStatement decStmt = conn.prepareStatement(decrementBookSql)) {
                    decStmt.setInt(1, bookId);
                    decStmt.executeUpdate();
                }
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int txnId) {
        String updateSql = "UPDATE transactions SET status='returned', return_date=CURDATE() WHERE transaction_id=?";
        String getBookSql = "SELECT book_id FROM transactions WHERE transaction_id=?";
        String incQtySql = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";

        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);

            int bookId = 0;
            try (PreparedStatement stmt = conn.prepareStatement(getBookSql)) {
                stmt.setInt(1, txnId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bookId = rs.getInt("book_id");
                } else {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setInt(1, txnId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(incQtySql)) {
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
