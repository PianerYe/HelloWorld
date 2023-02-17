package com.itheima.helloworld.com.itheima.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 使用Jedis操作Redis
 * */
public class JedisTest {
    @Test
    public void testRedis1(){
        //获取连接
        Jedis jedis = new Jedis("192.168.125.100",6379);
        jedis.auth("123456");
        //执行具体操作
        jedis.set("username","xiaoming");

        String username = jedis.get("username");
        System.out.println(username);

//        jedis.del("username");
//        jedis.hset("myhash","addr","beijing");
//
//        String addr = jedis.hget("myhash", "addr");
//        System.out.println(addr);
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        //关闭链接
        jedis.close();
    }
}
