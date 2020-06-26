package com.gms.commons.domain;

public class VenueAppInfo {
    private String code;
    private int venueId;
    private String reason;
    private String submitTime;
    private int statusId;


    public VenueAppInfo(){

    }

    public VenueAppInfo(String code, int venueId, String reason, String submitTime, int statusId) {
        this.code = code;
        this.venueId = venueId;
        this.reason = reason;
        this.submitTime = submitTime;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
