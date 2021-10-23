package com.ysb.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author abin
 */
public class Md5Utils {
    /**
     * md5加密字符串, 32位加密
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String MD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte b[] = md5.digest();

            StringBuilder sb = new StringBuilder("");
            for (int value : b) {
                int i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            //32位加密
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
