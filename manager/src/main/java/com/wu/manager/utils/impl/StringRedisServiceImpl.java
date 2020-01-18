package com.wu.manager.utils.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.manager.utils.StringRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: iosbbs
 * @description: 自定义redis service实现类（String类型）
 * @author: Wu
 * @create: 2020-01-17 23:56
 **/

@Service
public class StringRedisServiceImpl implements StringRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //存入的键值均为字符串
    public void setString(String key,String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    //存入的key为字符串，值为对象，存入时转化为json格式的字符
    @Override
    public void setObject(String key, Object o) {
        stringRedisTemplate.opsForValue().set(key, JsonUtils.objectToJson(o));
    }

    //根据键取出value
    public String getString(String key) {
        String result = stringRedisTemplate.opsForValue().get(key);
        return result;
    }
}
