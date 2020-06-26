package com.gms.commons.domain;

public class SpecialVenueInfoAdd {
    public int venueInfoId;
    public int schoolUnitId;
    public int weekId;

    public SpecialVenueInfoAdd() {
    }

    public SpecialVenueInfoAdd(int venueInfoId, int schoolUnitId, int weekId) {
        this.venueInfoId = venueInfoId;
        this.schoolUnitId = schoolUnitId;
        this.weekId = weekId;
    }

    public int getVenueInfoId() {
        return venueInfoId;
    }

    public void setVenueInfoId(int venueInfoId) {
        this.venueInfoId = venueInfoId;
    }

    public int getSchoolUnitId() {
        return schoolUnitId;
    }

    public void setSchoolUnitId(int schoolUnitId) {
        this.schoolUnitId = schoolUnitId;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
}
