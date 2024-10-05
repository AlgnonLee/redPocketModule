package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MQConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(MQConsumerApp.class, args);
        System.out.println( "Hello World!" );
    }
}
