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
public class GateWayApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateWayApp.class, args);
        System.out.println( "Hello Gateway!" );
    }
}
