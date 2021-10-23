package com.ysb.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yinshuaibin
 * @date 2021/6/7 14:09
 * @description
 */
public class ReflectionTest {

    public static void main(String[] args) throws Exception {
        Class dogClass = Dog.class;
        Object o = dogClass.newInstance();
        Method setColor = dogClass.getDeclaredMethod("setColor", String.class);
        Object red = setColor.invoke(o, "red");
        System.out.println(red);
        Method getColor = dogClass.getDeclaredMethod("getColor", null);
        System.out.println(getColor.invoke(o, null));
        Field color = dogClass.getDeclaredField("color");
        color.setAccessible(true);
        Object o1 = color.get(o);
        System.out.println(o1);


        String s = "1234";
        Class<? extends String> aClass = s.getClass();
        Method substring = aClass.getDeclaredMethod("substring", int.class);
        Object invoke = substring.invoke(s, 1);
        System.out.println(invoke);
        Method substring1 = aClass.getDeclaredMethod("substring", int.class, int.class);
        Object invoke1 = substring1.invoke(s, 1, 3);
        System.out.println(invoke1);

        Class integerClass = Integer.class;
        Method parseInt = integerClass.getDeclaredMethod("parseInt", String.class);
        // 静态方法调用的对象一直为null
        Object invoke2 = parseInt.invoke(null, "333");
        System.out.println(invoke2);

        Class stringBuilderClass = StringBuilder.class;
        Class[] interfaces = stringBuilderClass.getInterfaces();
        System.out.println(Arrays.asList(interfaces));
        Class superclass = stringBuilderClass.getSuperclass();
        System.out.println(superclass);
        Class superclass1 = superclass.getSuperclass();
        System.out.println(superclass1);
        Class superclass2 = superclass1.getSuperclass();
        System.out.println(superclass2);

        // 反射获取的方法, 仍然遵循多态原则
        Method call = Zoo.class.getDeclaredMethod("call");
        call.invoke(new Dog());
    }
}

class Zoo{
    private String name;
    public int age;
    public void call(){
        System.out.println("Zoo......");
    }
}

class Dog extends Zoo{
    private String color;

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public void call(){
        System.out.println("Dog......");
    }
}
