package com.gms.gmseqpt.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.EqptCompensation;
import com.gms.commons.domain.EqptRent;
import com.gms.commons.domain.EqptType;
import com.gms.commons.domain.Equipment;
import com.gms.commons.utils.Result;
import com.gms.gmseqpt.service.EqptManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EqptManageController {

    @Autowired
    EqptManageService eqptManageService;

    //显示所有器材信息
    @RequestMapping("/eqptInfo")
    public String getEquipment(){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.getEquipment();
        result.setCode(200);
        result.setMsg("返回器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //查询器材
    @RequestMapping("/queryEqpt")
    public String queryEqpt(@Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.queryEqpt(eqptID);
        result.setCode(200);
        result.setMsg("返回单个器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //添加器材
    @RequestMapping("/addEqpt")
    public String addEqpt(@RequestBody Equipment equipment){
        Result<List> result = new Result<>();
        try{
            eqptManageService.addEqpt(equipment);
            result.setCode(200);
            result.setMsg("添加器材");
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("添加失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/repairEqpt")
    @ResponseBody
    public String repairEqpt(@RequestBody @Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        if(eqptID!=null){
            eqptManageService.repairEqpt(eqptID);
            result.setCode(200);
            result.setMsg("报修成功");
        }else{
            System.out.println("获取不到器材ID");
            result.setCode(400);
            result.setMsg("报修失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;

    }

    //报废器材
    @RequestMapping("/deleteEqpt")
    @ResponseBody
    public String deleteEqpt(@RequestBody @Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        if(eqptID!=null){
            eqptManageService.deleteEqpt(eqptID);
            result.setCode(200);
            result.setMsg("报废成功");
        }else{
            System.out.println("获取不到器材ID");
            result.setCode(400);
            result.setMsg("报废失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //显示所有器材类型信息
    @RequestMapping("/queryType")
    public String queryType(){
        Result<List> result = new Result<>();
        List<EqptType> eqptInfo = eqptManageService.queryType();
        result.setCode(200);
        result.setMsg("返回器材类型信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //添加器材类别
    @RequestMapping("/addType")
    public String addType(@RequestBody EqptType eqptType){
        Result<List> result = new Result<>();
        try{
            eqptManageService.addType(eqptType);
            result.setCode(200);
            result.setMsg("添加器材");
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("添加失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/chargePrice")
    public String  chargePrice(@RequestBody EqptType eqptType){
        Result<List> result = new Result<>();
        eqptManageService.chargePrice(eqptType);
        result.setCode(200);
        result.setMsg("修改成功");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //查询单价
    @RequestMapping("/getPrice")
    public int getPrice(@Param("eqptName")String eqptName){
        int eqptInfo = eqptManageService.getPrice(eqptName);
        return eqptInfo;
    }

    //新增器材租用
    @RequestMapping("/eqptApp")
    public String eqptApp(@RequestBody EqptRent eqptRent){
        Result<List> result = new Result<>();
        try{
            eqptManageService.eqptApp(eqptRent);
            result.setCode(200);
            result.setMsg("新增器材租用");
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("新增失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //显示所有租用申请信息
    @RequestMapping("/rentList")
    public String getRentList(){
        Result<List> result = new Result<>();
        List<EqptRent> eqptInfo = eqptManageService.getRentList();
        result.setCode(200);
        result.setMsg("返回租用申请信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //根据类型查询器材
    @RequestMapping("/choseEqptList")
    public String getChoseEqptList(@Param("eqptName")String eqptName){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.getChoseEqptList(eqptName);
        result.setCode(200);
        result.setMsg("返回单个器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //驳回申请
    @RequestMapping("/rejectRent")
    public String rejectRent(@RequestBody EqptRent eqptRent){
        Result<List> result = new Result<>();
        eqptManageService.rejectRent(eqptRent);
        result.setCode(200);
        result.setMsg("驳回申请");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //分配器材
    @RequestMapping("/agreeRent")
    public String agreeRent(@RequestBody String[] multis){
        Result<List> result = new Result<>();
        eqptManageService.agreeRent(multis);
        result.setCode(200);
        result.setMsg("同意申请");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //同意申请
    @RequestMapping("/agreeVerify")
    public String agreeVerify(@RequestBody EqptRent eqptRent){
        Result<List> result = new Result<>();
        eqptManageService.agreeVerify(eqptRent);
        result.setCode(200);
        result.setMsg("同意申请");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //显示出租了器材的信息
    @RequestMapping("/searchRent")
    public String searchRent(){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.searchRent();
        result.setCode(200);
        result.setMsg("返回出租器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/recyclingEqpt")
    @ResponseBody
    public String recyclingEqpt(@RequestBody @Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        if(eqptID!=null){
            eqptManageService.recyclingEqpt(eqptID);
            result.setCode(200);
            result.setMsg("回收成功");
        }else{
            System.out.println("获取不到器材ID");
            result.setCode(400);
            result.setMsg("回收失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //根据器材类型获得出租器材信息
    @RequestMapping("/queryByType")
    public String queryByType(@Param("eqptName")String eqptName){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.queryByType(eqptName);
        result.setCode(200);
        result.setMsg("返回单个器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //根据器材编号获得出租器材信息
    @RequestMapping("/queryByID")
    public String queryByID(@Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.queryByID(eqptID);
        result.setCode(200);
        result.setMsg("返回单个器材信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/eqptDamage")
    @ResponseBody
    public String eqptDamage(@RequestBody @Param("eqptID")String eqptID){
        Result<List> result = new Result<>();
        if(eqptID!=null){
            eqptManageService.eqptDamage(eqptID);
            result.setCode(200);
            result.setMsg("回收成功");
        }else{
            System.out.println("获取不到器材ID");
            result.setCode(400);
            result.setMsg("回收失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //查询租用收费标准
    @RequestMapping("/getChargStandard")
    public String getChargStandard(@Param("eqptName")String eqptName){
        Result<List> result = new Result<>();
        List<EqptType> eqptInfo = eqptManageService.getChargStandard(eqptName);
        result.setCode(200);
        result.setMsg("返回单个租用收费标准信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    @RequestMapping("/getOptions")
    public String getOptions(){
        Result<List> result = new Result<>();
        List<Equipment> eqptInfo = eqptManageService.getOptions();
        result.setCode(200);
        result.setMsg("返回单个租用收费标准信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //显示赔偿列表
    @RequestMapping("/getCompensationeList")
    public String getCompensationeList(){
        Result<List> result = new Result<>();
        List<EqptCompensation> eqptInfo = eqptManageService.getCompensationeList();
        result.setCode(200);
        result.setMsg("返回赔偿列表");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //添加赔偿
    @RequestMapping("/addCompensation")
    public String addCompensation(@RequestBody EqptCompensation eqptCompensation){
        Result<List> result = new Result<>();
        try{
            eqptManageService.addCompensation(eqptCompensation);
            result.setCode(200);
            result.setMsg("添加赔偿");
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("添加失败");
        }
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //确定赔偿
    @RequestMapping("/finishCompensation")
    public String finishCompensation(@RequestBody EqptCompensation eqptCompensation){
        Result<List> result = new Result<>();
        eqptManageService.finishCompensation(eqptCompensation);
        result.setCode(200);
        result.setMsg("赔偿处理完成");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

    //查询自己的租用申请
    @RequestMapping("/orederQuery")
    public String orederQuery(@Param("telphone")String telphone){
        Result<List> result = new Result<>();
        List<EqptRent> eqptInfo = eqptManageService.orederQuery(telphone);
        result.setCode(200);
        result.setMsg("返回单个租用申请信息");
        result.setData(eqptInfo);
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }

}
