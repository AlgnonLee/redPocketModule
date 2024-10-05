package org.ms_demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Component
@FeignClient(value = "account-service")
public interface AccountClient {


}
