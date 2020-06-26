package com.gms.commons.domain;

public class VenueApp {
    public int id;
    public String code;
    public String venueClass;
    public String position;
    public String period;
    public String username;
    public String submitTime;
    public String appTime;
    public String status;

    public VenueApp(){

    }

    public VenueApp(int id, String code, String venueClass, String position, String period, String username, String submitTime, String appTime, String status) {
        this.id = id;
        this.code = code;
        this.venueClass = venueClass;
        this.position = position;
        this.period = period;
        this.username = username;
        this.submitTime = submitTime;
        this.appTime = appTime;
        this.status = status;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
