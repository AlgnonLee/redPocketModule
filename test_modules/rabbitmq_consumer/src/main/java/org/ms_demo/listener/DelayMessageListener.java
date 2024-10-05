package org.ms_demo.listener;

import org.ms_demo.config.DelayMessageConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Component
public class DelayMessageListener {

    @RabbitHandler
    @RabbitListener(queues = DelayMessageConfig.DELAY_QUEUE_NAME)
    public void process(String message) {
        System.out.println(message);
    }
}
