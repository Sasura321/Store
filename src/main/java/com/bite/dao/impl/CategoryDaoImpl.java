package com.bite.dao.impl;

import com.bite.dao.CategoryDao;
import com.bite.domain.Category;
import com.bite.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 *分类 接口dao实现层
 */
public class CategoryDaoImpl implements CategoryDao {
    //声明查询对象
    private QueryRunner qr =
            new QueryRunner(DataSourceUtils.getDataSource()) ;
    @Override
    public List<Category> findAll() throws Exception {
        //准备sql
        String sql = "select * from category" ;
        //执行查询
        List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
        return list;
    }
}
