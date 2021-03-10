package com.ysb.test;

import lombok.Data;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yinshuaibin
 * @date 2021/3/10 15:41
 */
public class GroupBySortFilterTest {

    public static void main(String[] args) {
        List<People> peopleList = new ArrayList<People>(){{
            add(new People("小明", 18));
            add(new People("小明", 19));
            add(new People("小明", 20));
            add(new People("小丽", 18));
            add(new People("小丽", 19));
            add(new People("小丽", 20));
        }};
        // 根据age分组
        Map<Integer, List<People>> collect = peopleList.stream().collect(Collectors.groupingBy(People::getAge));
        // 嵌套分组, 先根据age分组, 再根据name分组
        Map<Integer, Map<String, List<People>>> collect1 = peopleList.stream().collect(Collectors.groupingBy(People::getAge, Collectors.groupingBy(People::getName)));
        // 获得结果 小明,18
        System.out.println(collect1.get(18).get("小明"));
        // filter 过滤
        List<People> collect2 = peopleList.stream().filter(a -> a.getAge() > 19).collect(Collectors.toList());
        System.out.println(collect2);
        // 根据age升序排序
        peopleList.sort(Comparator.comparingInt(People::getAge));
        System.out.println(peopleList);
        // 根据age降序排序
        peopleList.sort(Comparator.comparing(People::getAge).reversed());
        System.out.println(peopleList);
        // 根据age升序排序后, 如果age相同, 再根据name排序
        peopleList.sort(Comparator.comparing(People::getAge).reversed().thenComparing(People::getName).reversed());
        System.out.println(peopleList);
    }

}

@Data
class People{
    private String name;
    private int age;

    People(String name, int age){
        this.name = name;
        this.age = age;
    }
}
