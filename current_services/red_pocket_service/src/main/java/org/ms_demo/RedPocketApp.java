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
public class RedPocketApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RedPocketApp.class, args);
        System.out.println( "Hello Red_pocket!" );
    }
}
