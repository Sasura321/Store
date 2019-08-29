package com.bite.dao;

import com.bite.domain.User;

import java.sql.SQLException;

/**
 * 接口层
 */
public interface UserDao {

    void add(User user) throws Exception;

    User findUserByCode(String code) throws Exception;

    void updateUser(User user) throws Exception;

    User findByUsernameAndPassword(String username, String password) throws Exception;
}
