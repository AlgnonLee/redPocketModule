package org.ms_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Configuration
public class InstantRedPocketConfig {

    public static final String INSTANT_RED_POCKET_EXCHANGE = "INSTANT_RED_POCKET_EXCHANGE";

    public static final String INSTANT_RED_POCKET_QUEUE = "INSTANT_RED_POCKET_QUEUE";

    @Bean
    public Exchange instantRedPocketExchange() {
        return new DirectExchange(INSTANT_RED_POCKET_EXCHANGE);
    }

    @Bean
    public Queue instantRedPocketQueue() {
        return new Queue(INSTANT_RED_POCKET_QUEUE);
    }

    @Bean
    public Binding instantRedPocketBinding() {
        return BindingBuilder.bind(instantRedPocketQueue()).to(instantRedPocketExchange()).with(INSTANT_RED_POCKET_QUEUE).noargs();
    }
}
