package org.ms_demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ms_demo.entity.User;
import org.springframework.stereotype.Component;


@Component
public interface CurrentMapper {

    @Select("select user.* from red_pocket left join user on red_pocket.uid = user.uid where red_pocket.uid = #{uid}")
    public User getUserByUid(@Param("uid") int uid);

}
