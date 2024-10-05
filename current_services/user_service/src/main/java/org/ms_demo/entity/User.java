package org.ms_demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    private long uid;
    private String username;
    private String salt;
    private String password;
    private String nickname;
    private boolean gender;

}
