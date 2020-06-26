package com.gms.commons.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class EqptRent {
    private int OrderID;
    private String eqptName;
    private int Amount;
    private String studentID;
    private String Tenant;
    private String Telphone;
    private int payAmount;
    private String  verifyState;

    @JSONField(format="yyyy-MM-dd")
    private Date rentTime;

    @JSONField(format="yyyy-MM-dd")
    private Date apprTime;

    public EqptRent() {
    }

    public EqptRent(int orderID, String eqptName, int amount, String studentID, String tenant, String telphone, int payAmount, String verifyState, Date rentTime, Date apprTime) {
        OrderID = orderID;
        this.eqptName = eqptName;
        Amount = amount;
        this.studentID = studentID;
        Tenant = tenant;
        Telphone = telphone;
        this.payAmount = payAmount;
        this.verifyState = verifyState;
        this.rentTime = rentTime;
        this.apprTime = apprTime;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getEqptName() {
        return eqptName;
    }

    public void setEqptName(String eqptName) {
        this.eqptName = eqptName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTenant() {
        return Tenant;
    }

    public void setTenant(String tenant) {
        Tenant = tenant;
    }

    public String getTelphone() {
        return Telphone;
    }

    public void setTelphone(String telphone) {
        Telphone = telphone;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public Date getApprTime() {
        return apprTime;
    }


    public void setApprTime(Date apprTime) {
        this.apprTime = apprTime;
    }

    public String getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState;
    }

    @Override
    public String toString() {
        return "EqptRent{" +
                "OrderID=" + OrderID +
                ", eqptName='" + eqptName + '\'' +
                ", studentID='" + studentID + '\'' +
                ", Tenant='" + Tenant + '\'' +
                ", Telphone='" + Telphone + '\'' +
                ", Amount=" + Amount +
                ", payAmount=" + payAmount +
                ", rentTime=" + rentTime +
                ", apprTime=" + apprTime +
                ", verifyState='" + verifyState + '\'' +
                '}';
    }
}
