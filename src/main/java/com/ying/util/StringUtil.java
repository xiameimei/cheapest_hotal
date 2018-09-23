package com.ying.util;

/**
 * Created by Chloe on 2018/9/22.
 */
public class StringUtil {
    public static boolean isEmptyString(String str) {
        if (str ==null || str.isEmpty()) {
            return true;
        }
        return false;
    }
}
