package org.ms_demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@Configuration
public class GainRedPocketConfig {

    public static final String GAIN_INSTANT_RED_POCKET_EXCHANGE = "GAIN_INSTANT_RED_POCKET_EXCHANGE";

    public static final String GAIN_INSTANT_RED_POCKET_QUEUE = "GAIN_INSTANT_RED_POCKET_QUEUE";

    @Bean
    public DirectExchange gainInstantRedPocketExchange() {
        return new DirectExchange(GAIN_INSTANT_RED_POCKET_EXCHANGE);
    }

    @Bean
    public Queue gainInstantRedPocketQueue() {
        return new Queue(GAIN_INSTANT_RED_POCKET_QUEUE);
    }

    @Bean
    public Binding gainInstantRedPocketBinding() {
        return BindingBuilder.bind(gainInstantRedPocketQueue()).to(gainInstantRedPocketExchange()).with(GAIN_INSTANT_RED_POCKET_QUEUE);
    }
}
