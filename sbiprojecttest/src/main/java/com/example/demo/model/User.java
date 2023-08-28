package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accountHolders")
public class User {
    @Id
    private String customerID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "accountNo")
    private String accountNo;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "currentAddress")
    private String currentAddress;

    @Column(name = "permanentAddress")
    private String permanentAddress;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "contactNo")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "aadharNo")
    private String aadharNo;

    @Column(name = "panNo")
    private String panNo;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "minAccountBalance")
    private double minAccountBalance;

    public User() {
    }

    public User(String CustomerID, String FirstName, String LastName, String AccountNo , String MiddleName, String CurrentAddress,
            String PermanentAddress, Date DOB, String ContactNo, String Email, String AadharNo, String PanNo,
            String Occupation, double MinAccountBalance) {
        super();
        this.customerID = CustomerID;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.accountNo = AccountNo;
        this.middleName = MiddleName;
        this.currentAddress = CurrentAddress;
        this.permanentAddress = PermanentAddress;
        this.dob = DOB;
        this.contactNo = ContactNo;
        this.email = Email;
        this.aadharNo = AadharNo;
        this.panNo = PanNo;
        this.occupation = Occupation;
        this.minAccountBalance = MinAccountBalance;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String CustomerID) {
        this.customerID = CustomerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.accountNo = AccountNo;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String MiddleName) {
        this.middleName = MiddleName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String CurrentAddress) {
        this.currentAddress = CurrentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String PermanentAddress) {
        this.permanentAddress = PermanentAddress;
    }

    public Date getDOB() {
        return dob;
    }

    public void setDOB(Date DOB) {
        this.dob = DOB;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String ContactNo) {
        this.contactNo = ContactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String AadharNo) {
        this.aadharNo = AadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String PanNo) {
        this.panNo = PanNo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String Occupation) {
        this.occupation = Occupation;
    }

    public double getMinAccountBalance() {
        return minAccountBalance;
    }

    public void setMinAccountBalance(double MinAccountBalance) {
        this.minAccountBalance = MinAccountBalance;
    }

}
