package com.bite.web.servlet;

import com.bite.domain.PageBean;
import com.bite.domain.Product;
import com.bite.service.ProductService;
import com.bite.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品模块
 */
@WebServlet("/product")
public class ProductServlet extends BaseServlet {
    //声明ProductService接口对象
    private ProductService productService = new ProductServiceImpl() ;

    /**
     * 通过商品编号获取商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String getById(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //${pageContext.request.contextPath}/product?method=getById&pid=${p.pid}
        //1.接收参数
        String pid = request.getParameter("pid");
        //2.调用ProductService--->查询对应的Product
        Product product = productService.getById(pid) ;
        //3.将当前商品对象存储到的request域中
        request.setAttribute("bean",product);
        //4.跳转到商品详情页
        return "/jsp/product_info.jsp" ;
    }


    /**
     * 根据分类id分页查询商品数据
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String findByPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //1.接收参数,同时设置pageSize的默认值
        //类别id
        String cid = request.getParameter("cid");
        //当前页码
        int currentPage = Integer.parseInt
                (request.getParameter
                        ("currentPage"));
        //pageSize的默认值
        int pageSize = 12 ;

        //2.调用Service完成查询
       PageBean<Product> pb =  productService.findByPage(cid,currentPage,pageSize) ;

       //3.将PageBean对象存储request域中
        request.setAttribute("pb",pb);

        //4.跳转页面
        return  "/jsp/product_list.jsp" ;

    }





}
