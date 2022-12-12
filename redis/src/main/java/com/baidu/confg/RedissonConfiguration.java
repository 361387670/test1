package com.baidu.confg;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


//@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedissonConfiguration {
    private final JedisConnectionFactory jedisConnectionFactory;
    //@Value("${redisson.url.prefix}")
    private String prefix="redis://";
    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        StringBuilder address = new StringBuilder();
        address.append(prefix);
        address.append(jedisConnectionFactory.getHostName());
        address.append(":");
        address.append(jedisConnectionFactory.getPort());
        config.useSingleServer().setAddress(address.toString())
                .setPassword(jedisConnectionFactory.getPassword());
        return Redisson.create(config);
    }
}
