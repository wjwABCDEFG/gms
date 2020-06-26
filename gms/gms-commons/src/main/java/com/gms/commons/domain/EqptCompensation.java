package com.gms.commons.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class EqptCompensation {
    private int compensateID;
    private String  eqptID;
    private String studentID;
    private String cpstStatus;
    private int indemnity;

    @JSONField(format = "yyyy-MM-dd")
    private Date submissionTime;

    public EqptCompensation() {
    }

    public EqptCompensation(int compensateID, String eqptID, String studentID, String cpstStatus, int indemnity, Date submissionTime) {
        this.compensateID = compensateID;
        this.eqptID = eqptID;
        this.studentID = studentID;
        this.cpstStatus = cpstStatus;
        this.indemnity = indemnity;
        this.submissionTime = submissionTime;
    }

    public int getCompensateID() {
        return compensateID;
    }

    public void setCompensateID(int compensateID) {
        this.compensateID = compensateID;
    }

    public String getEqptID() {
        return eqptID;
    }

    public void setEqptID(String eqptID) {
        this.eqptID = eqptID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCpstStatus() {
        return cpstStatus;
    }

    public void setCpstStatus(String cpstStatus) {
        this.cpstStatus = cpstStatus;
    }

    public int getIndemnity() {
        return indemnity;
    }

    public void setIndemnity(int indemnity) {
        this.indemnity = indemnity;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Override
    public String toString() {
        return "EqptCompensation{" +
                "compensateID=" + compensateID +
                ", eqptID='" + eqptID + '\'' +
                ", studentID='" + studentID + '\'' +
                ", cpstStatus='" + cpstStatus + '\'' +
                ", indemnity=" + indemnity +
                ", SubmissionTime=" + submissionTime +
                '}';
    }
}
