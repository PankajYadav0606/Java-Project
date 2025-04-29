// dao/TransactionDAO.java

package dao;

import model.Transaction;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public boolean requestBorrow(Transaction txn) {
        String sql = "INSERT INTO transactions (user_id, book_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, 'pending')";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, txn.getUserId());
            stmt.setInt(2, txn.getBookId());
            stmt.setDate(3, new java.sql.Date(txn.getBorrowDate().getTime()));
            stmt.setDate(4, new java.sql.Date(txn.getDueDate().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Fetch all pending transactions
    public List<Transaction> getPendingRequests() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE status = 'pending'";

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
                txn.setStatus(rs.getString("status"));
                list.add(txn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Approve or reject a transaction
    public boolean updateTransactionStatus(int txnId, String status) {
        String sql = "UPDATE transactions SET status = ? WHERE transaction_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, txnId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
 // List all approved borrowings for a user
    public List<Transaction> getUserBorrowedBooks(int userId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? AND status = 'approved'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction txn = new Transaction();
                txn.setTransactionId(rs.getInt("transaction_id"));
                txn.setUserId(rs.getInt("user_id"));
                txn.setBookId(rs.getInt("book_id"));
                txn.setBorrowDate(rs.getDate("borrow_date"));
                txn.setDueDate(rs.getDate("due_date"));
                txn.setStatus(rs.getString("status"));
                list.add(txn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Mark book as returned
    public boolean returnBook(int txnId) {
        String sql = "UPDATE transactions SET status='returned', return_date=CURDATE() WHERE transaction_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, txnId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    public int getTotalBorrowed() {
        String sql = "SELECT COUNT(*) FROM transactions WHERE status='approved'";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getOverdueBooks() {
        String sql = "SELECT COUNT(*) FROM transactions WHERE status='approved' AND due_date < CURDATE()";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    
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


}
