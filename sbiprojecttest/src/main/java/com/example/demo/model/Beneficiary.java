package com.example.demo.model;

// import java.sql.Date;

// import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beneficiaries")

public class Beneficiary {
    @Id
    private String accountNo;
    @Column(name = "receiverAccNo")
    private long receiverAccNo;
    @Column(name = "nickname")
    private double nickname;
    @Column(name = "name")
    private String name;

    public Beneficiary() {

    }

    public Beneficiary(String accountNo, long receiverAccNo, double nickname, String name) {
        super();
        this.accountNo = accountNo;
        this.receiverAccNo = receiverAccNo;
        this.nickname = nickname;
        this.name = name;

    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public long getReceiverAccNo() {
        return receiverAccNo;
    }

    public void setReceiverAccNo(long receiverAccNo) {
        this.receiverAccNo = receiverAccNo;
    }

    public double getNickname() {
        return nickname;
    }

    public void setNickname(double nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}