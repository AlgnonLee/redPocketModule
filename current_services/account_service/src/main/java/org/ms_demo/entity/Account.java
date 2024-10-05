package org.ms_demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Data
@TableName("account")
public class Account {

    @TableId
    private Integer uid;
    private float balance;


}
