package com.gms.commons.domain;

public class appMissInfo {
    public int id;
    public String code;
    public String venueClass;
    public String position;
    public String username;
    public int userId;
    public String period;
    public String contestTime;
    public int appStatus;
    public int days;

    public appMissInfo() {
    }

    public appMissInfo(int id, String code, String venueClass, String position, String username, int userId, String period, String contestTime, int appStatus, int days) {
        this.id = id;
        this.code = code;
        this.venueClass = venueClass;
        this.position = position;
        this.username = username;
        this.userId = userId;
        this.period = period;
        this.contestTime = contestTime;
        this.appStatus = appStatus;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVenueClass() {
        return venueClass;
    }

    public void setVenueClass(String venueClass) {
        this.venueClass = venueClass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getContestTime() {
        return contestTime;
    }

    public void setContestTime(String contestTime) {
        this.contestTime = contestTime;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }
}
