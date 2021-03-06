package com.ysb.config.spring;

import com.ysb.annotation.ValidTokenAnnotation;
import com.ysb.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.TimeUnit;

/**
 * @author yinshuaibin
 * @date 2021/3/8 15:46
 */
@Configuration
@Aspect
public class ValidTokenAspect {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public ValidTokenAspect(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(com.ysb.annotation.ValidTokenAnnotation)")
    public void valid(){}

    @Around("valid() && @annotation(validToken)")
    public Object before(ProceedingJoinPoint jp, ValidTokenAnnotation validToken) throws Throwable {
        String name = jp.getSignature().getName();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String key;
        if ( principal instanceof User){
            User user = (User)principal;
            key = name + user.getUsername();
        }else {
            key = name + principal;
        }
        String s = redisTemplate.opsForValue().get(key);
        if (s == null){
            redisTemplate.opsForValue().set(key, "time", 5, TimeUnit.SECONDS);
            return jp.proceed();
        }
        throw new BusinessException(402, "重复操作");
    }
}
