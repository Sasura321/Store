package com.bite.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类实体信息
 */
@Data
public class Category implements Serializable {

    //分类id
    private String cid ;
    //分类名称
    private String cname ;

}
