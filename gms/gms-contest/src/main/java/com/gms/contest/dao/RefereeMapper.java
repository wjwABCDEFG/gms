/**
 * @author wjw
 * @date 2020/6/9 22:11
 */
package com.gms.contest.dao;

import com.gms.commons.domain.Referee;
import com.gms.commons.domain.Sport;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RefereeMapper  extends Mapper<Referee> {

    @Override
    @Select("select * from referee")
    @Results(id = "refereeMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "sportId", column = "sport_id"),
            @Result(property = "sport", column = "sport_id", javaType = Sport.class, one = @One(select = "com.gms.contest.dao.SportMapper.selectByPrimaryKey"))
    })
    List<Referee> selectAll();


    @Override
    @Select("select * from referee where id = #{id}")
    @ResultMap(value="refereeMap")
    Referee selectByPrimaryKey(Object id);
}
