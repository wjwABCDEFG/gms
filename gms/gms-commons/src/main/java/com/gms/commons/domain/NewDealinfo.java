package com.gms.commons.domain;

public class NewDealinfo {
    public int venueAppId;
    public int userId;
    public int classId;
    public String dealTime;

    public NewDealinfo() {
    }

    public NewDealinfo(int venueAppId, int userId, int classId, String dealTime) {
        this.venueAppId = venueAppId;
        this.userId = userId;
        this.classId = classId;
        this.dealTime = dealTime;
    }

    public int getVenueAppId() {
        return venueAppId;
    }

    public void setVenueAppId(int venueAppId) {
        this.venueAppId = venueAppId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
