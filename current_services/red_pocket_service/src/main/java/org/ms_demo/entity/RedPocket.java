package org.ms_demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@TableName("red_pocket")
public class RedPocket implements Serializable {

    @TableId
    private long redPocketId;
    private String uuid;
    private int uid;
    private int pocketCount;
    private int restPocketCount;
    private double pocketMoney;
    private String redPocketName;
    private String redPocketType;
    private String redPocketDesc;
    private int redPocketStatus;
}
