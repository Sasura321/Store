package com.bite.dao;

import com.bite.domain.Category;

import java.util.List;

/**
 * dao层
 */
public interface CategoryDao {
    List<Category> findAll() throws Exception;
}
