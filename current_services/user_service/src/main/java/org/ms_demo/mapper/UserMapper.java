package org.ms_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ms_demo.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select password from user where username = #{username}")
    public String getPassword(@Param("username") String username);

    @Select("select * from user where uid = #{uid}")
    public User getUser(@Param("uid") int uid);

    @Select("select * from user limit #{page_now},#{page_size}")
    public List<User> getUsersByPage(@Param("page_now") int page_now, @Param("page_size") int page_size);

    @Select("select * from user where username = #{usrn}")
    public User getUserByUsrn(@Param("usrn") String usrn);
}
