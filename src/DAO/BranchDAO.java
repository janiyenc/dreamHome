/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import database.databaseConnection;
import MODEL.Branch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO {
    
    
    
    // Create (Insert)
  public boolean addBranch(Branch branch) {
    String checkQuery = "SELECT COUNT(*) FROM branch WHERE branchNo = ?";
    String insertQuery = "INSERT INTO branch (branchNo, street, city, postcode) VALUES (?, ?, ?, ?)";

    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
        
        checkStmt.setString(1, branch.getBranchNo());
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            System.err.println("Branch with branchNo " + branch.getBranchNo() + " already exists. Skipping insert.");
            return false;
        }

        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, branch.getBranchNo());
            insertStmt.setString(2, branch.getStreet());
            insertStmt.setString(3, branch.getCity());
            insertStmt.setString(4, branch.getPostcode());
            return insertStmt.executeUpdate() > 0;
        }

    } catch (SQLException e) {
        System.err.println("Error adding branch: " + e.getMessage());
        return false;
    }
}


    // Read (Retrieve all branches)
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String query = "SELECT * FROM branch";
        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Branch branch = new Branch(
                        rs.getString("branchNo"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("postcode")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching branches: " + e.getMessage());
        }
        return branches;
    }

    // Read (Retrieve a branch by branchNo)
    public Branch getBranchById(String branchNo) {
        String query = "SELECT * FROM branch WHERE branchNo = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branchNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Branch(
                        rs.getString("branchNo"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("postcode")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching branch: " + e.getMessage());
        }
        return null;
    }

    // Update
    public boolean updateBranch(Branch branch) {
        String query = "UPDATE branch SET street = ?, city = ?, postcode = ? WHERE branchNo = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branch.getStreet());
            stmt.setString(2, branch.getCity());
            stmt.setString(3, branch.getPostcode());
            stmt.setString(4, branch.getBranchNo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating branch: " + e.getMessage());
            return false;
        }
    }
public boolean branchExists(String branchNo) {
    String sql = "SELECT COUNT(*) FROM branch WHERE branchNo = ?";
    
    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, branchNo);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Returns true if branch exists
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false; // Returns false if branch does not exist or an error occurs
}
public boolean hasRelatedProperties(String branchNo) {
    String sql = "SELECT COUNT(*) FROM propertyforrent WHERE branchNo = ?";
    
    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, branchNo);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Returns true if properties are linked to the branch
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false; // Returns false if no related properties or an error occurs
}

    // Delete
  public boolean deleteBranch(String branchNo) {
    Connection conn = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    
    try {
        conn = databaseConnection.getConnection();
        conn.setAutoCommit(false); // Start transaction

        // Delete related properties in propertyforrent
        String deletePropertiesSQL = "DELETE FROM propertyforrent WHERE branchNo = ?";
        stmt1 = conn.prepareStatement(deletePropertiesSQL);
        stmt1.setString(1, branchNo);
        stmt1.executeUpdate();

        // Now delete the branch
        String deleteBranchSQL = "DELETE FROM branch WHERE branchNo = ?";
        stmt2 = conn.prepareStatement(deleteBranchSQL);
        stmt2.setString(1, branchNo);
        int affectedRows = stmt2.executeUpdate();

        conn.commit(); // Commit transaction
        return affectedRows > 0;
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Rollback if any error occurs
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        try {
            if (stmt1 != null) stmt1.close();
            if (stmt2 != null) stmt2.close();
            if (conn != null) conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return false;
}


}
