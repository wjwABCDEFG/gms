package com.gms.commons.domain;

public class VenueClass {
    private int id;
    private String VenueClass;

    public VenueClass() {
    }

    public VenueClass(int id, String venueClass) {
        this.id = id;
        VenueClass = venueClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueClass() {
        return VenueClass;
    }

    public void setVenueClass(String venueClass) {
        VenueClass = venueClass;
    }
}
