/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;


import java.math.BigDecimal;
import java.sql.Date;






public class Staff {
    private String No;
    private String fName;
    private String lName;
    private String position;
    private String sex;
    private Date dob;
    private BigDecimal salary;
    private String branchNo;

    public Staff(String No, String fName, String lName, String position, String sex, Date dob, BigDecimal salary, String branchNo) {
        this.No = No;
        this.fName = fName;
        this.lName = lName;
        this.position = position;
        this.sex = sex;
        this.dob = dob;
        this.salary = salary;
        this.branchNo = branchNo;
    }

    // Getters and Setters
    public String getNo() { return No; }
    public void setNo(String No) { this.No = No; }

    public String getfName() { return fName; }
    public void setfName(String fName) { this.fName = fName; }

    public String getlName() { return lName; }
    public void setlName(String lName) { this.lName = lName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public String getBranchNo() { return branchNo; }
    public void setBranchNo(String branchNo) { this.branchNo = branchNo; }
}
