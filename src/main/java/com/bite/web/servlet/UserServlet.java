package com.bite.web.servlet;

import com.bite.constant.Constant;
import com.bite.domain.User;
import com.bite.myconverter.MyConverter;
import com.bite.service.UserService;
import com.bite.service.impl.UserServiceImpl;
import com.bite.utils.MD5Utils;
import com.bite.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 和用户相关的Servlet
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet{

    //声明业务成调用对象
   private  UserService userService = new UserServiceImpl() ;

    //doGet()
    //请求转发,重定向/向浏览器输出内容
    //测试
    public String  add(HttpServletRequest request,HttpServletResponse response)
            throws  ServletException, IOException{
        System.out.println("调用了子类的add方法....");
        return  null ;
    }

    /**
     * 跳转注册页面的功能
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  registUI(HttpServletRequest request,HttpServletResponse response)
            throws  ServletException, IOException{
        //直接访问注册页面
        return  "/jsp/register.jsp" ;
    }

    /**
     * 用户注册的功能
        //     * @param request
        //     * @param response
        //     * @return
        //     * @throws ServletException
        //     * @throws IOException
        //     */
           public String  regist(HttpServletRequest request,HttpServletResponse response)
                   throws Exception {

               //加入验证码逻辑
               //1.获取前台的页面的中的注册码的内容
               String check = request.getParameter("check");
               //2.从服务器端的session域中获取生成的验证码内容
               String sessionCode = (String)request.getSession().
                       getAttribute("sessionCode");

               //一次性验证,防止验证码被复用
               request.getSession().removeAttribute("sessionCode");

               //3.两个进行对比
               //防止空指针异常
               if(sessionCode ==null || !sessionCode.equalsIgnoreCase(check)){
                   //跳转到当前的注册页面上,提示用户
                   request.setAttribute("msg","验证码错误!!");
                   return "/jsp/register.jsp" ;
               }



               //0)获取前台页面传递的参数(username=zhangsan&password=12345678...)
               Map<String, String[]> map =request.getParameterMap();
               // 1.封装User
           //创建User对象
           User user = new User() ;

        //commons-beanUtils.jar
        /**
         * 参数1:表示当前针对那个具体对象
         * 参数2:提交表单中的所有参数的map集合
         * public static void populate(Object bean, Map properties)
         * throws IllegalAccessException, InvocationTargetException {
         *
         *     }
         */
        //封装数据之前,需要将转换器注册(commons-beanutils)
               ConvertUtils.register(new MyConverter(), Date.class);
        BeanUtils.populate(user,map);

            //1.1 设置用户id
               user.setUid(UUIDUtils.getId());
               //1.2 设置用户的激活码(唯一)
               user.setCode(UUIDUtils.getCode());

               //1.3将密码进行加密
               user.setPassword(MD5Utils.md5(user.getPassword()));

        //2.调用UserService
        //调用注册方法
        userService.regist(user) ;

        //3.请求转发到msg.jsp ,提示,注册成功,请激活用户邮箱
        request.setAttribute("msg","注册成功,请激活用户邮箱");
        return  "/jsp/msg.jsp" ;
    }


    /**
     * 用户激活功能
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  active(HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        //http://localhost:8080/store/user?method=active&code=555EAA90D4D74E79AF9D3DEF3D5960C0
        //1.获取激活码
        String code = request.getParameter("code");
        //2.判断激活码是否存在
        if(code!=null){

            //3.调用service查询
            userService.active(code) ;
            //提示用户,激活成功而
            request.setAttribute("msg","您当前已经激活,请<a href='http://localhost:8080/store/jsp/login.jsp'>登录</a>");

        }

        return "/jsp/msg.jsp" ;
    }


    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String  loginUI(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
            return "/jsp/login.jsp" ;
    }

    /**
     * 用户登录功能
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String  login(HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        //1,前台验证码和服务器生成的验证码对比(同注册的验证码逻辑)


        //2.获取前台的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //密码加密
        password = MD5Utils.md5(password) ;
        //3.调用service完成查询用户
        User user = userService.findByUsernameAndPassword(username,password) ;
        //判断用户是否存在
        if(user ==null){
            //提示
            request.setAttribute("msg","用户名或者密码匹配");
            return "/jsp/login.jsp";
         }else{
            //用户存在
            //判断用户是否被激活
            if(Constant.USER_ACTIVE_STATE!=user.getState()){
                //用户没有激活
                request.setAttribute("msg","当前用户尚未激活!!");
                return "/jsp/login.jsp";
            }

        }


        //如果用户存在,并且激活了
        //将用户存储到Session域中
        request.getSession().setAttribute("user",user);
        //重定向到首页
        response.sendRedirect(request.getContextPath()+"/");
        //http://localhost:8080/store/
        return null;
    }


    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    
    public String  logout(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        //1.从session域中将user信息手动删除  (将session对象销毁)
        request.getSession().invalidate();
        //2.重定向首页
        response.sendRedirect(request.getContextPath()+"/");
        //记住用户名(Cookie)

        return null ;

    }



}
