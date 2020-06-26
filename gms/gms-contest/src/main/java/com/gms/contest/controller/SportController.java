/**
 * @author wjw
 * @date 2020/6/9 0:26
 */
package com.gms.contest.controller;

import com.gms.commons.domain.Sport;
import com.gms.commons.utils.Result;
import com.gms.contest.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private SportService sportService;

    //查询所有可用赛事
    @PostMapping("/list")
    public Result<List<Sport>> findAll(Integer status){
        //申请时发送status为1
        //管理时不发送参数
        List<Sport> sportList = this.sportService.selectAll(status);
        if (CollectionUtils.isEmpty(sportList)){
            return new Result<>(500, "未找到体育项目！！！！", null);
        }
        return new Result<>(200, "成功！！", sportList);
    }

    @PostMapping("/selectSportById")
    public Result<Sport> selectSportById(Integer id) throws Exception {
        if (id == null){
            return new Result<>(400, "输入信息不能为空！！！", null);
        }
        Sport sport = this.sportService.selectSportById(id);
        if (sport != null){
            return new Result<>(200, "成功！！", sport);
        }else{
            return new Result<>(500, "未找到该体育项目！！！", null);
        }
    }

    @PostMapping("/addSport")
    public Result addSport(String sportName){
        if (sportName == null || "".equals(sportName)){
            return new Result(400, "数据不能为空！！！");
        }
        int i = sportService.addSport(sportName);
        if (i > 0){
            return new Result<>(200, "添加成功！！！！");
        }else if ( i == 0){
            return new Result<>(500, "数据已经存在！！！！");
        }else {
            return new Result<>(500, "添加失败！！！！");
        }
    }

    @PostMapping("/delSport")
    public Result delSport(Integer id){
        int i = sportService.delSport(id);
        if (i > 0){
            return new Result<>(200, "删除成功！！！");
        }else {
            return new Result<>(500, "删除失败！！！");
        }
    }

    @PostMapping("/updateSportStatus")
    public Result updateSportStatus(@RequestParam(value = "id", required = true) Integer id,@RequestParam(value = "status", required = true) Integer status){
        int i = sportService.updateSportStatus(id, status);
        if (i > 0){
            return new Result<>(200, "修改成功！！！！");
        }else {
            return new Result<>(500, "服务器出现错误！！1！");
        }
    }
}
