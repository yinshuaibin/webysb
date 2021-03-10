package com.ysb.test;

/**
 * @author yinshuaibin
 * @date 2021/3/10 17:54
 */
public class NumTest {
    public static void main(String[] args) {
        int n = 3;
        System.out.println((n & 1) == 0 ? "偶数" : "奇数");
        // 右移n位 相当于 /2/n
        System.out.println(8 >> 2);
        // 左移n位 相当于 *2*n
        System.out.println(4 << 3);
    }
}
