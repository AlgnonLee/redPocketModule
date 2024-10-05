package org.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
