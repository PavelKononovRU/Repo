package com.exchangeinformant.repository;

import com.exchangeinformant.util.Name;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NameRepositoryRedis {
    private final RedisTemplate<String, Name> redisTemplate;

    public NameRepositoryRedis(RedisTemplate<String, Name> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void save(String serviceName, Name name) {
        redisTemplate.opsForHash().put(serviceName, name.getSecureCode(), name);
    }

    public Name get(String serviceName, String secureCode) {
        return (Name) redisTemplate.opsForHash().get(serviceName, secureCode);
    }

    public List<Object> findAll(String serviceName) {
        return redisTemplate.opsForHash().values(serviceName);
    }
}
