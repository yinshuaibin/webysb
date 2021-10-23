package com.ysb.test;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author yinshuaibin
 * @date 2021/4/21 10:19
 * @description 模拟内存泄漏
 */
public class Stack {

    private Object[] element;

    private static int DEFAULT_INITIAL_CAPACITY = 16;

    public int size = 0;

    public Stack(){
        element = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object o){
        ensureCapacity();
        element[size++] = o;
    }

    public Object pop(){
        if (size <= 0){
            throw new EmptyStackException();
        }
        // 此处删除后实际该元素还存在, 只是不再使用, 应该增加置空     element[size] = nll;
        return element[--size];
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity(){
        if(element.length == size){
            element = Arrays.copyOf(element, 2 * size + 1);
        }
    }
}
