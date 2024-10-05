package org.ms_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("org.ms_demo.mapper")
public class UserApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApp.class, args);
        System.out.println( "Hello World!" );
    }
}
