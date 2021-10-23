package com.ysb.test;

/**
 * @author yinshuaibin
 * @date 2021/5/26 17:13
 * @description 被忽略的异常(finally抛出异常后, catch中的异常就消失了)
 */
public class ExceptionTest {

    public static void main(String[] args) throws Exception {
        Exception throwE = null;
        try {
            Integer.parseInt("abc");
        }catch (Exception e){
            throwE = e;
            throw  throwE;
        }finally {
            IllegalAccessException illegalAccessException = new IllegalAccessException();
            if (throwE != null){
                illegalAccessException.addSuppressed(throwE);
            }
            throw illegalAccessException;
        }
    }
}
