package org.ms_demo.controller;


import org.ms_demo.entity.RedPocket;
import org.ms_demo.service.ProviderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RedPocketProvider {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/add_red_pocket")
    public String addRedPocket(@RequestBody RedPocket redPocket) {
        redPocket.setUuid(String.valueOf(UUID.randomUUID()));
        providerService.sendMessage(redPocket);
        providerService.sendDelayedMessage(redPocket);
        return "success";
    }

    @PostMapping("/gain_red_pocket")
    public String gainRedPocket(@RequestBody RedPocket redPocket,@RequestParam int uid){
        System.out.println(redPocket);
        providerService.sendGainRedPocketMessage(redPocket,uid);
        return "success";
    }
}
