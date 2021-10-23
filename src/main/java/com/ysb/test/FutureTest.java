package com.ysb.test;

import com.ysb.utils.CustomThreadFactoryBuilder;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author yinshuaibin
 * @date 2021/3/10 17:21
 */
public class FutureTest {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(11, 12, 300L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1000),
            new CustomThreadFactoryBuilder().setNamePrefix("FutureThread").build());

    public static void main(String[] args) throws Exception {
        long start = Clock.systemDefaultZone().millis();
//        CountDownLatch countDownLatch = new CountDownLatch(9);
//        IntStream.range(1, 10).forEach(item ->{
//            threadPoolExecutor.submit(()-> {
//                try {
//                    test(item * 1000L);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                    countDownLatch.countDown();
//                }
//            });
//        });
//        countDownLatch.await();

        List<Future> futureList = new ArrayList<>();
        IntStream.range(1, 10).forEach(item ->{
            futureList.add(threadPoolExecutor.submit(()-> {
                try {
                    test(item * 1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        });
        for (Future future : futureList){
            future.get();
        }
        threadPoolExecutor.shutdown();
        System.out.println("已经全部执行完毕, 共花费时间" + (Clock.systemDefaultZone().millis() - start) + "ms");
    }

    private static void test(Long time) throws Exception{
        String name = Thread.currentThread().getName();
        System.out.println(Thread.currentThread().getName() + "开始执行-" + time + "任务");
        long start = Clock.systemDefaultZone().millis();
        Thread.sleep(time);
        System.out.println(Thread.currentThread().getName() + "任务-" + time + "共执行了 " + (Clock.systemDefaultZone().millis() - start) + " ms");
    }








}
