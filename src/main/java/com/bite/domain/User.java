package com.bite.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * JavaBean:符合条件
 *  1)具体类
 *  2)提供私有字段 private String uid ;
 *  3)提供对外公共方法: getXXX/setXXX()  uid
 *  4)实现序列化接口
 *
 */
@Data
public class User implements Serializable {

    private String uid ;
    private String username ;
    private String password ;
    private String name ;
    private String email ;
    private String telephone ;
    private Date  birthday ;
    private String sex ;
    private Integer state = 0 ;// 当前用户的状态(0表示未激活,1表示激活状态)
    private String code ;//Code激活码(要求唯一)

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }
}
