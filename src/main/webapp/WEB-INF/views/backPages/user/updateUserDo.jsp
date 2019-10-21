<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <%-- 	<%
  	 int id = ParamUtil.getInt(request,"id",0);
	 String password = ParamUtil.getString(request,"password","");
	 String name = ParamUtil.getString(request,"name","");
	 String sex = ParamUtil.getString(request,"sex","");
	 String duty = ParamUtil.getString(request,"duty","");
	 String phone = ParamUtil.getString(request,"phone","");
	 String email = ParamUtil.getString(request,"email","");
	 String postcode = ParamUtil.getString(request,"postcode","");
	 String fax = ParamUtil.getString(request,"fax","");
	 
	 User user = new User(id,null,password,name,sex,duty,phone,email,null,postcode,fax);
	 if(UserDao.updateUserById(user)){
		 response.sendRedirect("userList.jsp");
	%>
		<script type="text/javascript">
			alert("更改失败");
			window.history.go(-1);
		</script>
	<%}%> --%>
  </body>
</html>
