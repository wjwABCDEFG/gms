/**
 * @author wjw
 * @date 2020/6/12 1:10
 */
package com.gms.contest.controller;

import com.gms.commons.domain.CtstApplication;
import com.gms.commons.utils.Result;
import com.gms.contest.service.ContestApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contestApplication")
public class ContestApplicationController {

    @Autowired
    private ContestApplicationService contestApplicationService;

    /**
     * 查询所有申请
     */
    @PostMapping("/list")
    public Result<List<CtstApplication>> selectAll(){
        List<CtstApplication> ctstApplications = contestApplicationService.selectAll();
        if (!CollectionUtils.isEmpty(ctstApplications)){
            return new Result<>(200, "查询成功！！！！！", ctstApplications);
        }else {
            return new Result<>(500, "未查询到任何申请！！！");
        }
    }

    /**
     * 查询等待审核的申请
     */
    @PostMapping("/selectWait")
    public Result<List<CtstApplication>> selectWait(){
        List<CtstApplication> ctstApplications = contestApplicationService.selectWait();
        if (!CollectionUtils.isEmpty(ctstApplications)){
            return new Result<>(200, "查询成功！！！！！", ctstApplications);
        }else {
            return new Result<>(500, "未查询到任何申请！！！");
        }
    }

    /**
     * 根据用户id查询
     */
    @PostMapping("/selectByUid")
    public Result<List<CtstApplication>> selectByUid(Integer userId){
        if (userId == null){
            return new Result<>(400, "输入的参数不能位空！！！");
        }
        List<CtstApplication> ctstApplications = this.contestApplicationService.selectByUserId(userId);
        if (!CollectionUtils.isEmpty(ctstApplications)){
            return new Result<>(200, "查询成功！！！！！", ctstApplications);
        }else {
            return new Result<>(500, "该学生尚未申请任何赛事！！！");
        }
    }

    /**
     * 根据状态查询
     */
    @PostMapping("/selectByStatus")
    public Result<List<CtstApplication>> selectByStatus(String status){
        if (status == null || "".equals(status)){
            return new Result<>(400, "输入的参数不能位空！！！");
        }
        List<CtstApplication> ctstApplications = this.contestApplicationService.selectByStatus(status);
        if (!CollectionUtils.isEmpty(ctstApplications)){
            return new Result<>(200, "查询成功！！！！！", ctstApplications);
        }else {
            return new Result<>(500, "该学生尚未申请任何赛事！！！");
        }
    }


    /**
     * 申请赛事
     */
    @PostMapping("/saveContestApplication")
    @CrossOrigin
    public Result saveContestApplication(CtstApplication ctstApplication, Integer roleId){
        if (ctstApplication == null){
            return new Result(400, "数据不能为空！！！");
        }
        int i = contestApplicationService.saveContestApplication(ctstApplication, roleId);
        if (i > 0){
            return new Result(200, "成功！！！");
        }else{
            return new Result<>(500, "添加失败！！！！");
        }
    }

    /**
     * 取消申请
     */
    @PostMapping("/cancelContestApplication")
    public Result cancelContestApplication(Integer id){
        if (id == null){
            return new Result(400, "数据不能为空！！！");
        }
        int i = contestApplicationService.cancelContestApplication(id);
        //System.out.println(i);
        if (i > 0){
            return new Result(200, "取消申请成功！！！");
        }else{
            return new Result<>(500, "申请取消失败！！！！");
        }
    }

    /**
     * 上传照片
     */
    @PostMapping("/uploadPic")
    public Result uploadPic(Integer id, String url){
        if (id == null){
            return new Result(400, "数据不能为空！！！");
        }
        int i = contestApplicationService.uploadPic(id);
        //System.out.println(i);
        if (i > 0){
            return new Result(200, "照片上传成功！！！");
        }else{
            return new Result<>(500, "照片上传失败！！！！");
        }
    }

    /**
     * 审核赛事
     */
    @PostMapping("/checkContestApplication")
    public Result checkContestApplication(Integer id, Boolean isAccept, Integer refereeId){
        if (id == null || isAccept == null){
            return new Result(400, "输入数据不能为空！！！");
        }
        if (isAccept && refereeId == null){
            return new Result(400, "教练信息不能为空！！！");
        }
        int i = contestApplicationService.checkContestApplication(id, isAccept, refereeId);
        if (i > 0){
            return new Result(200, "处理成功！！！");
        }else{
            return new Result<>(500, "处理失败！！！！");
        }
    }
}
