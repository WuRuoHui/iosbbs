package com.wu.manager;

import com.wu.manager.mapper.GameExtMapper;
import com.wu.manager.mapper.LeftNavExtMapper;
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
    @Autowired
    private GameExtMapper gameExtMapper;
    @Autowired
    private LeftNavExtMapper leftNavExtMapper;

    @Test
    void contextLoads() {
        /*List<LeftNav> leftNavs = leftNavExtMapper.selectLeftMenuByRoleId(2, 1, 2);
        for (LeftNav leftNav : leftNavs) {
            System.out.println(leftNav);
        }*/
        /*List<Integer> ids = gameExtMapper.selectIdsByNameSearch("奇迹之剑");
        for (Integer id : ids) {
            System.out.println(id);
        }*/
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
