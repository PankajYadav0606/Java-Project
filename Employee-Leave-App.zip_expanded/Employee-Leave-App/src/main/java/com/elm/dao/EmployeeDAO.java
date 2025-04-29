package com.elm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.elm.model.Employee;

public class EmployeeDAO {
	private Connection conn;

	public EmployeeDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean registerEmployee(Employee emp) {
        String sql = "INSERT INTO Employee (first_name, last_name, email, phone_number, dob, gender, job_title, " +
                     "department, employee_type, date_of_joining, manager_id, username, password_hash, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SHA2(?, 256), ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhoneNumber());
            stmt.setString(5, emp.getDob());
            stmt.setString(6, emp.getGender());
            stmt.setString(7, emp.getJobTitle());
            stmt.setString(8, emp.getDepartment());
            stmt.setString(9, emp.getEmployeeType());
            stmt.setString(10, emp.getDateOfJoining());
            stmt.setInt(11, emp.getManagerId());
            stmt.setString(12, emp.getUsername());
            stmt.setString(13, emp.getPassword());
            stmt.setString(14, emp.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public Employee validateUser(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
			          
            String sql = "SELECT * FROM Employee WHERE email = ? AND password_hash = SHA2(?, 256)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            Employee e = new Employee();
            if (rs.next()) {
            	e.setFirstName(rs.getString("first_name"));
            	e.setLastName(rs.getString("last_name"));
            	e.setEmail(rs.getString("email"));
            	e.setPhoneNumber(rs.getString("phone_number"));
            	e.setGender(rs.getString("gender"));
            	e.setJobTitle(rs.getString("job_title"));
            	e.setDepartment(rs.getString("department"));
            	e.setEmployeeType(rs.getString("employee_type"));
            	e.setManagerId(rs.getInt("manager_id"));
            	e.setUsername(rs.getString("username"));
            	e.setRole(rs.getString("role"));
            	
            	return e;
            }
            	
		return null;
		
	}
}
