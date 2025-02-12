/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import DAO.BranchDAO;
import MODEL.Branch;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BranchDAO branchDAO = new BranchDAO();

        // CREATE
        Branch newBranch = new Branch("B009", "123 Elm Street", "New York", "10001");
        if (branchDAO.addBranch(newBranch)) {
            System.out.println("Branch added successfully.");
        }

        // READ (All branches)
        List<Branch> branches = branchDAO.getAllBranches();
        System.out.println("\nAll Branches:");
        for (Branch b : branches) {
            System.out.println(b);
        }

        // READ (Single branch)
        Branch branch = branchDAO.getBranchById("B001");
        if (branch != null) {
            System.out.println("\nFetched Branch: " + branch);
        }

        // UPDATE
        if (branch != null) {
            branch.setStreet("456 Oak Avenue");
            branch.setCity("Los Angeles");
            if (branchDAO.updateBranch(branch)) {
                System.out.println("\nBranch updated successfully.");
            }
        }

        // DELETE
        if (branchDAO.deleteBranch("B001")) {
            System.out.println("Branch deleted successfully.");
        } else {
            System.out.println("Error deleting branch.");
        }
    }
}
