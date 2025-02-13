/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

public class Staff {
    private String staffNo;
    private String fName;
    private String lName;
    private String position;
    private String sex;
    private String DOB;
    private double salary;
    private String branchNo;  // Link to Branch

    // Constructor
    public Staff(String staffNo, String fName, String lName, String position, String sex, String DOB, double salary, String branchNo) {
        this.staffNo = staffNo;
        this.fName = fName;
        this.lName = lName;
        this.position = position;
        this.sex = sex;
        this.DOB = DOB;
        this.salary = salary;
        this.branchNo = branchNo;
    }

    // Getters and Setters
    public String getStaffNo() { return staffNo; }
    public void setStaffNo(String staffNo) { this.staffNo = staffNo; }

    public String getFName() { return fName; }
    public void setFName(String fName) { this.fName = fName; }

    public String getLName() { return lName; }
    public void setLName(String lName) { this.lName = lName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getDOB() { return DOB; }
    public void setDOB(String DOB) { this.DOB = DOB; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getBranchNo() { return branchNo; }
    public void setBranchNo(String branchNo) { this.branchNo = branchNo; }
}
