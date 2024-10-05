package org.ms_demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author AutumnLeaf
 * @date 2024/10/2
 * @Description
 */
public class RedissonConfig {

    private static Config config = new Config();

    private static RedissonClient redisson;

    static {
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");


        redisson = Redisson.create(config);
    }

    public static RedissonClient getRedisson() {
        return redisson;
    }
}
