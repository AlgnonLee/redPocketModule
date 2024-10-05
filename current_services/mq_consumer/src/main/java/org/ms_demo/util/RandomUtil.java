package org.ms_demo.util;

import java.util.Random;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
public class RandomUtil {

    private static Random random = new Random();

    public static double getRandomDouble(double restMoney) {
        double max = restMoney/8;
        double min = max/2;
        double gainMoney = random.nextDouble() * max;
        return gainMoney+min;
    }
}
