package com.wu.manager;

import com.wu.manager.mapper.UserGradeMapper;
import com.wu.manager.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ManagerApplicationTests {

    @Autowired
    private UserGradeMapper userGradeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value(value = "${BBS.USER.GRADE}")
    private String BBS_USER_GRADE_PREFIX;

    @Test
    void contextLoads() {
        /*List<UserGrade> userGrades = userGradeMapper.selectByExample(new UserGradeExample());
        Map<Integer,List<UserGrade>> map = new HashMap<>();
        map.put(userGrades.size(),userGrades);
        redisTemplate.opsForHash().putAll(BBS_USER_GRADE_PREFIX,map);
        Map entries = redisTemplate.opsForHash().entries(BBS_USER_GRADE_PREFIX);
        Map.Entry<Integer, List<UserGrade>> integerListEntry = map.entrySet().stream().findFirst().get();
        integerListEntry.getKey();
        integerListEntry.getValue();*/

    }

}
