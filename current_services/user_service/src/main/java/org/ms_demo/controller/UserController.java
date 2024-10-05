package org.ms_demo.controller;

import org.ms_demo.entity.ApiResult;
import org.ms_demo.entity.User;
import org.ms_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class UserController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        if(user.getPassword().equals(userService.getPasswordByUsername(user.getUsername()))) {
            ApiResult apiResult = new ApiResult();
            UUID uuid = UUID.randomUUID();
            Cookie cookie = new Cookie("user-session", uuid.toString());
            cookie.setMaxAge(30*60);
            cookie.setPath("/");
            response.addCookie(cookie);
            userService.addRedisSession(user.getUsername(),uuid.toString());
            apiResult.setCode(200);
            apiResult.setStatus(true);
            apiResult.setMessage("登录成功");
            return apiResult;
        }else {
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(400);
            apiResult.setStatus(false);
            apiResult.setMessage("登录失败");
            return apiResult;
        }
    };

    @PostMapping("/register")
    public ApiResult register(@RequestBody User user) {
        User userByUsername = userService.getUserByUsername(user.getUsername());
        if(userByUsername != null) {
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(400);
            apiResult.setStatus(false);
            apiResult.setMessage("该账户不可用");
            return apiResult;
        }
        int result = userService.addUser(user);
        ApiResult apiResult = new ApiResult();
        if(result > 0) {
            apiResult.setCode(200);
            apiResult.setStatus(true);
            apiResult.setMessage("注册成功");
            return apiResult;
        }else{
            apiResult.setCode(400);
            apiResult.setStatus(false);
            apiResult.setMessage("注册失败");
            return apiResult;
        }
    }

    @GetMapping("/test")
    public ApiResult test(HttpServletResponse response) {
        response.addCookie(new Cookie("name", "test"));
        ApiResult apiResult = new ApiResult();
        UUID uuid = UUID.randomUUID();
        apiResult.setCode(200);
        apiResult.setStatus(true);
        apiResult.setMessage(uuid.toString());
        return apiResult;
    }

}
