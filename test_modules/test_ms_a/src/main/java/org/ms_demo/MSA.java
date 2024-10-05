package org.ms_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class MSA
{
    public static void main( String[] args )
    {
        SpringApplication.run(MSA.class, args);
        System.out.println( "Hello World!" );
    }
}
