package org.ms_demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "directRabbitQueue")
public class DirectMessageListener {
    @RabbitHandler
    public void directMessage(String message) {
        System.out.println(message);
    }
}
