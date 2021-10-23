package com.ysb.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
        BigDecimal d1 = new BigDecimal("123.456789");
        System.out.println(d1.scale());
        // 四舍五入，123.4568
        BigDecimal d2 = d1.setScale(4, RoundingMode.HALF_UP);
        // 直接截断，123.4567
        BigDecimal d3 = d1.setScale(4, RoundingMode.DOWN);
        System.out.println(d2);
        System.out.println(d3);
        // BigDecimal做加减乘时, 不会出现精度丢失, 但是做除法时, 存在无法除进
        BigDecimal bigDecimal2 = new BigDecimal("123.45");
        BigDecimal bigDecimal3 = new BigDecimal("34.56");
        // 除不尽时不指定精度, 出现错误ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result
        // System.out.println(bigDecimal2.divide(bigDecimal3));
        System.out.println(bigDecimal2.divide(bigDecimal3, 10, RoundingMode.HALF_UP));
        // 取余数, 结果为一个数组, 第一个是商, 第二个是余数, 可以根据余数是否为0来判断是不是整数倍
        BigDecimal[] bigDecimals = bigDecimal2.divideAndRemainder(bigDecimal3);
        System.out.println(bigDecimals[0]);
        System.out.println(bigDecimals[1]);
        BigDecimal bigDecimal4 = new BigDecimal("123.4000");
        BigDecimal bigDecimal5 = new BigDecimal("123.4");
        // false, 因为equals方法不仅比较了数值大小, 还比较了scale大小
        System.out.println(bigDecimal4.equals(bigDecimal5));
        // 应该使用compareTo来比较, 分别返回负数, 0, 正数来对应结果, 小于, 等于, 大于
        System.out.println(bigDecimal4.compareTo(bigDecimal5));
    }
}
