package com.ysb.test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author yinshuaibin
 * @date 2021/3/10 11:46
 */
public class BigNumber {
    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("9999999").pow(99);
        float v = b1.floatValue();
        // Infinity  超出了最大值, 将会出现此结果
        System.out.println(v);
        BigDecimal b = new BigDecimal("123000");
        // 去除末尾的0
        BigDecimal bigDecimal = b.stripTrailingZeros();
        BigDecimal b2 = new BigDecimal("1234.00");
        BigDecimal bigDecimal1 = b2.stripTrailingZeros();
        // 返回此BigDecimal的精度。如果为零或正数，精度数字在小数点右侧的数字。
        // 如果为负数，数的非标度值乘以10比例的负数的幂值。例如，精度为-3表示非标度值乘以1000
        System.out.println(bigDecimal.scale());
        System.out.println(bigDecimal1.scale());
    }
}
