package com.bite.web.servlet;

import com.bite.domain.Category;
import com.bite.domain.Product;
import com.bite.service.CategoryService;
import com.bite.service.ProductService;
import com.bite.service.impl.CategoryServiceImpl;
import com.bite.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 和首页相关的servlet
 */
@WebServlet("/index")
public class IndexServlet extends BaseServlet {
   /* //声明业务接口对象
    private CategoryService categoryService = new CategoryServiceImpl() ;*/
   //声明productService接口对象
    private ProductService productService = new ProductServiceImpl() ;

        public String index(HttpServletRequest request, HttpServletResponse response)
                throws Exception {

           /* //加载index.jsp首页信息,将数据库中分类信息表查询出来
            //1)完成分类的获取
            List<Category> cList = categoryService.findAll() ;

            //2)将cList集合对象存储到request域中
            request.setAttribute("cList",cList);*/

            //进入到首页之后,加载最新商品,热门商品相关的列表
            //调用ProductService查询最新商品和热门商品
            // TODO
           List<Product> nList =  productService.findNew() ;
           List<Product> hList =  productService.findHot() ;

           //将nList/hList存储到request域对象中
            request.setAttribute("nList",nList);
            request.setAttribute("hList",hList);

            return "/jsp/index.jsp" ;

    }
}
