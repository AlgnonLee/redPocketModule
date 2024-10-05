package org.ms_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AutumnLeaf
 * @date 2024/10/2
 * @Description
 */

@Configuration
public class DelayedRedPocketConfig {

    public static final String DELAYED_RED_POCKET_EXCHANGE = "DELAYED_RED_POCKET_EXCHANGE";

    public static final String DELAYED_RED_POCKET_QUEUE = "DELAYED_RED_POCKET_QUEUE";


    @Bean
    public CustomExchange delayedRedPocketExchange() {
        return new CustomExchange(DELAYED_RED_POCKET_EXCHANGE,"x-delayed-message", true, false);
    }

    @Bean
    public Queue delayedRedPocketQueue() {
        return new Queue(DELAYED_RED_POCKET_QUEUE);
    }

    @Bean
    public Binding delayedRedPocketBinding() {
        return BindingBuilder.bind(delayedRedPocketQueue()).to(delayedRedPocketExchange()).with(DELAYED_RED_POCKET_QUEUE).noargs();
    }
}
