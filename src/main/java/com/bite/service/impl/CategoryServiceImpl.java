package com.bite.service.impl;

import com.bite.dao.CategoryDao;
import com.bite.dao.impl.CategoryDaoImpl;
import com.bite.domain.Category;
import com.bite.service.CategoryService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.util.List;

/**
 * 分类信息的业务接口实现层
 */
public class CategoryServiceImpl implements CategoryService {

    //声明dao接口层对象
    private CategoryDao categoryDao = new CategoryDaoImpl() ;
    @Override
    public List<Category> findAll() throws Exception {
        // 1)直接从数据库中查
        // return categoryDao.findAll() ;

        /**
         * 缓存的逻辑
         * 1)从缓存中获取数据(集合数据)
         * 2)判断集合中是否在数据,
         * 如果不存在,
         *       就直接读取数据库的信息,
         *      将当前读取的数据给缓存中添加
         *
         * 如果存在:
         *      直接从缓存中获取数据
         */
        //CacheManager:缓存处理器,需要通过缓存文件来创建该对象
        CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().
                getResourceAsStream("ehcache.xml")) ;

        //通过缓存文件中的标识名称获取缓存对象:Cache
        Cache cache = cacheManager.getCache("categoryCache");

        //从缓存对象获取存储的Element(缓存集合对象)
        Element element = cache.get("cList"); //clist(Key)


        //声明List集合对象
        List<Category> list = null ;

        //判断缓存集合对象中是否为空
        if (element ==null){
            System.out.println("当前分类信息数据从后台数据库中获取...");
            //缓存集合对象中没有数据
            //从数据库查
           list =  categoryDao.findAll() ;
           //存储到缓存
            //Element(Object key, Object value)
            cache.put(new Element("cList",list));
        } else{
            System.out.println("当前分类信息是从缓存中获取...");
            //不为空,直接从缓存中获取
            list   =(List<Category>) element.getObjectValue();
        }

        return list;
    }


    public static void main(String[] args) {
        //读取resources目录下的缓存文件:ehcache.xml
        InputStream in = CategoryServiceImpl.class.getClassLoader().
                getResourceAsStream("ehcache.xml");
        System.out.println(in);//java.io.BufferedInputStream@2c7b84de
    }
}
