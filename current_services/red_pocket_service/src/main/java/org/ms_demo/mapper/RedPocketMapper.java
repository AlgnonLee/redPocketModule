package org.ms_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ms_demo.entity.RedPocket;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface RedPocketMapper extends BaseMapper<RedPocket> {

    @Select("select * from red_pocket")
    List<RedPocket> selectAll();

    @Select("select * from red_pocket where red_pocket_id = #{rid}")
    RedPocket selectByRedPocketId(Integer rid);

    @Select("select * from red_pocket where uid = #{uid}")
    List<RedPocket> selectByUid(Integer uid);

    @Update("update red_pocket set red_pocket_status = 0 where red_pocket_id = #{rid}")
    int updateRedPocketStatus(int redPocket);
}
