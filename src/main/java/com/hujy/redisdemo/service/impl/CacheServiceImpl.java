package com.hujy.redisdemo.service.impl;

import com.hujy.redisdemo.entity.User;
import com.hujy.redisdemo.service.CacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-10-07 13:23
 */
@Service
public class CacheServiceImpl implements CacheService {
    // 为了简化功能，用一个集合代替数据库
    private Map<Integer, User> db = new ConcurrentHashMap<>();

    @Override
    @CachePut(value = "userCache", key = "'user:' + #result.id")
    public User saveUser(User user) {
        // 保存user信息 省略保存数据库的过程
        db.put(user.getId(), user);
        return user;
    }

    @Override
    @Cacheable(value = "userCache", key = "'user:' + #id", unless = "#result == null")
    public User getUser(Integer id) {
        System.out.println("没有命中缓存，进入业务方法了");
        return db.get(id);
    }

    @Override
    @CachePut(value = "userCache", key = "'user:' + #result.id", condition = "#result != null")
    public User updateUser(User user) {
        // 调用getUser方法，user存在才会更新
        // 基于SpringAOP原理，同类中的方法调用会导致切面失效，因此这里会使getUser的缓存注解会失效
        User u = this.getUser(user.getId());
        if (u == null) {
            return null;
        }
        db.put(user.getId(), u);
        return user;
    }

    @Override
    @CacheEvict(value = "userCache", key = "'user:' + #id", beforeInvocation = true)
    public Integer removeUser(Integer id) {
        db.remove(id);
        return 1;
    }
}
