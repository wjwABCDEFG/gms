package com.gms.commons.domain;

public class VenueAdd {
    private int classId;
    private int positionId;
    private int chargePersonId;
    private int statusId;

    public VenueAdd(){

    }

    public VenueAdd(int classId, int positionId, int chargePersonId, int statusId) {
        this.classId = classId;
        this.positionId = positionId;
        this.chargePersonId = chargePersonId;
        this.statusId = statusId;
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
}
