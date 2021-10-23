package com.ysb.test;

import java.util.Random;

/**
 * @author yinshuaibin
 * @date 2021/5/24 15:59
 * @description
 */
public class RandomTest {


    private int getRandom2(int min, int max){
        Random random = new Random();
        int i = random.nextInt();
        return 0;
    }

    private int getRandom(int min, int max){
        double random = Math.random();
        double result = (max - min) * random + min;
        return (int) result;
    }
}
