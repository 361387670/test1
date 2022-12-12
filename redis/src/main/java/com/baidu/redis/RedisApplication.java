package com.baidu.redis;

import com.baidu.common.entity.House;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
        System.out.println("测试一下了");
        House house = new House();
        house.setCode("311");
        System.out.println(house.toString());
    }

}
