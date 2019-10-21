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
	<link rel="stylesheet" href="${root }/resources/css/reset.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${root }/resources/css/substyle.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${root }/resources/css/invalid.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${root }/resources/scripts/jquery-1.3.2.min.js"></script>
	<link href="${root }/resources/kkpager/kkpager.css" rel="stylesheet" media="screen" />
	<script type="text/javascript" src="${root }/resources/kkpager/kkpager.js"></script>
	<script type="text/javascript">
$(function() {
	initPage();
});
//初始化分页排序控件
function initPage() {
	var pageNo = getParameter('page');//当前页
	var name = $('#name').val();
	if (!pageNo) {
		pageNo = 1;
	}
	var totalPage = '${totalPage}';
	var totalRecords = '${totalRows}';
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pagerid : "kkpager",
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//链接前部
		hrefFormer : "selectAll",
		//链接尾部
		hrefLatter : '',
		getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?page="+  n+"&name="+name;
		}
	});
}

function getParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

</script>
  </head>
  <body>
  	  	<div id="main-content" >
  	<form action="selectAll" method="post">
  		<input type="hidden" name="isFirst" value="1"/>
  		<input type="hidden" name="page" value="1" />
  		姓名：<input type="text" name="name" value="${name }" id="name"/><input type="submit" value="查询"/>
  	</form>
  	<table border="1">
  		<tr>
  			<td>id</td>
  			<td>用户名</td>
  			<td>密码</td>
  			<td>姓名</td>
  			<td>职责</td>
  		    <td>性别</td>
  		    <td>邮箱</td>
  		    <td>电话</td>
  		    <td>邮编</td>
  		    <td>传真</td>
  		    <td>操作</td>
  		</tr>
  		<c:forEach items="${users }" var="users">
  		<tr>
  			<td>${users.id }</td>
  			<td>${users.username }</td>
  			<td>${users.password }</td>
  			<td>${users.name }</td>
  			<td>${users.duty }</td>
  			
  			<c:choose>
  			<c:when test="${managers.sex == '0' }">
  			<td>
  			女
  			</td>
  			</c:when>
  			<c:otherwise>
  			<td>
  			男
  			</td>
  			</c:otherwise>
  			</c:choose>
  			
  			<td>${users.email }</td>
  			<td>${users.phone }</td>
  			<td>${users.postcode }</td>
  			<td>${users.fax }</td>
  			<td>
  				<a href="updateUser?id=${users.id }" >更改</a>
  				<a href="userDelete?id=${users.id }" >删除</a>  			
  			</td>
  		</tr>
  		</c:forEach>
  	</table>
  	 <div id="kkpager" style="width: 700px; float: right; margin-right: 30px;" align="left"></div>	
  	</div>
  </body>
</html>
