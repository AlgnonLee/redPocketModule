package org.ms_demo.controller;

import org.ms_demo.config.DelayMessageConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 * 发送延迟消息
 */

@RestController
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send_delay_message")
    public String sendDelayMessage() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-delay", 10*1000);
        rabbitTemplate.convertAndSend(DelayMessageConfig.DELAY_EXCHANGE_NAME, DelayMessageConfig.DELAY_QUEUE_NAME, "hello",new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 10*1000);
                return message;
            }
        });
        System.out.println("message sent,Date+");
        return "success";
    }
}
