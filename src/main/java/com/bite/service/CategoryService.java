package com.bite.service;

import com.bite.domain.Category;

import java.util.List;

/**
 * 分类信息的业务接口层
 */
public interface CategoryService {
    List<Category> findAll() throws  Exception;
}
