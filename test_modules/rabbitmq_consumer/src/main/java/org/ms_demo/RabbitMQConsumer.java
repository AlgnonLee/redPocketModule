package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RabbitMQConsumer
{
    public static void main( String[] args )
    {
        SpringApplication.run(RabbitMQConsumer.class, args);
        System.out.println( "Hello World!" );
    }
}
