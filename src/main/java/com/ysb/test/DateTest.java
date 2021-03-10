package com.ysb.test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * @author yinshuaibin
 * @date 2021/3/10 17:03
 */
public class DateTest {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        // 获取1970 1 1 0 0 0 000到现在的毫秒
        System.currentTimeMillis();
        Clock.systemDefaultZone().millis();
        Calendar.getInstance().getTimeInMillis();
        // 获取当月第一天
        LocalDate nowDate = LocalDate.now();
        LocalDate of = LocalDate.of(nowDate.getYear(), nowDate.getMonth(), 1);
        // TemporalAdjusters 提供了很多的api, 用来帮助我们获取时间, 例如当月最后/第一天
        LocalDate with = nowDate.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println(with);
        System.out.println(of);
        // 日期减   日期加 plus
        LocalDate localDate = nowDate.minusDays(1);
        // 日期比较, 两个同一时间比较总是false
        boolean before = nowDate.isBefore(nowDate);
        boolean isAfter = nowDate.isAfter(nowDate);
        System.out.println(before + "---" + isAfter);
    }
}
