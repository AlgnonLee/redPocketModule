package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class TestConsumer {
    public static void main(String[] args) {
        SpringApplication.run(TestConsumer.class,args);
    }
}
