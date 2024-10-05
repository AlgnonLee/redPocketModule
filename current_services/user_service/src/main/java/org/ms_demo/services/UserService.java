package org.ms_demo.services;

import org.ms_demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getUsers();

    public String getPasswordByUsername(String username);

    public User getUserByUsername(String username);

    public int addUser(User user);

    public int updateUser(User user);

    public int deleteUser(int uid);

    public List<User> getUsersByPage(int page, int size);

    public Long addRedisSession(String username,String session_id);

    public String getRedisSession(String username);
}
