package com.gms.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

//器材信息
@JsonInclude(JsonInclude.Include.NON_EMPTY )
public class Equipment {
    private String eqptID;
    private String eqptName;
    private String rentalStatus;
    private String eqptStatus;

    public Equipment() {
    }

    public Equipment(String eqptID, String eqptName, String rentalStatus, String eqptStatus) {
        this.eqptID = eqptID;
        this.eqptName = eqptName;
        this.rentalStatus = rentalStatus;
        this.eqptStatus = eqptStatus;
    }

    public String getEqptID() {
        return eqptID;
    }

    public void setEqptID(String eqptID) {
        this.eqptID = eqptID;
    }

    public String getEqptName() {
        return eqptName;
    }

    public void setEqptName(String eqptName) {
        this.eqptName = eqptName;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getEqptStatus() {
        return eqptStatus;
    }

    public void setEqptStatus(String eqptStatus) {
        this.eqptStatus = eqptStatus;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "eqptID='" + eqptID + '\'' +
                ", eqptName='" + eqptName + '\'' +
                ", rentalStatus='" + rentalStatus + '\'' +
                ", eqptStatus='" + eqptStatus + '\'' +
                '}';
    }
}
