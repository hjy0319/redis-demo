package com.hujy.redisdemo.service;

import com.hujy.redisdemo.entity.User;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-10-07 13:14
 */
public interface CacheService {
    User saveUser(User user);
    User getUser(Integer id);
    User updateUser(User user);
    Integer removeUser(Integer id);
}
