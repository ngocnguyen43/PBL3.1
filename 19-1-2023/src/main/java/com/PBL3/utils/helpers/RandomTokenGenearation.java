package com.PBL3.utils.helpers;

import java.util.Random;

public class RandomTokenGenearation {
    public static String getRandomHexString(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.substring(0, numchars);
    }

}
