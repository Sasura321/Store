package com.bite.dao.impl;

import com.bite.dao.ProductDao;
import com.bite.domain.Product;
import com.bite.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * 商品dao接口实现层
 */
public class ProductDaoImpl implements ProductDao {
    //声明查询对象
    private QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource()) ;

    /**
     * 查询最新商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findNew() throws Exception {
        //准备sql
        String sql = "select * from product  order by  pdate limit 9 " ;
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    /**
     * 查询热门商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findHot() throws Exception {
        String sql = "select * from product where is_hot =1 order by pdate limit 9" ;
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }


    /**
     * 根据商品编号查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public Product findProByPid(String pid) throws Exception {
        //准备sql
        String sql = "select * from product where pid = ? limit 1" ;
        return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    /**
     * 通过分类id查询总记录数
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public int getTotalCount(String cid) throws Exception {
        //准备sql
        String sql = "select count(*) from product where cid = ?" ;
        return ((Long)qr.query(sql,new ScalarHandler(),cid)).intValue();
    }

    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception {
        //准备sql
        String sql = "select * from product where cid = ? limit ?,?" ;
        return qr.query(sql,new BeanListHandler<Product>(Product.class),cid,(currentPage-1)*pageSize,pageSize);
    }
}
