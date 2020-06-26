package com.gms.commons.domain;

public class Venue {
    private int id;
    private String venueClass;
    private int classId;
    private int positionId;
    private String position;
    private int status;
    private VenueCharge venueCharge;

    public Venue(){

    }

    public Venue(int id, String venueClass, int classId, int positionId, String position, int status, VenueCharge venueCharge) {
        this.id = id;
        this.venueClass = venueClass;
        this.classId = classId;
        this.positionId = positionId;
        this.position = position;
        this.status = status;
        this.venueCharge = venueCharge;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public VenueCharge getVenueCharge() {
        return venueCharge;
    }

    public void setVenueCharge(VenueCharge venueCharge) {
        this.venueCharge = venueCharge;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", venueClass='" + venueClass + '\'' +
                ", position='" + position + '\'' +
                ", status=" + status +
                ", venueCharge=" + venueCharge +
                '}';
    }
}
