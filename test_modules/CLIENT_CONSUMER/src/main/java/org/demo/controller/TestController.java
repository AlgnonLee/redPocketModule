package org.demo.controller;

import org.demo.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */

@RestController
public class TestController {

    @Autowired
    private TestClient testClient;

    @GetMapping("/test")
    public String test(){
        return testClient.Try();
    }
}
