package com.elm.model;

public class Employee {
    private String firstName, lastName, email, phoneNumber, dob, gender;
    private String jobTitle, department, employeeType, dateOfJoining;
    private int managerId;
    private String username, password, role;

    public Employee() {}
    public Employee(String firstName, String lastName, String email, String phoneNumber, String dob, String gender,
                    String jobTitle, String department, String employeeType, String dateOfJoining, int managerId,
                    String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.department = department;
        this.employeeType = employeeType;
        this.dateOfJoining = dateOfJoining;
        this.managerId = managerId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    // Setters
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    // Getters
    public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getJobTitle() { return jobTitle; }
    public String getDepartment() { return department; }
    public String getEmployeeType() { return employeeType; }
    public String getDateOfJoining() { return dateOfJoining; }
    public int getManagerId() { return managerId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
