package org.ms_demo.services.impl;


import lombok.val;
import org.ms_demo.entity.User;
import org.ms_demo.mapper.UserMapper;
import org.ms_demo.services.UserService;
import org.ms_demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    public String getPasswordByUsername(String username) {
        return userMapper.getPassword(username);
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsrn(username);
    }

    public int addUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    public int updateUser(User user) {
        int i = userMapper.updateById(user);
        return i;
    }

    public int deleteUser(int uid) {
        int i = userMapper.deleteById(uid);
        return i;
    }

    public List<User> getUsersByPage(int page, int size) {
        return userMapper.getUsersByPage(page, size);
    }

    @Override
    public Long addRedisSession(String username,String session_id) {
        Long hset = JedisUtil.hset("user_in", username, session_id);
        return hset;
    }

    @Override
    public String getRedisSession(String username) {
        String user_session = JedisUtil.hget("user_in", username);
        return user_session;
    }
}
