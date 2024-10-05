package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@EnableEurekaClient
@SpringBootApplication
public class ClientProvider {

    public static void main(String[] args) {
        SpringApplication.run(ClientProvider.class, args);
    }
}
