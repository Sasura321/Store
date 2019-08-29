package com.bite.service;

import com.bite.domain.PageBean;
import com.bite.domain.Product;

import java.util.List;

/**
 * 商品业务接口层
 */
public interface ProductService {
    List<Product> findNew() throws Exception;

    List<Product> findHot() throws Exception;

    Product getById(String pid) throws  Exception;

    PageBean<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception;
}
