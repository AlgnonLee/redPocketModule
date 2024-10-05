package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class MQProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(MQProviderApp.class, args);
        System.out.println( "Hello MQProvider!" );
    }
}
