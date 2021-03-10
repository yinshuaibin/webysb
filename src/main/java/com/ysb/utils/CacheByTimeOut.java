package com.ysb.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author abin
 */
public class CacheByTimeOut {

    private static final ConcurrentHashMap<String, Entity> CACHE = new ConcurrentHashMap<>();

    private final static ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();

    public static void put(String key, Object value){
        CACHE.remove(key);
        CACHE.put(key, new Entity(value, null));
    }

    public static void put(String key, Object value, long timeOut){
        CACHE.remove(key);
        Future future = null;
        if(timeOut > 0){
            future = SERVICE.schedule(() -> {
                synchronized (CACHE) {
                    CACHE.remove(key);
                }
            }, timeOut, TimeUnit.MILLISECONDS);
        }
        CACHE.put(key, new Entity(value, future));
    }

    public static Object get(String key){
        Entity entity = CACHE.get(key);
        return entity == null ? null : entity.value;
    }

    public static <T>T get(String key, Class<T> tClass){
        Object o = CacheByTimeOut.get(key);
        return o == null ? null : tClass.cast(o);
    }
    
    public static Object remove(String key){
        Entity remove = CACHE.remove(key);
        if(remove != null){
            remove.future.cancel(true);
        }
        return remove == null ? null : remove.value;
    }

    public static int size(){
        return CACHE.size();
    }
    
    public static Set<String> getAllKey (){
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, Entity> entityEntry : CACHE.entrySet()){
            result.add(entityEntry.getKey());
        }
        return result;
    }
    
    private static class Entity{
        /**
         * 存入的数据
         */
        Object value;

        /**
         * 过期清除定时器
         */
        private Future future;

        Entity (Object value, Future future){
            this.value = value;
            this.future = future;
        }

    }
}
