package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@EnableEurekaServer
@SpringBootApplication
public class TestEureka {
    public static void main(String[] args) {
        SpringApplication.run(TestEureka.class, args);
        System.out.println("Test eureka server started");
    }
}
