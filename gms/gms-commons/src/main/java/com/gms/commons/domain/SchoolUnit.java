package com.gms.commons.domain;

public class SchoolUnit {
    public int id;
    public String name;
    public String unitClass;

    public SchoolUnit() {
    }

    public SchoolUnit(int id, String name, String unitClass) {
        this.id = id;
        this.name = name;
        this.unitClass = unitClass;
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

    public String getUnitClass() {
        return unitClass;
    }

    public void setUnitClass(String unitClass) {
        this.unitClass = unitClass;
    }
}
