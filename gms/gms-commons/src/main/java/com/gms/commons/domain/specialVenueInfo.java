package com.gms.commons.domain;

public class specialVenueInfo {
    private int id;
    private String period;
    private int status;
    private String venueClass;
    private String position;
    private int weekId;
    private String useUnit;

    public specialVenueInfo() {
    }

    public specialVenueInfo(int id, String period, int status, String venueClass, String position, int weekId, String useUnit) {
        this.id = id;
        this.period = period;
        this.status = status;
        this.venueClass = venueClass;
        this.position = position;
        this.weekId = weekId;
        this.useUnit = useUnit;
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

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public String getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(String useUnit) {
        this.useUnit = useUnit;
    }
}
