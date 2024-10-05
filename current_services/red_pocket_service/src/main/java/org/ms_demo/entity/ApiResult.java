package org.ms_demo.entity;

import lombok.Data;

@Data
public class ApiResult {
    private boolean status;
    private int code;
    private Object message;
}
