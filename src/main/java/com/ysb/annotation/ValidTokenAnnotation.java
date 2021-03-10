package com.ysb.annotation;

import java.lang.annotation.*;

/**
 * @author yinshuaibin
 * @date 2021/3/8 15:42
 */
// 注解信息会被添加到java文档中
@Documented
// 注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidTokenAnnotation {
    String value() default "";
}
