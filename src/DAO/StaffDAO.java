package DAO;

import database.databaseConnection;
import java.sql.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.databaseConnection;  // Make sure the package path matches your project structure
import MODEL.Staff;  // Ensure the model class is in the correct package (adjust the path as needed)


public class StaffDAO {

    // Method to save (update) staff record
    public boolean saveStaff(Staff staff) {
        String sql = "UPDATE staff SET fName = ?, lName = ?, position = ?, sex = ?, DOB = ?, salary = ?, branchNo = ? WHERE staffNo = ?";
        
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set parameters for the SQL update query
            stmt.setString(1, staff.getFName());
            stmt.setString(2, staff.getLName());
            stmt.setString(3, staff.getPosition());
            stmt.setString(4, staff.getSex());
            stmt.setString(5, staff.getDOB());
            stmt.setDouble(6, staff.getSalary());
            stmt.setString(7, staff.getBranchNo());  // Set the branchNo for the staff
            stmt.setString(8, staff.getStaffNo());  // Update based on staffNo
            
            int rowsUpdated = stmt.executeUpdate();
            
            return rowsUpdated > 0;  // Returns true if update is successful
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
        }
        return false;  // Returns false if the update failed
    }

    // Method to get branchNo based on city name
    public String getBranchNoByCity(String city) {
        BranchDAO branchDAO = new BranchDAO();
        try {
            return branchDAO.getBranchNoByCity(city);  // Use BranchDAO's method to fetch branchNo
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception if any error occurs
        }
        return null;
    }
}
