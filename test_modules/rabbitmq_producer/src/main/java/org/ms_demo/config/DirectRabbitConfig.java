package org.ms_demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directRabbitQueue() {
        return new Queue("directRabbitQueue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    @Bean
    public Binding directRabbitBinding() {
        return BindingBuilder.bind(directRabbitQueue()).to(directExchange()).with("directMessage");
    }
}
