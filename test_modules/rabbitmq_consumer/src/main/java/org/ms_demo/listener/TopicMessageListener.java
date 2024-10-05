package org.ms_demo.listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.#")
public class TopicMessageListener {

    @RabbitHandler
    public void process(String message) {
        System.out.println("TopicMessageListener: " + message);
    }
}
