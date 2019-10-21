<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${root }/resources/scripts/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${root }/resources/scripts/yanzheng.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		onlyInteger($("[name='phone']:eq(0)"))
		onlyInteger($("[name='telephone']:eq(0)"))
		onlyInteger($("[name='postcode']:eq(0)"))
	})
	function check(){
		if (checkInput()==false)return false;
		if (checkPassword()==false)return false;
		if (isEmail($("[name='email']:eq(0)"),"请输入正确的邮箱") == false){
			
			$($("[name='email']:eq(0)")).focus();
			return false;
		}
		return true;
	}
	</script>
  </head>
  <body>
  	  	<form action="updateUserDo" onsubmit="return check()" method="post">
  	  			<input type="hidden" name="id" value="${id }">
  		用户名：<input type="text" name="username" value="${user.username}" readonly="readonly" style="background-color: gray;"/>(不能改)<br/>
  		密码：<input type="password" name="password" value="${user.password}"/><br/>
  		真实姓名：<input type="text" name="name" value="${user.name}"/><br/>
  		<c:choose>
  		<c:when test="${user.sex =='0' }">
  		性别：女<input type="radio"" name="sex" value="0" checked="checked"/>
  			 男 <input type="radio"" name="sex" value="1"/><br/>
  		</c:when>
  		<c:otherwise>
  		性别：女<input type="radio"" name="sex" value="0" />
  			 男 <input type="radio"" name="sex" value="1" checked="checked"/><br/>
  			 </c:otherwise>
  		</c:choose>
  		职责：<input type="text" name="duty" value="${user.duty}"/><br/>
  		电话：<input type="text" name="phone" value="${user.phone}" /><br/>
  		邮箱：<input type="text" name="email" value="${user.email}"/><br/>
  		邮编：<input type="text" name="postcode" value="${user.postcode}"/><br/>
  		传真：<input type="text" name="fax" value="${user.fax}"/><br/>
  		<input type="reset" value="重置"/>
  		<input type="submit" value="提交"/>
  		</form>
  </body>
</html>
