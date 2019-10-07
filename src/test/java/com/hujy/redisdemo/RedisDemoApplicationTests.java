package com.hujy.redisdemo;

import com.hujy.redisdemo.entity.User;
import com.hujy.redisdemo.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void test() {
        saveUser();
        getUser();
        updateUser();
        removeUser();
    }

    private void saveUser() {
        User u = new User();
        u.setId(1);
        u.setName("张三");
        u.setAge(25);
        User user = cacheService.saveUser(u);
        System.out.println("【save】" + user);
    }

    private void getUser() {
        User user = cacheService.getUser(1);
        System.out.println("【get】" + user);
    }

    private void updateUser() {
        User u = new User();
        u.setId(1);
        u.setName("李四");
        u.setAge(30);
        User user = cacheService.updateUser(u);
        System.out.println("【update】" + user);
        User user1 = cacheService.getUser(1);
        System.out.println("【get】" + user1);
    }

    private void removeUser() {
        Integer r = cacheService.removeUser(1);
        System.out.println("【remove】" + r);
    }
}
