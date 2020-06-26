package com.gms.commons.domain;

public class VenuePeriod {
    private int id;
    private String period;

    public VenuePeriod(){

    }
    public VenuePeriod(int id, String period) {
        this.id = id;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
