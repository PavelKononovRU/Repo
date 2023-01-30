package com.exchangeinformant.repository;

import com.exchangeinformant.dto.NameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NameRepository {
    public static final String HASH_KEY = "Name";

    @Autowired
    private RedisTemplate redisTemplate;

    public NameDTO save(NameDTO nameDTO) {
        redisTemplate.opsForHash().put(HASH_KEY, nameDTO.getSecureCode(), nameDTO);
        return nameDTO;
    }

    public List<NameDTO> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }
}
