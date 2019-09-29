package com.study.mq.controller;

import com.study.mq.config.RedisOperator;
import com.study.mq.untils.JsonResult;
import com.study.mq.untils.JsonUtils;
import com.study.mq.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * 标题: 整合redis<br>
 * 描述: redis访问<br>
 *
 * @author zc
 * @date 2018/04/26
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/test")
    public JsonResult test() {
        strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");
        User user = new User();
        user.setId(100111);
        user.setName("imooc");
        user.setPassword("abc123");
        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));
        User jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), User.class);
        return JsonResult.ok(jsonUser);
    }

    @RequestMapping("/getJsonList")
    public JsonResult getJsonList() {

        User user = new User();
        user.setName("慕课网");
        user.setPassword("123456");

        User u1 = new User();
        u1.setName("imooc");
        u1.setPassword("123456");

        User u2 = new User();
        u2.setName("hello imooc");
        u2.setPassword("123456");

        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(u1);
        userList.add(u2);
        redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);
        String userListJson = redis.get("json:info:userlist");
        List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);
        return JsonResult.ok(userListBorn);
    }
}
