package com.ysb.test;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.StringJoiner;

/**
 * @author yinshuaibin
 * @date 2021/3/10 14:05
 */
public class TestStr {
    public static void main(String[] args) {
        // 不区分大小写的equals
        boolean fff = "fff".equalsIgnoreCase("FFF");
        System.out.println(fff);
        getMemInfo();
        System.out.println();
        getDiskInfo();
        String[] names = {"ABC", "XYZ", "ZOO"};
        String s = names[1];
        names[1] = "cat";
        // s是"XYZ"还是"cat"? 是XYZ
        System.out.println(s);
        for (String name : names) {
            System.out.println(name);
        }
    }
    private static void getDiskInfo() {
        File[] disks = File.listRoots();
        for(File file : disks) {
            System.out.print(file.getPath() + "    ");
            // 空闲空间
            System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 + "M" + "    ");
            // 可用空间
            System.out.print("已经使用 = " + file.getUsableSpace() / 1024 / 1024 + "M" + "    ");
            // 总空间
            System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 + "M" + "    ");
            System.out.println();
        }
    }

    private static void getMemInfo() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
    }

    private static void stringJoiner() {
        StringJoiner sj = new StringJoiner(",", "(",")");

    }
}
