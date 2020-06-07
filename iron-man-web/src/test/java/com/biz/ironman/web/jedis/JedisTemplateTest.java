package com.biz.ironman.web.jedis;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 13:47
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class JedisTemplateTest {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void testString(){
        redisTemplate.opsForValue().set("redis","values");
    }
}
