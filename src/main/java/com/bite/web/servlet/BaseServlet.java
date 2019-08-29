package com.bite.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 实现页面统一编码
 * Auther： zsm
 * Date： 2019/8/28 16:23
 * Description：BaseServlet，让一个Servlet可以同时处理多个请求
 */
public class BaseServlet extends HttpServlet {

    /**
     * 重写HttpServlet中的service方法
     * 将protected--->public
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //反射的方式
        try {
            //1.获取子类
            Class clazz = this.getClass();
            System.out.println(this);
            //接收url的中method
            // //${pageContext.request.contextPath}/user?method=registUI

            //2.获取方法
            //method--->BaseServlet中子类的方法名
            String method = request.getParameter("method");
            System.out.println(method);

            //为了保证代码的健壮性
            //http://localhost:8080/store    --->index.jsp
            if(method==null){
                method ="index" ;
            }

            //3.调用方法
            Method m = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //当前子类中的返回值String类型,页面跳转的路径
            String  s = (String) m.invoke(this, request, response);
            //如果s==null
            if(s!=null){
                //请求转发
                request.getRequestDispatcher(s).forward(request,response);
            }

        } catch (Exception e) {
            // e.printStackTrace(); 假设如果上面代码出现异常
            //抛出具体的异常 500/404
            throw new RuntimeException() ;
        }
    }

    public String index(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return null;
    }

}
