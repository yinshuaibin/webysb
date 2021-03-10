package com.ysb.spring;

import com.ysb.annotation.ValidTokenAnnotation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author yinshuaibin
 * @date 2021/3/8 15:46
 */
@Configuration
@Aspect
public class ValidTokenAspect {

    @Pointcut("@annotation(com.ysb.annotation.ValidTokenAnnotation)")
    public void valid(){}

    @Before("valid() && @annotation(validToken)")
    public void before(ValidTokenAnnotation validToken){
        System.out.println(validToken.value());
    }
}
