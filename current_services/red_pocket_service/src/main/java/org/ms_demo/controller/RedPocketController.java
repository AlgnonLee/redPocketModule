package org.ms_demo.controller;

import org.ms_demo.entity.ApiResult;
import org.ms_demo.entity.RedPocket;
import org.ms_demo.services.RedPocketRedisService;
import org.ms_demo.services.RedPocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedPocketController {

    @Autowired
    private RedPocketService redPocketService;

    @Autowired
    private RedPocketRedisService redPocketRedisService;

    @PostMapping("/add_red_pocket_to_db")
    public ApiResult addRedPocketToDb(@RequestBody RedPocket redPocket){
        int i = redPocketService.addRedPocket(redPocket);
        ApiResult apiResult = new ApiResult();
        if(i>0){
            apiResult.setCode(200);
            apiResult.setMessage("红包添加成功");
            apiResult.setStatus(true);
            return apiResult;
        }else {
            apiResult.setCode(500);
            apiResult.setStatus(false);
            apiResult.setMessage("红包添加出错");
            return apiResult;
        }
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
