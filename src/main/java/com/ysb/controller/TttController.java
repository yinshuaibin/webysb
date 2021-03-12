package com.ysb.controller;

import com.ysb.annotation.ValidTokenAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author yinshuaibin
 * @date 2020/7/6 9:19
 */
@RestController
public class TttController {

    private StringRedisTemplate redisTemplate;

    private RestTemplate restTemplate;

    @Autowired
    public TttController(StringRedisTemplate redisTemplate, RestTemplate restTemplate){
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }



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

    @RequestMapping("/redisTest")
    public String redisTest(){
        redisTemplate.opsForValue().set("ttt", "沃日沃日", 3, TimeUnit.SECONDS);
        System.out.println(restTemplate);
        return redisTemplate.opsForValue().get("ttt");
    }
}
