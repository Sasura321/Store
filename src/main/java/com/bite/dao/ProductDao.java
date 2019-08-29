package com.bite.dao;

import com.bite.domain.Product;

import java.util.List;

/**
 * 商品的dao接口层
 */
public interface ProductDao {
    List<Product> findNew() throws Exception;

    List<Product> findHot() throws Exception;

    Product findProByPid(String pid) throws Exception;

    int getTotalCount(String cid) throws Exception;

    List<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception;
}
