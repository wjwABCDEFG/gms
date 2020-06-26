/**
 * @author wjw
 * @date 2020/6/10 1:17
 */
package com.gms.contest.controller;

import com.gms.commons.domain.Referee;
import com.gms.commons.utils.Result;
import com.gms.contest.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/referee")
public class RefereeController {

    @Autowired
    private RefereeService refereeService;

    @PostMapping("/list")
    public Result<List<Referee>> selectAll(Integer status){
        List<Referee> referees = this.refereeService.selectAll(status);
        if (!CollectionUtils.isEmpty(referees)){
            return new Result<>(200, "成功！！！", referees);
        }else {
            return new Result<>(500, "没有找到任何教练信息！！！！！");
        }
    }

    @PostMapping("/selectById")
    public Result<Referee> selectById(Integer id){
        if (id == null){
            return new Result<>(400, "输入信息不能为空！！！", null);
        }
        Referee referee = this.refereeService.selectById(id);
        if (referee != null){
            return new Result<>(200, "成功！！！", referee);
        }else {
            return new Result<>(500, "没有找到这个教练！！！");
        }
    }

    @PostMapping("/selectBySportId")
    public Result<List<Referee>> selectBySportId(Integer sportId){
        if (sportId == null){
            return new Result<>(400, "输入信息不能为空！！！", null);
        }
        List<Referee> referees = this.refereeService.selectBySportId(sportId);
        if (!CollectionUtils.isEmpty(referees)){
            return new Result<>(200, "成功！！！", referees);
        }else {
            return new Result<>(500, "没有找到任何教练信息！！！！！");
        }
    }

    @PostMapping("/addReferee")
    public Result addReferee(Referee referee){
        if (referee.getName() == null
            || referee.getSex() == null
            || referee.getPhone() == null){
            return new Result(400, "数据不能为空！！！");
        }
        int i = refereeService.addReferee(referee);
        if (i > 0){
            return new Result<>(200, "添加成功！！！！");
        }else if ( i == 0){
            return new Result<>(500, "数据已经存在！！！！");
        }else {
            return new Result<>(500, "添加失败！！！！");
        }
    }

    @PostMapping("/delReferee")
    public Result delReferee(Integer id){
        int i = refereeService.delReferee(id);
        if (i > 0){
            return new Result<>(200, "删除成功！！！");
        }else {
            return new Result<>(500, "删除失败！！！");
        }
    }

    @PostMapping("/updateReferee")
    public Result updateReferee(Referee referee){
        int i = refereeService.updateReferee(referee);
        if (i > 0){
            return new Result<>(200, "修改成功！！！");
        }else {
            return new Result<>(500, "修改失败！！！");
        }
    }

    @PostMapping("/updateRefereeStatus")
    public Result updateRefereeStatus(Integer id,  Integer status){
        int i = refereeService.updateRefereeStatus(id, status);
        if (i > 0){
            return new Result<>(200, "修改成功！！！");
        }else {
            return new Result<>(500, "修改失败！！！");
        }
    }

}
