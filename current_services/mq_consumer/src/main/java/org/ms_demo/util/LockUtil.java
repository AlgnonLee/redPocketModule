package org.ms_demo.util;

import org.ms_demo.config.RedissonConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author AutumnLeaf
 * @date 2024/10/2
 * @Description
 */
public class LockUtil {

    private static final RedissonClient redisson = RedissonConfig.getRedisson();
    private static final String LOCK_KEY_PREFIX = "lock_";

    public static boolean lock(String lockKey) throws InterruptedException {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);

        boolean tryLock = lock.tryLock(30, TimeUnit.SECONDS);
        return tryLock;

    }

    public static boolean isMyLock(String lockKey) {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);
        return lock.isHeldByCurrentThread();
    }

    public static void unlock(String lockKey) {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);
        lock.unlock();
    }
}
