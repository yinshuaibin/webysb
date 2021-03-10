package com.ysb.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2021/3/10 17:49
 *  enterSet iterator方式最快, 删除线程安全
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(15);
        // 不论是否存在, 都进行操作, 操作后放入map中, 返回最新的值
        String a1 = map.compute("a", (k, v) -> v = "aaa");
        System.out.println(a1);
        System.out.println(map);
        // 如果存在则返回值, 如果不存在, 初始化并返回值
        String b = map.computeIfAbsent("b", key -> "bbb");
        System.out.println(b);
        System.out.println(map);
        // 只有存在的时候才生效, 修改值, 并返回
        String c = map.computeIfPresent("c", (k, v) -> v = "ccc");
        String updateA = map.computeIfPresent("a", (k, v) -> v = "ccc");
        System.out.println(c);
        System.out.println(updateA);
        System.out.println(map);

        // stream单线程, 实际idea更推荐普通的entrySet方式遍历, 即下边这种方法
        map.entrySet().stream().forEach(System.out::println);
        map.forEach((key, value) -> {
            System.out.println(key);
        });
        // 多线程遍历
        map.entrySet().parallelStream().forEach(System.out::println);
    }
}
