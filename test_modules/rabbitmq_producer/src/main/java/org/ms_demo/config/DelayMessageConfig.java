package org.ms_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AutumnLeaf
 * @date 2024/9/30
 * @Description
 */

@Configuration
public class DelayMessageConfig {


    public static final String DELAY_QUEUE_NAME = "delay_queue";

    public static final String DELAY_EXCHANGE_NAME = "delay_exchange";


    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE_NAME);
    }

    @Bean
    public CustomExchange delayExchange() {
        return new CustomExchange(DELAY_EXCHANGE_NAME,"x-delayed-message",true,false);
    }


    @Bean
    public Binding bindingDelayQueue() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_QUEUE_NAME).noargs();
    }
}
