package org.ms_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicMessageConfig {

    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_APE = "topic.ape";
    public static final String TOPIC_ALL = "topic.#";
    public static final String TOPIC_TEST = "topic.test";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue topicTest() {
        return new Queue(TOPIC_TEST);
    }


    @Bean
    public Queue topicApe() {
        return new Queue(TOPIC_APE);
    }

    @Bean
    public Queue topicAll() {
        return new Queue(TOPIC_ALL);
    }

    @Bean
    public Binding bindingTopicApe() {
        return BindingBuilder.bind(topicApe()).to(topicExchange()).with(TOPIC_APE);
    }

    @Bean
    public Binding bindingTopicAll() {
        return BindingBuilder.bind(topicAll()).to(topicExchange()).with(TOPIC_ALL);
    }

    @Bean
    public Binding bindingTopicTest() {
        return BindingBuilder.bind(topicTest()).to(topicExchange()).with(TOPIC_TEST);
    }

}
