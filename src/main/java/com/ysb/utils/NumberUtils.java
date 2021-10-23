package com.ysb.utils;

import java.util.regex.Pattern;

/**
 * @author yinshuaibin
 * @date 2021/4/19 10:17
 * @description
 */
public class NumberUtils {

    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    /**
     * 判断字符串是否是罗马数字(该方法有点问题)
     * @param s 字符串
     * @return boolean
     */
    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }


    public static void main(String[] args) {
        String s ="1123123123";
        boolean fff = isRomanNumeral("fff");
        System.out.println(fff);
        System.out.println(isRomanNumeral("123"));
    }

}
