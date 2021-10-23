package com.ysb.test;

import com.ysb.utils.CustomThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yinshuaibin
 * @date 2021/4/29 9:59
 * @description
 */
public class Test {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 300L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1000),
            new CustomThreadFactoryBuilder().setNamePrefix("FutureThread").build());

    private static Semaphore sp = new Semaphore(3);

    private static int a = 0;

    public static void main(String[] args) throws InterruptedException {
//        IntStream.range(0, 10000).forEach(x->
//            threadPoolExecutor.execute(()-> insert(x))
//        );
        while (true){
            a++;
            threadPoolExecutor.execute(()-> insert(a));
        }
    }

    private static void insert(int a){
        // 执行查询操作并记录查询的最后一条数据
        try {
            sp.acquire();
            System.out.println(a + "开始执行写入操作");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(a + "执行完毕");
            sp.release();
        }
    }
}
