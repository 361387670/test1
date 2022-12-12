package com.baidu.redis;

import com.baidu.util.RedisUtil;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Slf4j
@SpringBootTest
class RedisApplicationTests {

    private RedisTemplate redisTemplate;


    /*
        @Autowired
        private RedissonClient redissonClient;*/
    /*@Autowired
    private com.baidu.util.RedisUtil  redisUtil;*/

    private static Config config;
    private static Redisson redisson;
    static {
        config = new Config();
        config.useSingleServer().setAddress("redis://"+"127.0.0.1"+":6379").setPassword("123456").setDatabase(0);
        redisson = (Redisson)Redisson.create(config);
    }

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
    //============================================================================================================//
    // redis使用场景

    @Test
    void contextLoads() {
        // 获取list长度
        Long size = redisTemplate.opsForList().size("test1");
        // 获取长度数据
        Object obj = redisTemplate.opsForList().range("test1", 0,(size-1));
        log.info("结果为:{}",obj.toString());
        System.out.println("结果为"+obj.toString());
    }

    //============================================================================================================//
    // 分布式锁
    static String prefix = "prefix";

    @Test
    public void test6(){
        try {
            RLock lock = redisson.getLock(prefix+" ");
            if (lock.tryLock()){
                log.info("进锁了");
                RLock lock1 = redisson.getLock(prefix + " ");
                System.out.println("和似懂非懂说话的时候说的话");
                return;
            }else {
                log.info("出锁了");
            }
        } finally {

        }
        log.info("结束了");
    }

}
