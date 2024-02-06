package com.lee.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class StringRedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // JSON序列化工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testString() {
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("name", "玛丽");
        // 获取string数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);
    }

    @Test
    void testSaveUser() throws JsonProcessingException {
        // 创建对象
        User user = new User("丁一", 24);
        // 手动序列化
        String jsonStr = mapper.writeValueAsString(user);
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:20", jsonStr);

        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:20");
        // 手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1=" + user1);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:30", "name", "李向");
        stringRedisTemplate.opsForHash().put("user:30", "age", "27");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:30");
        System.out.println("entries=" + entries);
    }
}
