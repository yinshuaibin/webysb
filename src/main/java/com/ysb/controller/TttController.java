package com.ysb.controller;

import com.ysb.annotation.ValidTokenAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinshuaibin
 * @date 2020/7/6 9:19
 */
@RestController
public class TttController {

    @ValidTokenAnnotation
    @RequestMapping("/t1")
    public Object ttt(){
        return "12312312";
    }


    @ValidTokenAnnotation("测试内容")
    @RequestMapping("/t2")
    public Object ttt2() {
        return "ttt2";
    }
}
