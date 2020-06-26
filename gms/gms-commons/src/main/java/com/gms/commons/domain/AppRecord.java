/**
 * @author wjw
 * @date 2020/6/17 3:27
 */
package com.gms.commons.domain;

import javax.persistence.*;

//TODO 到时候这个实体类加上泽文和淑勤的申请表实体类
@Table(name = "app_record")
public class AppRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer contestAppId;
    private Integer venueAppId;
    private Integer eqptAppId;

    @Transient
    private CtstApplication ctstApplication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestAppId() {
        return contestAppId;
    }

    public void setContestAppId(Integer contestAppId) {
        this.contestAppId = contestAppId;
    }

    public Integer getVenueAppId() {
        return venueAppId;
    }

    public void setVenueAppId(Integer venueAppId) {
        this.venueAppId = venueAppId;
    }

    public Integer getEqptAppId() {
        return eqptAppId;
    }

    public void setEqptAppId(Integer eqptAppId) {
        this.eqptAppId = eqptAppId;
    }

    public CtstApplication getCtstApplication() {
        return ctstApplication;
    }

    public void setCtstApplication(CtstApplication ctstApplication) {
        this.ctstApplication = ctstApplication;
    }
}
