package com.qfedu.seckillsysy.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void test(){
        //通过opsForValue()，获取valuOperation对象，操作字符串类型
        redisTemplate.opsForValue().set("age","56");//普通的key  values数据
    }
    //setnx   如果存在 就直接返回    若果不存在就创建

    /*加锁
    * @param  key 商品 id
    * @param value 随机值
    * */

    public boolean lock(String key, String  value){
        //setIfAbsent  作用 setnx 如果不存在设置key-value 返回true

        Boolean ret = redisTemplate.opsForValue().setIfAbsent(key, value);
         if(ret){
            //设置key 的超时时间  TimeUnit 设置时间的单位
            redisTemplate.expire(key,5, TimeUnit.SECONDS);
            return  true;
        }
        return  false;
    }



    /*
     *解锁 通过相同的key value 保证解同一把锁
     * @param  key 商品 id
     * @param value 随机值
     * */
    public  boolean unlock(String key, String value){
        //先根据key找到 判断是否存在
        String v = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(v) && v.equals(value)) {
            //删除指定key值的数据
            redisTemplate.delete(key);
        }
        return true;
    }
}
