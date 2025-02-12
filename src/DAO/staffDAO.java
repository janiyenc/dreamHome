/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODEL.Staff;
import database.databaseConnection;


public class staffDAO {

    // Insert new staff (auto-assign branchNo based on city)
    public void addStaff(Staff staff, String city) throws SQLException {
        String sql = "INSERT INTO staff (No, fName, lName, position, sex, DOB, salary, branchNo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT branchNo FROM branch WHERE city = ?))";
        
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, staff.getNo());
            pstmt.setString(2, staff.getfName());
            pstmt.setString(3, staff.getlName());
            pstmt.setString(4, staff.getPosition());
            pstmt.setString(5, staff.getSex());
            pstmt.setDate(6, staff.getDob());
            pstmt.setBigDecimal(7, staff.getSalary());
            pstmt.setString(8, city); // Auto-assign branchNo

            pstmt.executeUpdate();
        }
    }

    // Retrieve all staff
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getString("No"),
                    rs.getString("fName"),
                    rs.getString("lName"),
                    rs.getString("position"),
                    rs.getString("sex"),
                    rs.getDate("DOB"),
                    rs.getBigDecimal("salary"),
                    rs.getString("branchNo")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }

    // Update staff details
    public void updateStaff(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET fName=?, lName=?, position=?, sex=?, DOB=?, salary=? WHERE No=?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, staff.getfName());
            pstmt.setString(2, staff.getlName());
            pstmt.setString(3, staff.getPosition());
            pstmt.setString(4, staff.getSex());
            pstmt.setDate(5, staff.getDob());
            pstmt.setBigDecimal(6, staff.getSalary());
            pstmt.setString(7, staff.getNo());

            pstmt.executeUpdate();
        }
    }

    // Delete a staff member
    public void deleteStaff(String staffNo) throws SQLException {
        String sql = "DELETE FROM staff WHERE No = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, staffNo);
            pstmt.executeUpdate();
        }
    }
}

    
    
    

