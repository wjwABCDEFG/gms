package com.gms.commons.domain;

import java.util.List;

public class EqptType {
    private String eqptName;
    private int unitPrice;
    private int totalQty;
    private List<Equipment> equipment;

    public EqptType() {
    }

    public EqptType(String eqptName, int unitPrice, int totalQty, List<Equipment> equipment) {
        this.eqptName = eqptName;
        this.unitPrice = unitPrice;
        this.totalQty = totalQty;
        this.equipment = equipment;
    }

    public String getEqptName() {
        return eqptName;
    }

    public void setEqptName(String eqptName) {
        this.eqptName = eqptName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    @Override
    public String toString() {
        return "EqptType{" +
                "eqptName='" + eqptName + '\'' +
                ", unitPrice=" + unitPrice +
                ", totalQty=" + totalQty +
                '}';
    }
}
