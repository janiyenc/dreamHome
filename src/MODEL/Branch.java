/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Windows
 */
public class Branch {
    
    private String branchNo;
    private String street;
    private String city;
    private String postcode;

    public Branch() {}

    public Branch(String branchNo, String street, String city, String postcode) {
        this.branchNo = branchNo;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }

    public String getBranchNo() { return branchNo; }
    public void setBranchNo(String branchNo) { this.branchNo = branchNo; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    @Override
    public String toString() {
        return "Branch [branchNo=" + branchNo + ", street=" + street + ", city=" + city + ", postcode=" + postcode + "]";
    }
}


