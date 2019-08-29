<%--
  Created by IntelliJ IDEA.
  User: zhangyang
  Date: 2019/7/18
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <%--jstl:核心标签库
            empty 可以判断对象/集合是否为空
            --%>
            <c:if test="${empty user}">
                <li><a href="${pageContext.request.contextPath}/user?method=loginUI">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/user?method=registUI">注册</a></li>
            </c:if>
            <c:if test="${not empty user}">
                欢迎回来,${user.name}
                <li><a href="${pageContext.request.contextPath}/user?method=logout">退出</a></li>
                <li><a href="${pageContext.request.contextPath}/user?method=registUI">我的订单</a></li>
            </c:if>


            <li><a href="cart.htm">购物车</a></li>
        </ol>
    </div>
</div>
<!--
时间：2015-12-30
描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul  id="menuId" class="nav navbar-nav">
                    <%--
                        jstl:c:foreach  从域中的属性名称--获取对应的数据
                        items:从域中获取的属性
                    --%>
                   <%-- <c:forEach items="${cList}" var="p">
                        <li><a href="#">${p.cname}电脑办公</a></li>
                    </c:forEach>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>

<%--
    加载首页的时候,动态包含了head.jsp,异步请求服务器,发送ajax
--%>
<script>
    //页面载入事件
    $(function () {
        //直接要发送ajax请求
        $.get("${pageContext.request.contextPath}/category?method=findAll",function (data) {
//data:[{cid:1,cname:"电脑办公"},{},{},{}],
            //测试
            // alert(data);

            //通过Jquery方式获取Ul标签对象
           var $ul =  $("#menuId") ;
           //遍历服务器响应过来的集合数据
            $(data).each(function () {
                //在ul的添加子标签li
                $ul.append($("<li><a href='${pageContext.request.contextPath}/product?method=findByPage&cid="+this.cid+"&currentPage=1'>"+this.cname+"</a></li>")) ;
            }) ;

        },"json") ;

    }) ;

</script>


