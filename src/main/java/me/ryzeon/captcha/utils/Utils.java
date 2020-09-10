package me.ryzeon.captcha.utils;

import java.util.Random;

/**
 * Created by Ryzeon
 * Project: SimpleGUICatpcha
 * Date: 10/09/2020 @ 09:07
 * Template by Elb1to
 */

public class Utils {

    public static int randomInteger(int min, int max) {
        Random r = new Random();
        int realMin = Math.min(min, max);
        int realMax = Math.max(min, max);
        int exclusiveSize = realMax - realMin;
        return r.nextInt(exclusiveSize + 1) + min;
    }
}
