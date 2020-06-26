package com.gms.gmseqpt.dao;

import com.gms.commons.domain.EqptCompensation;
import com.gms.commons.domain.EqptRent;
import com.gms.commons.domain.EqptType;
import com.gms.commons.domain.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface EquipmentDao {
    public List<Equipment> getEquipment(); //获得所有器材信息

    public List<Equipment> queryEqpt(@Param("eqptID")String eqptID); //根据器材编号获得信息

    public void addEqpt(Equipment equipment); //添加器材

    public void repairEqpt(@Param("eqptID")String eqptID); //报修器材

    public void deleteEqpt(@Param("eqptID")String eqptID); //报废器材

    public List<EqptType> queryType(); //获得器材类型和数量

    public void addType(EqptType eqptType); //添加类别

    public void chargePrice(EqptType eqptType); //修改单价

    public int getPrice(@Param("eqptName")String eqptName); //根据类型查找单价

    public void eqptApp(EqptRent eqptRent); //器材租用申请

    public List<EqptRent>  getRentList(); //获取租用申请订单列表

    public List<Equipment> getChoseEqptList(@Param("eqptName")String eqptName); //根据器材类型查找相应的器材

    public void rejectRent(EqptRent eqptRent); //拒绝申请

    public void agreeRent(String[] multis); //分配器材

    public void agreeVerify(EqptRent eqptRent); //同意申请

    public List<Equipment> searchRent(); //查找出租的器材

    public void recyclingEqpt(@Param("eqptID")String eqptID); //回收器材

    public List<Equipment> queryByType(@Param("eqptName")String eqptName); //根据器材类型获得出租器材信息

    public List<Equipment> queryByID(@Param("eqptID")String eqptID); //根据器材编号获得出租器材信息

    public void eqptDamage(@Param("eqptID")String eqptID); //损坏器材

    public List<EqptType> getChargStandard(@Param("eqptName")String eqptName); //获得器材类型和数量

    public List<Equipment> getOptions(); //获得损坏的器材编号

    public List<EqptCompensation> getCompensationeList(); //获取赔偿列表

    public void addCompensation(EqptCompensation eqptCompensation); //添加赔偿

    public void finishCompensation(EqptCompensation eqptCompensation); //确认赔偿

    public List<EqptRent>  orederQuery(@Param("telphone")String telphone); //查询自己的租用申请
}
