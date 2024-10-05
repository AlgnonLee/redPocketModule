package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RabbitMQProducer
{
    public static void main( String[] args )
    {
        SpringApplication.run(RabbitMQProducer.class, args);
        System.out.println( "Hello World!" );
    }
}
