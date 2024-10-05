package org.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */

@FeignClient(value = "client-provider")
public interface TestClient {

    @GetMapping("/test")
    public String Try();
}
