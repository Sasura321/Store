<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;

}

	#regist_span{
		color: red;
		font-size: 20px;
		text-align: center;
	}
 </style>
	<%--注册的表单校验
	--%>
	<script>

		/**
		 * 定义规则:
		 * 用户名:8-20位的单词字符 [a-zA-Z0-9]  (w)
		 * 密码:8-20位的单词字符 [a-zA-Z0-9]  (w)
		 * 邮箱:符合邮件格式(919081924@qq.com/@163.com)
		 *真实姓名:非空
		 * 性别:非空
		 * 出生日期:非空
		 * 验证码(注册的时候后台的校验)
		 */

		//校验用户名的方法
		function checkUsername(){
			//测试
			/*alert("校验用户名") ;
			return true ;*/
			// 用户名:8-20位的单词字符 [a-zA-Z0-9]  (w)
			//获取用户名的内容
			var username = $("#username").val() ; //document.getElementById("username").value;

			//规则
			var reg_username = /^\w{8,20}$/ ;

			//校验
			var flag = reg_username.test(username) ;
			//判断标记
			if(flag){
				//校验成功
				$("#username").css("border","2px solid green") ;
			}else{
				//校验失败
				$("#username").css("border","2px solid red") ;
			}
			return flag ;

		}

		//校验密码的方法
		function checkPassword(){

			// 用户名:8-20位的单词字符 [a-zA-Z0-9]  (w)
			//获取用户名的内容
			var password = $("#inputPassword3").val() ; //document.getElementById("username").value;

			//规则
			var reg_password = /^\w{8,20}$/ ;

			//校验
			var flag = reg_password.test(password) ;
			//判断标记
			if(flag){
				//校验成功
				$("#inputPassword3").css("border","2px solid green") ;
			}else{
				//校验失败
				$("#inputPassword3").css("border","2px solid red") ;
			}
			return flag ;

		}

		//邮箱的校验
		function checkEmail(){
			var email = $("#inputEmail3").val() ; //document.getElementById("username").value;

			//规则
			//邮箱:符合邮件格式(919081924@qq.com/@163.com)
			var reg_email = /^\w+@\w+\.\w+$/ ;

			//校验
			var flag = reg_email.test(email) ;
			//判断标记
			if(flag){
				//校验成功
				$("#inputEmail3").css("border","2px solid green") ;
			}else{
				//校验失败
				$("#inputEmail3").css("border","2px solid red") ;
			}
			return flag ;
		}
		//页面载入事件     原生js body 给onload="init()"

		$(function () {

			//注册的时候,要将当前的所有的校验方法都执行
			$("#registForm").submit(function () {

				//如果当前表单中所有的表单项都校验成功,才能注册
				return checkUsername() && checkPassword()&& checkEmail();
			}) ;


			//某个表单项失去焦点,校验
			$("#username").blur(checkUsername) ;
			$("#inputPassword3").blur(checkPassword) ;
			$("#inputEmail3").blur(checkEmail) ;
		}) ;



	</script>
</head>
<body>




<jsp:include page="/jsp/head.jsp"></jsp:include>




<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	
	


	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>会员注册</font>USER REGISTER<br>
		<span id="regist_span">${msg}</span>
		<form class="form-horizontal"  id="registForm" style="margin-top:5px;" action="${pageContext.request.contextPath}/user?method=regist" method="post">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="username"  name="username" placeholder="请输入用户名">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="请输入密码">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" name="email" id="inputEmail3" placeholder="Email">
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control"  name="name" id="usercaption" placeholder="请输入姓名">
			    </div>
			  </div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio"  id="inlineRadio1"  name="sex" value="男" checked> 男
			</label>
			<label class="radio-inline">
			  <input type="radio"  id="inlineRadio2" name="sex" value="女"> 女
			</label>
			</div>
			  </div>		
			  <div class="form-group">
			    <%--@declare id="date"--%><label for="date"   class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="date" class="form-control" name="birthday"  >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" class="form-control" name="check"  >
			      
			    </div>
			    <div class="col-sm-2">
			    <img src="checkCode"  onclick="changeCodeImg(this)"  />
					<%--图片的点击事件--%>
					<script type="text/javascript">
						function changeCodeImg(img) {
								//更改img对象的src属性
							img.src="checkCode?"+new Date().getTime();
						}
					</script>

			    </div>
			    
			  </div>
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="注册" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>

	  
	
	<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2009-202 品优商城 版权所有
		</div>

</body></html>




