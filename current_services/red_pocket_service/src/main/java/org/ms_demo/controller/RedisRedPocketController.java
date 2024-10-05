package org.ms_demo.controller;

import org.ms_demo.entity.RedPocket;
import org.ms_demo.services.RedPocketRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@RestController
public class RedisRedPocketController {

    @Autowired
    private RedPocketRedisService redPocketRedisService;

    @GetMapping("/get_red_pockets_from_cache")
    public List<RedPocket> get_red_pockets_from_cache() {
        List<RedPocket> redPockets = redPocketRedisService.getRedPockets();
        return redPockets;
    }
}
