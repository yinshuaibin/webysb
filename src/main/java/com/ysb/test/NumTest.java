package com.ysb.test;

/**
 * @author yinshuaibin
 * @date 2021/3/10 17:54
 */
public class NumTest {
    public static void main(String[] args) {
        /**
         * 正数是直接用原码表示的，如单字节5，在计算机中就表示为：0000 0101。
         * 负数以其正值的补码形式表示，如单字节-5，在计算机中表示为1111 1011。
         * 正数的反码和补码都与原码相同。
         * 而负数的反码为对该数的原码除符号位外各位取反。
         * 负数的补码为对该数的原码除符号位外各位取反，然后在最后一位加1
         *
         * 原码表示法规定：用符号位和数值表示带符号数，正数的符号位用“0”表示，负数的符号位用“1”表示，数值部分用二进制形式表示。
         * 反码表示法规定：正数的反码与原码相同，负数的反码为对该数的原码除符号位外各位取反。
         * 补码表示法规定：正数的补码与原码相同，负数的补码为对该数的原码除符号位外各位取反，然后在最后一位加1.
         * 正零和负零的补码相同，[+0]补=[-0]补=0000 0000B
         */
        int n = 4;
        System.out.println(1<<3);
        System.out.println((n & 1) == 0 ? "偶数" : "奇数");
        // 右移n位 相当于 / 2的n次方
        System.out.println(32 >> 4);
        // 左移n位 相当于 * 2的n次方
        System.out.println(4 << 3);
        // 如果参与运算的两个数其中一个是整型，那么整型可以自动提升到浮点型 6.0
        System.out.println(1.2 + 24 / 5);
        // 在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况 5.2
        System.out.println(1.2 + 24.0 / 5);
        // 如果要对浮点数进行四舍五入，可以对浮点数加上0.5再强制转型 10
        System.out.println((int)9.5 + 0.5);
    }

    /**
     * 求 1 到 x 相加的结果
     * @param x x
     * @return int
     */
    private static int sum(int x){
        return (1 + x) * x / 2;
    }

    /**
     * 求平方根
     * @param x x
     * @param y y
     * @return 结果
     */
    private static int[] pfg(int x, int y){
        return null;
    }
}
