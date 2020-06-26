package com.gms.commons.domain;

public class VenueInfoAdd {

    private int venueId;
    private int periodId;
    private int statusId;
    private int cost;

    public VenueInfoAdd(){

    }

    public VenueInfoAdd(int venueId, int periodId, int statusId, int cost) {
        this.venueId = venueId;
        this.periodId = periodId;
        this.statusId = statusId;
        this.cost = cost;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
