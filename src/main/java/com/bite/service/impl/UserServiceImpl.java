package com.bite.service.impl;

import com.bite.dao.UserDao;
import com.bite.dao.impl.UserDaoImpl;
import com.bite.domain.User;
import com.bite.service.UserService;
import com.bite.utils.MailUtils;
import com.bite.utils.MailUtils_2;

/**
 * 用户的具体的业务实现层
 */
public class UserServiceImpl implements UserService {
    //调用dao
    private  UserDao userDao = new UserDaoImpl() ;
    /**
     * 用户注册
     * @param user
     */
    @Override
    public void regist(User user) throws Exception {

        userDao.add(user);

        //邮件激活
        //定义邮件的内容
        String emailMsg =
                "您的邮箱尚未激活,<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>点击激活</a>" ;
         //发送邮件
//        MailUtils.sendMail(user.getEmail(),emailMsg);
        MailUtils_2.sendMail(user.getEmail(),emailMsg,"用户邮件激活") ;
    }


    /**
     * 用户激活
     * @param code
     */
    @Override
    public void active(String code) throws Exception {
        //1)通过激活码查询用户
       User user =  userDao.findUserByCode(code) ;
        //如果该用户存在,
        if(user!=null){

            //2)将用户的激活状态进行更新
            user.setState(1);
            userDao.updateUser(user) ;
        }

    }

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        return userDao.findByUsernameAndPassword(username,password) ;
    }

}
