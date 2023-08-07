package com.lee.redis;

import com.lee.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "赵六");
        // 获取string数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);
    }

    @Test
    void testSaveUser() {
        redisTemplate.opsForValue().set("user:10", new User("harry", 19));
        User user = (User) redisTemplate.opsForValue().get("user:10");
        System.out.println("user=" + user);
    }
}
