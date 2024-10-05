package org.ms_demo.util;


import org.junit.Test;

/**
 * @author AutumnLeaf
 * @date 2024/10/2
 * @Description
 */
public class LockUtilForAccountTest {
    @Test
    public void testLock() {
        LockUtil lockUtil = new LockUtil();
        lockUtil.lock("account");
        System.out.println(lockUtil.lock("account"));
    }
}