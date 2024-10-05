package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerB
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerB.class, args);
        System.out.println( "Hello World!" );
    }
}
