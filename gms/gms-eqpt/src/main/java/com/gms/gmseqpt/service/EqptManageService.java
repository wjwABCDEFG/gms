package com.gms.gmseqpt.service;

import com.gms.commons.domain.EqptCompensation;
import com.gms.commons.domain.EqptRent;
import com.gms.commons.domain.EqptType;
import com.gms.commons.domain.Equipment;
import com.gms.gmseqpt.dao.EquipmentDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EqptManageService {

    @Autowired
    EquipmentDao eqptDao;

    // 全部器材信息
    public List<Equipment> getEquipment(){
        List<Equipment> eqptInfo = eqptDao.getEquipment();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //查询器材信息
    public List<Equipment> queryEqpt(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"测试接受数据！！！！！！");
        List<Equipment> eqptInfo = eqptDao.queryEqpt(eqptID);
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //添加器材
    public void  addEqpt(Equipment equipment){
        System.out.println(equipment+"添加数据测试");
        eqptDao.addEqpt(equipment);
        System.out.println("器材添加成功");
    }

    //修改器材状态
    public void repairEqpt(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"？？？数据输入测试");
        String eqptid = eqptID.substring(0,9);
        System.out.println(eqptid+"？？？数据输入测试");
        eqptDao.repairEqpt(eqptid);
        System.out.println("器材状态改变成功");
    }

    //报废器材
    public void deleteEqpt(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"数据输入测试");
        String eqptid = eqptID.substring(0,9);
        System.out.println(eqptid+"数据输入测试");
        eqptDao.deleteEqpt(eqptid);
        System.out.println("器材报废删除成功");
    }

    public List<EqptType> queryType(){
        List<EqptType> eqptInfo = eqptDao.queryType();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    public void addType(EqptType eqptType){
        System.out.println(eqptType+"添加数据测试");
        eqptDao.addType(eqptType);
        System.out.println("数据添加成功");
    }

    public void chargePrice(EqptType eqptType) {
        System.out.println(eqptType + "添加数据测试");
        eqptDao.chargePrice(eqptType);
    }

    //查询器材单价
    public int getPrice(@Param("eqptName")String eqptName){
        System.out.println(eqptName+"测试接受数据！！！！！！");
        int eqptInfo = eqptDao.getPrice(eqptName);
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //租用申请添加、提交
    public void  eqptApp(EqptRent eqptRent){
        System.out.println(eqptRent+"添加数据测试");
        eqptDao.eqptApp(eqptRent);
        System.out.println("租用申请提交成功");
    }

    //获取租用申请列表
    public List<EqptRent> getRentList(){
        List<EqptRent> eqptInfo = eqptDao.getRentList();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //根据类型查找器材
    public List<Equipment> getChoseEqptList(@Param("eqptName")String eqptName){
        System.out.println(eqptName+"接受输入测试");
        List<Equipment> eqptInfo = eqptDao.getChoseEqptList(eqptName);
        System.out.println(eqptInfo+"查找相应器材信息");
        return eqptInfo;
    }

    //驳回申请
    public void rejectRent(EqptRent eqptRent){
        System.out.println(eqptRent+"输入接受测试");
        eqptDao.rejectRent(eqptRent);
    }

    //改变器材状态
    public void agreeRent(String[] multis){
        for(int i=0;i<multis.length;i++){
            System.out.println(multis[i]);
        }
        eqptDao.agreeRent(multis);
    }

    //同意申请
    public void agreeVerify(EqptRent eqptRent){
        System.out.println(eqptRent+"输入接受测试");
        eqptDao.agreeVerify(eqptRent);
    }

    //获取出租了的器材信息
    public List<Equipment> searchRent(){
        List<Equipment> eqptInfo = eqptDao.searchRent();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //回收器材
    public void recyclingEqpt(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"测试输入数据");
        String eqptid = eqptID.substring(0,9);
        eqptDao.recyclingEqpt(eqptid);
    }

    //根据器材类型获得出租器材信息
    public List<Equipment> queryByType(@Param("eqptName")String eqptName){
        System.out.println(eqptName+"测试接受数据！！！！！！");
        List<Equipment> eqptInfo = eqptDao.queryByType(eqptName);
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //根据器材编号获得出租器材信息
    public List<Equipment> queryByID(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"测试接受数据！！！！！！");
        List<Equipment> eqptInfo = eqptDao.queryByID(eqptID);
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //损坏器材
    public void eqptDamage(@Param("eqptID")String eqptID){
        System.out.println(eqptID+"测试输入数据");
        String eqptid = eqptID.substring(0,9);
        eqptDao.eqptDamage(eqptid);
    }

    //查询租用收费标准
    public List<EqptType> getChargStandard(@Param("eqptName")String eqptName){
        System.out.println(eqptName+"测试接受数据！！！！！！");
        List<EqptType>  eqptInfo = eqptDao.getChargStandard(eqptName);
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    public List<Equipment> getOptions(){
        List<Equipment> eqptInfo = eqptDao.getOptions();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    public List<EqptCompensation> getCompensationeList(){
        List<EqptCompensation> eqptInfo = eqptDao.getCompensationeList();
        System.out.println(eqptInfo);
        return eqptInfo;
    }

    //添加赔偿
    public void addCompensation(EqptCompensation eqptCompensation){
        System.out.println(eqptCompensation+"添加数据测试");
        eqptDao.addCompensation(eqptCompensation);
        System.out.println("赔偿添加成功");
    }

    //回收器材
    public void finishCompensation(EqptCompensation eqptCompensation){
        System.out.println(eqptCompensation+"测试输入数据");
        eqptDao.finishCompensation(eqptCompensation);
    }

   public List<EqptRent>  orederQuery(@Param("telphone")String telphone){
       System.out.println(telphone+"测试接受数据！！！！！！");
       List<EqptRent> eqptInfo = eqptDao.orederQuery(telphone);
       System.out.println(eqptInfo);
       return eqptInfo;
   }
}
