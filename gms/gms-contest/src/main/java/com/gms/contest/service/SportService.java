/**
 * @author wjw
 * @date 2020/6/9 13:09
 */
package com.gms.contest.service;

import com.gms.commons.domain.Sport;
import com.gms.contest.dao.SportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class SportService {

    @Autowired
    private SportMapper sportMapper;

    //查询可用体育项目
    public List<Sport> selectAll(Integer status){
        Sport sport = new Sport();
        if (status == null){
            return sportMapper.selectAll();
        }
        sport.setStatus(status);
        return sportMapper.select(sport);
    }

    //查询指定体育项目
    public Sport selectSportById(Integer id){
        return sportMapper.selectByPrimaryKey(id);
    }

    //添加体育项目
    public int addSport(String sportName){
        //判断项目是否已经存在
        Example example = new Example(Sport.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sportName", sportName);
        List<Sport> sports = sportMapper.selectByExample(example);
        if (sports.size() > 0){     //已经存在
            return 0;
        }

        //不存在则添加
        Sport sport = new Sport();
        sport.setSportName(sportName);
        sport.setStatus(1);
        return sportMapper.insert(sport);
    }

    //删除项目，实为修改状态为0
    public int delSport(Integer id){
        Sport sport = sportMapper.selectByPrimaryKey(id);
        sport.setStatus(0);
        return sportMapper.updateByPrimaryKeySelective(sport);
    }

    //修改状态
    public int updateSportStatus(Integer id, Integer status){
        Sport sport = sportMapper.selectByPrimaryKey(id);
        sport.setStatus(status);
        return sportMapper.updateByPrimaryKeySelective(sport);
    }
}
