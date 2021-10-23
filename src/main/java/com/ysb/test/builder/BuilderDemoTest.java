package com.ysb.test.builder;

/**
 * @author yinshuaibin
 * @date 2021/4/12 11:52
 * @description
 */
public class BuilderDemoTest {

    public static void main(String[] args) {
        BuilderDemo build = new BuilderDemo.Builder("111", "2222")
                .notNecessary(1)
                .notNecessary2(1)
                .build();

        BuilderDemo build2 = new BuilderDemo.Builder("111", "2222")
                .build();
        System.out.println(build);
        System.out.println(build2);
    }
}