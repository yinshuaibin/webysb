package com.ysb.test;

import com.ysb.controller.TttController;
import com.ysb.utils.JacksonUtils;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yinshuaibin
 * @date 2020/9/1 10:55
 */
public class TestFor {
    public static void main(String[] args){
        List<String> list = new ArrayList<String>(){{add("1");}};
        for (String s : list) {
            System.out.println(s);
        }
        // 下列方式的for可以看成while(true)无限循环
        for (int x = 0; ; x++){
            if (x == 10000){
                throw new RuntimeException();
            }
        }
    }
}
