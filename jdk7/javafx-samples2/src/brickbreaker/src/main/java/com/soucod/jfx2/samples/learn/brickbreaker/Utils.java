package com.soucod.jfx2.samples.learn.brickbreaker;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-11-19:13
 * UpdateDate: 2021-01-11-19:13
 * FileName: Utils
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class Utils {

    private static final java.util.Random RANDOM = new java.util.Random();

    // Returns random integer number from 0 to max - 1
    public static int random(int max) {
        return (int) (RANDOM.nextDouble() * max);
    }

    // Returns sign of the value
    public static int sign(double n) {
        if (n == 0) {
            return 0;
        }
        if (n > 0) {
            return 1;
        } else {
            return -1;
        }
    }

}
