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
public class EurekaServerC
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerC.class, args);
        System.out.println( "Hello World!" );
    }
}
