/**
 * @author wjw
 * @date 2020/6/9 22:05
 */
package com.gms.commons.domain;

import javax.persistence.*;

/**
 * 有个坑，命名不好容易和引导类重名，所以这里类名采用缩写
 */
@Table(name = "ctst_application")
public class CtstApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contestId;
    private Integer userId;
//    @Transient
//    private User user;
    private String activityType;
    private Integer sportId;
    @Transient
    private Sport sport;
    private String contestName;
    private String contestTeam;
    private String contestTime;
    private String activityDescription;
    private String appTime;
    private String appStatus;
    private String appRecordId;
    private String appRecordTime;
    private Integer refereeId;
    @Transient
    private Referee referee;

    public CtstApplication() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getContestTeam() {
        return contestTeam;
    }

    public void setContestTeam(String contestTeam) {
        this.contestTeam = contestTeam;
    }

    public String getContestTime() {
        return contestTime;
    }

    public void setContestTime(String contestTime) {
        this.contestTime = contestTime;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppRecordId() {
        return appRecordId;
    }

    public void setAppRecordId(String appRecordId) {
        this.appRecordId = appRecordId;
    }

    public String getAppRecordTime() {
        return appRecordTime;
    }

    public void setAppRecordTime(String appRecordTime) {
        this.appRecordTime = appRecordTime;
    }

    public Integer getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }
}
