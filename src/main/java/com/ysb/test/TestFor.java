package com.ysb.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yinshuaibin
 * @date 2020/9/1 10:55
 */
public class TestFor {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(){{add("1");}};
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }

        for (int x = 0; ; x++){
            System.out.println(x);
        }
    }
}
