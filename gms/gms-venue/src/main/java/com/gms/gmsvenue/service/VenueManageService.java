package com.gms.gmsvenue.service;


import com.gms.commons.domain.*;
import com.gms.commons.utils.DateConvertUtil;
import com.gms.commons.utils.Encrypt;
import com.gms.gmsvenue.dao.VenueManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
//@ComponentScan(basePackages = "com.gms.gmsvenue")
public class VenueManageService {

    @Autowired
    VenueManageDao venueManageDao;

    // 获取所有的场地信息
    public List<VenueInfo> getAllVenueInfo(){
        List<VenueInfo> venueInfo = venueManageDao.getAllVenueInfo();
        System.out.println(venueInfo);
        return venueInfo;

    }

    public List<appMissInfo> getAllAppMissInfo() throws ParseException {
        Date contestTime;
        List<appMissInfo> newList = new ArrayList<>();
        List<appMissInfo> appMissInfos = venueManageDao.getAllAppMissInfo();
        for(int i=0;i<appMissInfos.size();i++){
            contestTime = DateConvertUtil.string2Date(appMissInfos.get(i).getContestTime());
            Date date = new Date();// 获取当前时间
            int days = (int) ((date.getTime() - contestTime.getTime()) / (1000 * 60 * 60 * 24));
            if(days > 3){
                appMissInfos.get(i).setAppStatus(2);
                appMissInfos.get(i).setDays(days);
                newList.add(appMissInfos.get(i));
            }else {
//                appMissInfos.get(i).setAppStatus(1);
            }
        }
        System.out.println("失约信息查询成功");
        return  newList;
    }

    public List<appMissInfo> getAllDealInfo() throws ParseException {
        Date contestTime;
        List<appMissInfo> newList = new ArrayList<>();
        List<appMissInfo> appMissInfos = venueManageDao.getAllDealInfo();
        for(int i=0;i<appMissInfos.size();i++){
            contestTime = DateConvertUtil.string2Date(appMissInfos.get(i).getContestTime());
            Date date = new Date();// 获取当前时间
            int days = (int) ((date.getTime() - contestTime.getTime()) / (1000 * 60 * 60 * 24));
            if(days > 3){
                appMissInfos.get(i).setAppStatus(2);
                appMissInfos.get(i).setDays(days);
                newList.add(appMissInfos.get(i));
            }else {
//                appMissInfos.get(i).setAppStatus(1);
            }
        }
        System.out.println("处理信息查询成功");
        return  newList;
    }

    // 通过id获取所有的场地信息
    public VenueInfo getVenueInfoById(int id){

        VenueInfo venueInfo = venueManageDao.getVenueInfoById(id);
        System.out.println(venueInfo);

        return venueInfo;

    }

    // 获取所有的场地信息
    public List<VenueInfo> getAllVenueInfoByClassId(int classId){

        List<VenueInfo> venueInfo = venueManageDao.getAllVenueInfoByClassId(classId);
        System.out.println(venueInfo);

        return venueInfo;

    }

    // 获取所有的学校单位
    public List<SchoolUnit> getAllSchoolUnit(int tabPosition) {

        List<SchoolUnit> venueInfo = venueManageDao.getAllSchoolUnit(tabPosition);
        System.out.println("获取学校单位成功");

        return venueInfo;
    }

    // 获取所有的特殊场地信息
    public List<specialVenueInfo> getAllSpecialVenueInfo(int unitClassId){

        List<specialVenueInfo> venueInfos = venueManageDao.getAllSpecialVenueInfo(unitClassId);
        System.out.println("获取特殊场地信息成功");

        return venueInfos;

    }

    // 根据审批号获取赛事信息
    public ContestAppInfo getContestAppInfoByCode(String code) throws ParseException {
        ContestAppInfo contestAppInfo = venueManageDao.getContestAppInfoByCode(code);
//        contestAppInfo.setContestTime(DateConvertUtil.string2Date(contestAppInfo.getContestTimeStr(), "yyyy-MM-dd"));
        System.out.println("获取赛事申请信息成功");
        return contestAppInfo;
    }

    public List<VenueApp> getVenueAppByStatusId(int statusId){
        List<VenueApp> venueApps;
        List<VenueApp> venueAppsAdd;
        if(statusId == 3){
            venueApps = venueManageDao.getVenueAppByStatusId(statusId);
            venueAppsAdd = venueManageDao.getVenueAppByStatusId(statusId+1);
            venueApps.addAll(venueAppsAdd);
        }else {
            venueApps = venueManageDao.getVenueAppByStatusId(statusId);
        }
        System.out.println("获取场地申请信息成功");
        return venueApps;
    }

    // 通过id设置场地信息状态为9，即删除状态
    public void deleteVenueInfoById(int id){
        venueManageDao.deleteVenueInfoById(id);
        System.out.println("场地信息删除成功");
    }

    // 通过id删除特殊场地信息
    public void deleteSpecialVenueInfoById(int id){
        venueManageDao.deleteSpecialVenueInfoById(id);
        System.out.println("删除特殊场地成功");
    }

    // 通过状态id计算场地申请数
    public int countVenueAppByStatusId(int statusId){
        int i;
        if(statusId == 3){
            i = venueManageDao.countVenueAppByStatusId(statusId) + venueManageDao.countVenueAppByStatusId(statusId+1);
        }else {
            i = venueManageDao.countVenueAppByStatusId(statusId);
        };
        System.out.println("场地申请信息计数成功");
        return i;
    }
    // 通过状态id计算场地申请数
    public int countDealInfo(){
        int i = venueManageDao.countDealInfo();
        System.out.println("处理计数成功");
        return i;
    }

    // 检查场地申请信息是否冲突
    public boolean checkAppInfoIfconflicts(int venueInfoId, String contestTime){
        boolean flag = venueManageDao.checkAppInfoIfconflicts(venueInfoId, contestTime);
        if(flag) {
            System.out.println("场地申请信息检验不冲突");
        }
        return flag;
    }

    // 检查场地申请是否与特殊场地冲突
    public boolean checkAppInfoIfSpecial(int venueInfoId, String week){
        boolean flag = venueManageDao.checkAppInfoIfSpecial(venueInfoId, week);
        if(flag) {
            System.out.println("场地申请信息与特殊场地冲突");
        }
        return flag;
    }


    // 获取所有的场地列表
    public List<Venue> getAllVenue(){
        List<Venue> venues = venueManageDao.getAllVenue();
        System.out.println(venues);
        return venues;
    }

    public void deleteVenueById(int id){
        venueManageDao.deleteVenueById(id);
        System.out.println("场地删除成功");

    }

    public void updateVenue(VenueUpdate venueUpdate){
        venueManageDao.updateVenue(venueUpdate);
        System.out.println("场地信息更新成功");;
    }

    public List<VenueClass> getAllVenueClass(){
        List<VenueClass> classes = venueManageDao.getAllVenueClass();
        System.out.println("获取场地类别数据成功");
        return classes;
    }

    public List<VenuePosition> getAllVenuePosition(){
        List<VenuePosition> positions = venueManageDao.getAllVenuePosition();
        System.out.println("获取场地位置信息成功");
        return positions;
    }

    public List<VenuePeriod> getAllVenuePeriod(){
        List<VenuePeriod> positions = venueManageDao.getAllVenuePeriod();
        System.out.println("获取时间段信息成功");
        return positions;
    }

    public List<VenueCharge> getAllVenueCharge(){
        List<VenueCharge> venueCharges = venueManageDao.getAllVenueCharge();
        System.out.println("获取场地管理员信息成功");
        return venueCharges;
    }

    // 通过用户姓名获取id
    public int getUserIdByName(String username){
        int userId = venueManageDao.getUserIdByName(username);
        System.out.println("获取用户id成功");
        return userId;
    }

    // 新增场地
    public void addVenue(VenueAdd venueAdd){
        venueManageDao.addVenue(venueAdd);
        System.out.println("新增场地成功");
    }

    // 新增场地
    public void addDealInfo(NewDealinfo newDealinfo){
        Date date = new Date();// 获取当前时间
        newDealinfo.setDealTime(DateConvertUtil.date2String(date, "yyyy-MM-dd HH:mm:ss"));
        venueManageDao.addDealInfo(newDealinfo);
        System.out.println("新增处理信息成功");
    }

    // 新增场地信息
    public void addVenueInfo(VenueInfoAdd venueInfoAdd){
        venueManageDao.addVenueInfo(venueInfoAdd);
        System.out.println("新增场地信息成功");
    }

    public boolean updateAppinfoPayStatus(String code, String password){
        String passwordS = Encrypt.Encrypt_md5(password);
        boolean flag = venueManageDao.checkAppPassword(code, passwordS);
        if(flag==true){
            venueManageDao.updateAppinfoStatus(code, 2);
        }
        return flag;
    }

    public boolean addSpecialVenueInfo(SpecialVenueInfoAdd specialVenueInfoAdd){
        boolean flag = venueManageDao.checkSpecialVenueAdd(specialVenueInfoAdd.getVenueInfoId(), specialVenueInfoAdd.getWeekId());
        if(flag==false){
            venueManageDao.addSpecialVenueInfo(specialVenueInfoAdd);
        }
        return flag;
    }

    public void updateAppinfoStatus(String code, int statusId){
        venueManageDao.updateAppinfoStatus(code, statusId);
        System.out.println("场地申请信息更新成功");
    }

    // 新增场地申请信息
    public void addVenueAppInfo(VenueAppInfo venueAppInfo){
        venueManageDao.addVenueAppInfo(venueAppInfo);
        System.out.println("新增场地申请信息成功");
    }
}
