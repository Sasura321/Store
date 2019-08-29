package com.bite.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体类
 */
@Data
public class Product implements Serializable {

    private String pid ;             //商品编号
    private String pname ;           //商品名称
    private Double market_price ;    //市场价
    private Double shop_price ;      //商城价
    private String pimage ;          //商品图片
    private Date pdate ;             //商品创建日期
    private Integer is_hot =1;       //是否是热门商品 1：表示热门 0：非热门
    private String pdesc ;           //商品描述
    private Integer pflag =1 ;       //商品是否下架 1：表示上架 0 ：下架
    private  Category category ;

}
