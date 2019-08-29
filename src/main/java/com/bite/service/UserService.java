package com.bite.service;

import com.bite.domain.User;

/**
 * 用户的业务接口层
 */
public interface UserService {
    /**
     *用户注册
     * @param user
     */
    void regist(User user) throws Exception;

    void active(String code) throws Exception;

    User findByUsernameAndPassword(String username, String password) throws Exception;
}
