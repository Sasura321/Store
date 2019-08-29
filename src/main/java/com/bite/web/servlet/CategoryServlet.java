package com.bite.web.servlet;

import com.bite.domain.Category;
import com.bite.service.CategoryService;
import com.bite.service.impl.CategoryServiceImpl;
import com.bite.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
    //声明业务接口对象
    private CategoryService categoryService = new CategoryServiceImpl() ;
    /**
     * 分类展示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public  void findAll(HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        //加载index.jsp首页信息,将数据库中分类信息表查询出来
        //1)完成分类的获取
        List<Category> cList = categoryService.findAll() ;

        //2)将cList集合对象存储到request域中
//        request.setAttribute("cList",cList);

        //2)将cList对象响应台前台head.jsp
        //响应格式
       // response.setContentType("application/json;charset=utf-8");
        //使用Json解析器解析集合数据
        //JsonArray.fromObject(Object value) :list/数组
        //JsonObject.fromObject(Object value):Map/JavaBean
        String json = JsonUtil.list2json(cList);

        //设置服务器的响应的中文乱码
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);

    }


}
