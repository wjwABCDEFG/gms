package com.gms.commons.domain;

import java.util.Date;

public class ContestAppInfo {
    private String appRecordCode;
    private String activityType;
    private int userId;
    private String userName;
    private Date contestTime;
    private String contestTimeStr;

    public ContestAppInfo(){

    }

    public ContestAppInfo(String appRecordCode, String activityType, int userId, String userName, Date contestTime, String contestTimeStr) {
        this.appRecordCode = appRecordCode;
        this.activityType = activityType;
        this.userId = userId;
        this.userName = userName;
        this.contestTime = contestTime;
        this.contestTimeStr = contestTimeStr;
    }

    public String getAppRecordCode() {
        return appRecordCode;
    }

    public void setAppRecordCode(String appRecordCode) {
        this.appRecordCode = appRecordCode;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getContestTime() {
        return contestTime;
    }

    public void setContestTime(Date contestTime) {
        this.contestTime = contestTime;
    }

    public String getContestTimeStr() {
        return contestTimeStr;
    }

    public void setContestTimeStr(String contestTimeStr) {
        this.contestTimeStr = contestTimeStr;
    }
}


