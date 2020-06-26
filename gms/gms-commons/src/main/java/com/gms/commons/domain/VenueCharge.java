package com.gms.commons.domain;

public class VenueCharge {
    private int id;
    private String name;
    private String unit;
    private String workTime;
    private String phone;

    public VenueCharge(){

    }

    public VenueCharge(int id, String name, String unit, String workTime, String phone) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.workTime = workTime;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "VenueCharge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", workTime='" + workTime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
