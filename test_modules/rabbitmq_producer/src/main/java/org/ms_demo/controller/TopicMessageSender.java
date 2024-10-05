package org.ms_demo.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendToApe")
    public String sendToApe() {
        rabbitTemplate.convertAndSend("topic_exchange","topic.ape", "hello ape");
        return "hello ape";
    }

    @GetMapping("/sendToTest")
    public String sendToTest() {
        rabbitTemplate.convertAndSend("topic_exchange","topic.test", "hello test");
        return "hello test";
    }

}
