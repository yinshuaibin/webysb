package com.ysb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinshuaibin
 * @date 2020/7/6 9:19
 */
@RestController
public class TttController {


    @RequestMapping("/ttt")
    public Object ttt(){
        return "12312312";
    }
}
