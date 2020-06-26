/**
 * @author wjw
 * @date 2020/6/9 22:17
 */
package com.gms.contest.service;

import com.gms.commons.domain.Referee;
import com.gms.commons.domain.Sport;
import com.gms.contest.dao.RefereeMapper;
import com.gms.contest.dao.SportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class RefereeService {

    @Autowired
    private RefereeMapper refereeMapper;

    @Autowired
    private SportMapper sportMapper;

    //查询所有教练（带参则查询未退休）
    public List<Referee> selectAll(Integer status){
        Referee referee = new Referee();
        if (status == null){
            return refereeMapper.selectAll();
        }
        referee.setStatus(status);
        List<Referee> referees = refereeMapper.select(referee);
        for (Referee re : referees) {
            Sport sport = new Sport();
            sport.setId(re.getSportId());
            Sport sport1 = sportMapper.selectOne(sport);
            re.setSport(sport1);
        }
        return referees;
    }

    //查询指定教练
    public Referee selectById(Integer id){
        return refereeMapper.selectByPrimaryKey(id);
    }

    //添加教练
    public int addReferee(Referee referee){
        //判断裁判是否已经存在（名字和电话都一样说明存在）
        Example example = new Example(Referee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", referee.getName());
        criteria.andEqualTo("phone", referee.getPhone());
        List<Referee> referees = refereeMapper.selectByExample(example);
        if (referees.size() > 0){     //已经存在
            return 0;
        }

        //不存在则添加
        //先查出sportName对应的sport
//        Sport sport = new Sport();
//        sport.setSportName(sportName);
//        Sport storedSport = sportMapper.selectOne(sport);
//        referee.setSportId(storedSport.getId());
        referee.setStatus(1);
        return refereeMapper.insertSelective(referee);
    }

    //删除项目，实为修改状态为0
    public int delReferee(Integer id){
        Referee referee = refereeMapper.selectByPrimaryKey(id);
        referee.setStatus(0);
        return refereeMapper.updateByPrimaryKeySelective(referee);
    }

    //修改状态
    public int updateRefereeStatus(Integer id, Integer status){
        Referee referee = refereeMapper.selectByPrimaryKey(id);
        referee.setStatus(status);
        return refereeMapper.updateByPrimaryKeySelective(referee);
    }

    public int updateReferee(Referee referee){
        return refereeMapper.updateByPrimaryKeySelective(referee);
    }

    public List<Referee> selectBySportId(Integer sportId) {
        Referee referee = new Referee();
        referee.setSportId(sportId);
        List<Referee> referees = refereeMapper.select(referee);
        for (Referee re : referees) {
            Sport sport = new Sport();
            sport.setId(re.getSportId());
            Sport sport1 = sportMapper.selectOne(sport);
            re.setSport(sport1);
        }
        return referees;
    }
}
