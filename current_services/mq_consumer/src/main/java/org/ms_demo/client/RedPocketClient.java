package org.ms_demo.client;


import org.ms_demo.entity.ApiResult;
import org.ms_demo.entity.RedPocket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient( value = "red-pocket-service")
public interface RedPocketClient {

    @PostMapping("/add_red_pocket_to_db")
    public ApiResult addRedPocketToDb(@RequestBody RedPocket redPocket);

    @GetMapping("/test")
    public String test();
}
