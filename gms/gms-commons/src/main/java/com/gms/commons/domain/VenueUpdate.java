package com.gms.commons.domain;

public class VenueUpdate {
    private int classId;
    private int positionId;
    private int chargePersonId;
    private int statusId;
    private int venueId;

    public VenueUpdate() {
    }

    public VenueUpdate(int classId, int positionId, int chargePersonId, int statusId, int venueId) {
        this.classId = classId;
        this.positionId = positionId;
        this.chargePersonId = chargePersonId;
        this.statusId = statusId;
        this.venueId = venueId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getChargePersonId() {
        return chargePersonId;
    }

    public void setChargePersonId(int chargePersonId) {
        this.chargePersonId = chargePersonId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}
