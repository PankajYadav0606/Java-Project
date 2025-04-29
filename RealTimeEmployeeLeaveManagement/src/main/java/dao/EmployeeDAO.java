package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.DBConnection;

public class EmployeeDAO {

    // Insert a new employee (Registration or Admin adding new employee)
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO Employee (first_name, last_name, email, phone_number, dob, gender, job_title, department, employee_type, date_of_joining, manager_id, username, password_hash, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhoneNumber());
            ps.setDate(5, new java.sql.Date(emp.getDob().getTime()));
            ps.setString(6, emp.getGender());
            ps.setString(7, emp.getJobTitle());
            ps.setString(8, emp.getDepartment());
            ps.setString(9, emp.getEmployeeType());
            ps.setDate(10, new java.sql.Date(emp.getDateOfJoining().getTime()));
            if (emp.getManagerId() != null) {
                ps.setInt(11, emp.getManagerId());
            } else {
                ps.setNull(11, java.sql.Types.INTEGER);
            }
            ps.setString(12, emp.getUsername());
            ps.setString(13, emp.getPasswordHash());
            ps.setString(14, emp.getRole());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Fetch all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phone_number"));
                emp.setDob(rs.getDate("dob"));
                emp.setGender(rs.getString("gender"));
                emp.setJobTitle(rs.getString("job_title"));
                emp.setDepartment(rs.getString("department"));
                emp.setEmployeeType(rs.getString("employee_type"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setManagerId(rs.getInt("manager_id"));
                emp.setEmployeeStatus(rs.getString("employee_status"));
                emp.setUsername(rs.getString("username"));
                emp.setRole(rs.getString("role"));
                employees.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    // Fetch employee by ID
    public Employee getEmployeeById(int id) {
        Employee emp = null;
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPhoneNumber(rs.getString("phone_number"));
                    emp.setDob(rs.getDate("dob"));
                    emp.setGender(rs.getString("gender"));
                    emp.setJobTitle(rs.getString("job_title"));
                    emp.setDepartment(rs.getString("department"));
                    emp.setEmployeeType(rs.getString("employee_type"));
                    emp.setDateOfJoining(rs.getDate("date_of_joining"));
                    emp.setManagerId(rs.getInt("manager_id"));
                    emp.setEmployeeStatus(rs.getString("employee_status"));
                    emp.setUsername(rs.getString("username"));
                    emp.setRole(rs.getString("role"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emp;
    }

    // Update employee
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE Employee SET first_name=?, last_name=?, email=?, phone_number=?, dob=?, gender=?, job_title=?, department=?, employee_type=?, date_of_joining=?, manager_id=?, employee_status=?, role=? WHERE employee_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhoneNumber());
            ps.setDate(5, new java.sql.Date(emp.getDob().getTime()));
            ps.setString(6, emp.getGender());
            ps.setString(7, emp.getJobTitle());
            ps.setString(8, emp.getDepartment());
            ps.setString(9, emp.getEmployeeType());
            ps.setDate(10, new java.sql.Date(emp.getDateOfJoining().getTime()));
            if (emp.getManagerId() != null) {
                ps.setInt(11, emp.getManagerId());
            } else {
                ps.setNull(11, java.sql.Types.INTEGER);
            }
            ps.setString(12, emp.getEmployeeStatus());
            ps.setString(13, emp.getRole());
            ps.setInt(14, emp.getEmployeeId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Delete employee
    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
