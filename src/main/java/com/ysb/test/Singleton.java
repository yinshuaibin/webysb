package com.ysb.test;

/**
 * @author yinshuaibin
 * @date 2021/3/11 10:55
 */
public class Singleton {

    private Singleton(){

    }

    private volatile static Singleton getInstance;

    public static Singleton getSingleton(){
        if (getInstance == null) {
            synchronized (Singleton.class){
                if (getInstance == null){
                    getInstance = new Singleton();
                }
            }
        }
        return getInstance;
    }

}
