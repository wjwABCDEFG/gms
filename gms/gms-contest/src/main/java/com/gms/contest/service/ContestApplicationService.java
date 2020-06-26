/**
 * @author wjw
 * @date 2020/6/12 0:12
 */
package com.gms.contest.service;

import com.gms.commons.domain.CtstApplication;
import com.gms.commons.domain.Referee;
import com.gms.commons.domain.Sport;
import com.gms.commons.utils.DateConvertUtil;
import com.gms.commons.utils.Encrypt;
import com.gms.contest.dao.ContestApplicationMapper;
import com.gms.contest.dao.RefereeMapper;
import com.gms.contest.dao.SportMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContestApplicationService {


    @Autowired
    private ContestApplicationMapper contestApplicationMapper;

    @Autowired
    private SportMapper sportMapper;

    @Autowired
    private RefereeMapper refereeMapper;


    /**
     * 查询所有赛事申请
     */
    public List<CtstApplication> selectAll(){
        List<CtstApplication> ctstApplications = contestApplicationMapper.selectAll();
        //偷懒写法
        for (CtstApplication ctstApplication : ctstApplications) {
            Sport sport = sportMapper.selectByPrimaryKey(ctstApplication.getSportId());
            Referee referee = refereeMapper.selectByPrimaryKey(ctstApplication.getRefereeId());
            ctstApplication.setSport(sport);
            ctstApplication.setReferee(referee);
        }
        return ctstApplications;
    }


    /**
     * 查询某个学生的赛事申请
     */
    public List<CtstApplication> selectByUserId(Integer userId){
        CtstApplication ctstApplication = new CtstApplication();
        ctstApplication.setUserId(userId);
        List<CtstApplication> ctstApplications = contestApplicationMapper.select(ctstApplication);
        //偷懒写法
        for (CtstApplication storedCtstApplication : ctstApplications) {
            Sport sport = sportMapper.selectByPrimaryKey(storedCtstApplication.getSportId());
            Referee referee = refereeMapper.selectByPrimaryKey(storedCtstApplication.getRefereeId());
            storedCtstApplication.setSport(sport);
            storedCtstApplication.setReferee(referee);
        }
        return ctstApplications;
    }

    /**
     * 根据状态查询申请（查询未审核的赛事申请）
     */
    public List<CtstApplication> selectByStatus(String status){
        CtstApplication ctstApplication = new CtstApplication();
        ctstApplication.setAppStatus(status);
        List<CtstApplication> ctstApplications = contestApplicationMapper.select(ctstApplication);
        //偷懒写法
        for (CtstApplication storedCtstApplication : ctstApplications) {
            Sport sport = sportMapper.selectByPrimaryKey(storedCtstApplication.getSportId());
            Referee referee = refereeMapper.selectByPrimaryKey(storedCtstApplication.getRefereeId());
            storedCtstApplication.setSport(sport);
            storedCtstApplication.setReferee(referee);
        }
        return ctstApplications;
    }

    /**
     * 学生：提交赛事申请
     * 管理员：直接创建赛事（额外设置appStatus为ACCEPT_CONTEST，生成appRecordId，设置一个裁判）
     */
    public int saveContestApplication(CtstApplication ctstApplication, Integer roleId){
//        //1.生成GS+4位赛事代号
//        String contestId = "GS" + Math.round((Math.random() + 1) * 1000);
//        ctstApplication.setContestId(contestId);
        //2.获取申请时间（是提交申请的时间不是申请的比赛的时间）
        String dateStr = DateConvertUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        ctstApplication.setAppTime(dateStr);

        //3.1判断是否是管理员，如果是管理员，直接创建
        if (roleId == 3){
            ctstApplication.setAppStatus("ACCEPT_CONTEST");
            String appRecordId = "Y" + Math.round((Math.random() + 1) * 1000000000);
            ctstApplication.setAppRecordId(appRecordId);
            ctstApplication.setAppRecordTime(DateConvertUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }else{
            //3.2如果为学生设置appStatus为WAIT_CONTEST
            ctstApplication.setAppStatus("WAIT_CONTEST");
        }
        return contestApplicationMapper.insert(ctstApplication);
    }

    /**
     * 取消申请
     */
    public int cancelContestApplication(Integer id){
        CtstApplication ctstApplication = contestApplicationMapper.selectByPrimaryKey(id);
        //1.设置为appStatus为CANCEL
        ctstApplication.setAppStatus("CANCEL");
        //TODO 2.将场地和器材的状态也设置为CANCEL
        return contestApplicationMapper.updateByPrimaryKeySelective(ctstApplication);
    }

    /**
     * 审核申请
     */
    public int checkContestApplication(Integer id, Boolean isAccept, Integer refereeId){
        //1.管理员点击某条未审核的申请
        CtstApplication ctstApplication = contestApplicationMapper.selectByPrimaryKey(id);
        //2.设置为appStatus为ACCEPT_CONTEST或者REFUSE_CONTEST
        if (isAccept){
            ctstApplication.setAppStatus("ACCEPT_CONTEST");
            //3.若设置为ACCEPT_CONTEST，还要给appRecordId生成一个Y后面接10位数的随机值
            String[] recordCode = getTimeStampMandomNumber(1, "1", "1");
            int length = recordCode[0].length();
            String appRecordId = "Y" + recordCode[0].substring(2, length);
//            String appRecordId = "Y" + Math.round((Math.random() + 1) * 1000000000);
            ctstApplication.setAppRecordId(appRecordId);
            //4.设置一个裁判
            ctstApplication.setRefereeId(refereeId);
        }else{
            ctstApplication.setAppStatus("REFUSE_CONTEST");
        }
        //5.设置时间
        ctstApplication.setAppRecordTime(DateConvertUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return contestApplicationMapper.updateByPrimaryKeySelective(ctstApplication);
    }

    public List<CtstApplication> selectWait() {
        CtstApplication ctstApplication = new CtstApplication();
        ctstApplication.setAppStatus("WAIT_CONTEST");
        List<CtstApplication> ctstApplications = contestApplicationMapper.select(ctstApplication);
        //偷懒写法
        for (CtstApplication ctstApplication1 : ctstApplications) {
            Sport sport = sportMapper.selectByPrimaryKey(ctstApplication1.getSportId());
            Referee referee = refereeMapper.selectByPrimaryKey(ctstApplication1.getRefereeId());
            ctstApplication1.setSport(sport);
            ctstApplication1.setReferee(referee);
        }
        return ctstApplications;
    }

    //上传照片
    //1.设置为appStatus为DONE
    //TODO ha
    public int uploadPic(Integer id) {
        CtstApplication ctstApplication = contestApplicationMapper.selectByPrimaryKey(id);
        //1.设置为appStatus为DONE
        ctstApplication.setAppStatus("DONE");
        //TODO 2.将场地和器材的状态也设置为DONE
        return contestApplicationMapper.updateByPrimaryKeySelective(ctstApplication);
    }

    public static String[] getTimeStampMandomNumber(int num,String scourseTypeIdString,String agentIdString){
        String[] codes = new String[num];
        for(int i = 0;i<num;i++){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
            String timeStamp = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

            String code = agentIdString+scourseTypeIdString+ Encrypt.Encrypt_md5(timeStamp+i+"").substring(6,16).toUpperCase();
            codes[i] = code;

        }
        return codes;
    }

}
