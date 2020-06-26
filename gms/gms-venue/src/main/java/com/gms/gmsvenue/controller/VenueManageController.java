package com.gms.gmsvenue.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.*;
import com.gms.commons.utils.DateConvertUtil;
import com.gms.commons.utils.Result;
import com.gms.gmsvenue.service.VenueManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class VenueManageController {

    @Autowired
    VenueManageService venueManageService;


    @RequestMapping("/getVenueInfo")
    public String getVenueInfoList(){

        Result<List> result = new Result<>();
        List<VenueInfo> venueInfos = venueManageService.getAllVenueInfo();
        result.setCode(200);
        result.setMsg("返回场地信息");
        result.setData(venueInfos);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/getAllAppMissInfo")
    public String getAllAppMissInfo() throws ParseException {
        Result<List> result = new Result<>();
        List<appMissInfo> appMissInfos = venueManageService.getAllAppMissInfo();
        if(appMissInfos.size()!=0){
            result.setCode(200);
            result.setMsg("查询成功");
            result.setData(appMissInfos);
        }else {
            result.setCode(300);
            result.setMsg("查询失败");
        }

        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }
    @RequestMapping("/getAllDealInfo")
    public String getAllDealInfo() throws ParseException {
        Result<List> result = new Result<>();
        List<appMissInfo> appMissInfos = venueManageService.getAllDealInfo();
        if(appMissInfos.size()!=0){
            result.setCode(200);
            result.setMsg("查询成功");
            result.setData(appMissInfos);
        }else {
            result.setCode(300);
            result.setMsg("查询失败");
        }

        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }


    @RequestMapping("/getVenueInfoById")
    public String getVenueInfoListById(int id){

        Result result = new Result();
        VenueInfo venueInfos = venueManageService.getVenueInfoById(id);
        if(venueInfos!=null){
            result.setCode(200);
            result.setMsg("返回场地信息");
            result.setData(venueInfos);
        }else {
            result.setCode(300);
            result.setMsg("返回场地信息");
        }

        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/getVenueInfoByClassId")
    public String getVenueInfoListByClassId(int classId){

        Result<List> result = new Result<>();
        List<VenueInfo> venueInfos = venueManageService.getAllVenueInfoByClassId(classId);
        if(venueInfos.size() == 0){
            result.setCode(300);
            result.setMsg("返回场地信息失败");
        }else{
            result.setCode(200);
            result.setMsg("返回场地信息");
            result.setData(venueInfos);
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/getAllSpecialVenueInfo")
    public String getAllSpecialVenueInfo(int unitClassId){

        Result<List> result = new Result<>();
        List<specialVenueInfo> venueInfos = venueManageService.getAllSpecialVenueInfo(unitClassId);
//        System.out.println();
        result.setCode(200);
        result.setMsg("返回特殊场地信息");
        result.setData(venueInfos);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/checkAppInfo")
    public String checkAppInfoIfconflicts(int venueInfoId, String contestTime){
//        System.out.println(venueInfoId+contestTime);
        Result result = new Result();
        String week = DateConvertUtil.dateToWeek(contestTime);
        boolean flag1 = venueManageService.checkAppInfoIfconflicts(venueInfoId, contestTime);
        boolean flag2 = venueManageService.checkAppInfoIfSpecial(venueInfoId,week);
        if(flag1) {
            result.setCode(300);
            result.setMsg("该场地时间段已被申请");
        }else if(flag2) {
            result.setCode(400);
            result.setMsg("该场地时间段为校方单位使用");
        }else {
            result.setCode(200);
            result.setMsg("申请信息无冲突");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }


    @RequestMapping("/updateAppinfoPayStatus")
    public String updateAppInfoPayStatus(String code, String password){
        Result result = new Result();
        boolean flag = venueManageService.updateAppinfoPayStatus(code, password);
        if(flag == true){
            result.setCode(200);
            result.setMsg("状态更新成功");
        }else {
            result.setCode(300);
            result.setMsg("状态更新失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/addSpecialVenueInfo")
    public String addSpecialVenueInfo(@RequestBody SpecialVenueInfoAdd specialVenueInfoAdd){
        Result result = new Result();
        boolean flag = venueManageService.addSpecialVenueInfo(specialVenueInfoAdd);
        if(flag == false){
            result.setCode(200);
            result.setMsg("特殊场地新增成功");
        }else {
            result.setCode(300);
            result.setMsg("特殊场地新增失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/updateAppinfoStatus")
    public String updateAppInfoStatus(String code, int statusId){
        Result result = new Result();
        venueManageService.updateAppinfoStatus(code, statusId);
        result.setCode(200);
        result.setMsg("状态更新成功");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/getVenue")
    public String getVenueList(){
        Result<List> result = new Result<>();
        List<Venue> venues = venueManageService.getAllVenue();
        result.setCode(200);
        result.setMsg("返回场地列表");
        result.setData(venues);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getVenueClass")
    public String getVenueClass(){
        Result<List> result = new Result<>();
        List<VenueClass> list = venueManageService.getAllVenueClass();
        result.setCode(200);
        result.setMsg("返回场地类别");
        result.setData(list);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getSchoolUnit")
    public String getSchoolUnit(int tabPosition){
        Result<List> result = new Result<>();
        List<SchoolUnit> list = venueManageService.getAllSchoolUnit(tabPosition);
        result.setCode(200);
        result.setMsg("返回学校单位列表");
        result.setData(list);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getVenuePosition")
    public String getVenuePosition(){
        Result<List> result = new Result<>();
        List<VenuePosition> list = venueManageService.getAllVenuePosition();
        result.setCode(200);
        result.setMsg("返回场地位置");
        result.setData(list);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getVenuePeriod")
    public String getVenuePeriod(){

        Result<List> result = new Result<>();
        List<VenuePeriod> list = venueManageService.getAllVenuePeriod();
        result.setCode(200);
        result.setMsg("返回时间段信息");
        result.setData(list);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getVenueCharge")
    public String getVenueCharge(){
        Result<List> result = new Result<>();
        List<VenueCharge> list = venueManageService.getAllVenueCharge();
        result.setCode(200);
        result.setMsg("返回场地类别");
        result.setData(list);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/getVenueApp")
    public String getVenueApp(int statusId){
        Result<List> result = new Result<>();
        List<VenueApp> list = venueManageService.getVenueAppByStatusId(statusId);

        if(list.size()!=0){
            result.setCode(200);
            result.setMsg("返回场地申请信息");
            result.setData(list);
        }else {
            result.setCode(300);
            result.setMsg("返回场地申请信息失败");
        }
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    //获取场地申请数量信息
    @RequestMapping("/countVenueApp")
    public String getContestAppInfo(int statusId) throws ParseException {
        Result result = new Result<>();
        int i = venueManageService.countVenueAppByStatusId(statusId);
        result.setCode(200);
        result.setMsg("返回场地申请数量");
        result.setData(i);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/countDealInfo")
    public String countDealInfo(){
        Result result = new Result<>();
        int i = venueManageService.countDealInfo();
        result.setCode(200);
        result.setMsg("返回处理数量");
        result.setData(i);
        String resultStr = JSON.toJSONString(result);
        return resultStr;

    }

    //获取赛事申请信息
    @RequestMapping("/getContestAppInfo")
    public String getContestAppInfo(String code) throws ParseException {
        Result result = new Result<>();
        ContestAppInfo contestAppInfo = venueManageService.getContestAppInfoByCode(code);
        if(contestAppInfo != null){
            result.setCode(200);
            result.setMsg("返回赛事申请信息");
            result.setData(contestAppInfo);
        }else {
            result.setCode(300);
            result.setMsg("申请信息查询不到");
        }
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    // 删除场地信息
    @RequestMapping("/venueInfoDelete")
    public void deleteVenueInfo (int id) {
        venueManageService.deleteVenueInfoById(id);

    }

    // 删除场地
    @RequestMapping("/venueDelete")
    public void deleteVenue (int id) {
        venueManageService.deleteVenueById(id);

    }

    // 更新场地信息
    @RequestMapping("/updateVenue")
    public void updateVenue(@RequestBody VenueUpdate venueUpdate){
        venueManageService.updateVenue(venueUpdate);
    }

    // 删除特殊场地信息
    @RequestMapping("/specialVenueDelete")
    public void deleteSpecialVenueInfo(int id){
//        Result result = new Result<>();
        venueManageService.deleteSpecialVenueInfoById(id);
//        result.setCode(200);
//        String resultStr = JSON.toJSONString(result);
//        return resultStr;
    }

    // 获取用户id
    @RequestMapping("/getUserId")
    public String getUserIdByName(String username){
        Result result = new Result<>();
        int id = venueManageService.getUserIdByName(username);
        result.setCode(200);
        result.setMsg("返回用户id");
        result.setData(id);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }


    @RequestMapping("/addVenue")
    public String addVenue(@RequestBody VenueAdd venueAdd){
        Result<List> result = new Result<>();
        venueManageService.addVenue(venueAdd);
        result.setCode(200);
        result.setMsg("新增场地成功");
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/addVenueInfo")
    public String addVenueInfo(@RequestBody VenueInfoAdd venueInfoAdd){
//        System.out.println(venueInfoAdd.getCost());
        Result<List> result = new Result<>();
        venueManageService.addVenueInfo(venueInfoAdd);
        result.setCode(200);
        result.setMsg("新增场地信息成功");
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/addDealInfo")
    public String addDealInfo(@RequestBody NewDealinfo newDealinfo){
        Result result = new Result();
        venueManageService.addDealInfo(newDealinfo);
        result.setCode(200);
        result.setMsg("新增处理信息成功");
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/addVenueAppInfo")
    public String addVenueAppInfo(@RequestBody VenueAppInfo venueAppInfo) throws ParseException {
        // ContestAppInfo contestAppInfo = venueManageService.getContestAppInfoByCode(venueAppInfo.getCode());
        Result<List> result = new Result<>();
        Date date = new Date();// 获取当前时间
        venueAppInfo.setSubmitTime(DateConvertUtil.date2String(date, "yyyy-MM-dd HH:mm:ss"));
        venueAppInfo.setStatusId(1);
        venueManageService.addVenueAppInfo(venueAppInfo);
        result.setCode(200);
        result.setMsg("新增场地申请信息成功");
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }


}
