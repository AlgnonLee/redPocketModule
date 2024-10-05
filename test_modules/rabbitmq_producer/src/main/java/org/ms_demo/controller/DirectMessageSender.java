package org.ms_demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class DirectMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDMessage/{message}")
    public String sendMessage(@PathVariable String message){
        rabbitTemplate.convertAndSend("directExchange", "directMessage", message);
        return message;
    }
}
