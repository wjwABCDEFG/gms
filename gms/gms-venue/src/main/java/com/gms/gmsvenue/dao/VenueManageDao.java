package com.gms.gmsvenue.dao;

import com.gms.commons.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface VenueManageDao {

    public List<VenueInfo> getAllVenueInfo();  // 查询所有场地信息接口

    public VenueInfo getVenueInfoById(int id);  // 查询所有场地信息接口

    public List<VenueInfo> getAllVenueInfoByClassId(int classId);  // 查询所有场地信息接口

    public List<appMissInfo> getAllAppMissInfo();  // 获取所有失约信息

    public List<appMissInfo> getAllDealInfo();

    public void deleteVenueInfoById(int id);  // 通过id删除场地信息接口

    public List<Venue> getAllVenue();   // 查询所有场地接口

    public void updateVenue(VenueUpdate venueUpdate); // 更新场地信息

    public void deleteVenueById(int id);   // 通过id删除场地接口

    public void deleteSpecialVenueInfoById(int id);

    public List<VenueClass> getAllVenueClass();  // 查询所有场地类别接口

    public List<SchoolUnit> getAllSchoolUnit(int tabPosition);  // 查询所有学校单位

    public List<VenuePosition> getAllVenuePosition();  // 查询所有位置信息接口

    public List<VenueCharge> getAllVenueCharge();  // 查询所有的场地管理员

    public List<VenuePeriod> getAllVenuePeriod();

    public List<specialVenueInfo> getAllSpecialVenueInfo(int unitClassId);

    public int countVenueAppByStatusId(int statusId);

    public int countDealInfo();

    public List<VenueApp> getVenueAppByStatusId(int statusId);

    public ContestAppInfo getContestAppInfoByCode(String code);

    public boolean checkAppInfoIfconflicts(int venueInfoId, String contestTime);

    public boolean checkAppInfoIfSpecial(int venueInfoId, String week);

    public boolean checkSpecialVenueAdd(int venueInfoId, int weekId);

    public boolean checkAppPassword(String code, String password);

    public void updateAppinfoStatus(String code, int statusId);

    public int getUserIdByName(String username);

    public void addVenue(VenueAdd venueAdd);

    public void addDealInfo(NewDealinfo newDealinfo);

    public void addVenueInfo(VenueInfoAdd venueInfoAdd);

    public void addVenueAppInfo(VenueAppInfo venueAppInfo);

    public void addSpecialVenueInfo(SpecialVenueInfoAdd specialVenueInfoAdd);




}



