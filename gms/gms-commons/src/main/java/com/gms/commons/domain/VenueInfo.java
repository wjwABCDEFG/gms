package com.gms.commons.domain;

public class VenueInfo {
    private int id;
    private int cost;
    private String period;
    private int status;
    private String venueClass;
    private String position;
    private VenueCharge venueCharge;

    public VenueInfo(){

    }

    public VenueInfo(int id, int cost, String period, int status, String venueClass, String position, VenueCharge venueCharge) {
        this.id = id;
        this.cost = cost;
        this.period = period;
        this.status = status;
        this.venueClass = venueClass;
        this.position = position;
        this.venueCharge = venueCharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public VenueCharge getVenueCharge() {
        return venueCharge;
    }

    public void setVenueCharge(VenueCharge venueCharge) {
        this.venueCharge = venueCharge;
    }

    @Override
    public String toString() {
        return "VenueInfo{" +
                "id=" + id +
                ", cost=" + cost +
                ", period='" + period + '\'' +
                ", status=" + status +
                ", venueClass='" + venueClass + '\'' +
                ", position='" + position + '\'' +
                ", venueCharge=" + venueCharge +
                '}';
    }
}
