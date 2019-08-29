package com.bite.dao.impl;

import com.bite.dao.UserDao;
import com.bite.domain.User;
import com.bite.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * dao的实现层
 */
public class UserDaoImpl implements UserDao {
    //创建QueryRunner对象
    private  QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource()) ;
    /**
     * 注册用户
     * @param user
     */
    @Override
    public void add(User user) throws Exception {

        /**
         *
         *  `uid` VARCHAR(32) NOT NULL,
         *   `username` VARCHAR(20) DEFAULT NULL,
         *   `password` VARCHAR(50) DEFAULT NULL,
         *   `name` VARCHAR(20) DEFAULT NULL,
         *   `email` VARCHAR(30) DEFAULT NULL,
         *   `telephone` VARCHAR(20) DEFAULT NULL,
         *   `birthday` DATE DEFAULT NULL,
         *   `sex` VARCHAR(10) DEFAULT NULL,
         *   `state` INT(11) DEFAULT NULL,
         *   `code` VARCHAR(64) DEFAULT NULL,
         */
        //准备Sql
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?) ";
        //执行更新
        qr.update(sql,user.getUid(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                user.getBirthday(),
                user.getSex(),
                user.getState(),
                user.getCode());

    }

    /**
     * 通过激活码查询用户
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public User findUserByCode(String code) throws Exception {
        //准备sql
        String sql = "select * from user where code = ? limit 1" ;
        return qr.query(sql,new BeanHandler<User>(User.class),code);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void updateUser(User user) throws Exception {
        String sql = "update user set username =? ,password = ?, name = ? ,email = ?, birthday=?,sex=?,state=?,code=? where uid = ?"   ;
        qr.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getSex(),
                user.getState(),
                null,user.getUid());
    }


    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        //准备sql
        String sql = "select * from user where username = ? and password=?" ;
        return qr.query(sql,new BeanHandler<User>(User.class),username,password) ;
    }
}
