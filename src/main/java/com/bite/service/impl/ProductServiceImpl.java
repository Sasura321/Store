package com.bite.service.impl;

import com.bite.dao.ProductDao;
import com.bite.dao.impl.ProductDaoImpl;
import com.bite.domain.PageBean;
import com.bite.domain.Product;
import com.bite.service.ProductService;

import java.util.List;

/**
 * 商品业务接口是现场
 */
public class ProductServiceImpl implements ProductService {
    //声明dao层对象
    private ProductDao productDao = new ProductDaoImpl() ;
    @Override
    public List<Product> findNew() throws Exception {
        return productDao.findNew();
    }

    @Override
    public List<Product> findHot() throws Exception {
        return  productDao.findHot();
    }

    /**
     * 根据商品编号查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public Product getById(String pid) throws Exception {
        return productDao.findProByPid(pid);
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
    public PageBean<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception {

        //查询总记录数:通过分类id
        int totalCount = productDao.getTotalCount(cid) ;
        //通过分页查询:列表页面中的集合数据list
        List<Product> list  = productDao.findByPage(cid,currentPage,pageSize) ;

        return new PageBean<Product>(totalCount,currentPage,pageSize,list);
    }
}
