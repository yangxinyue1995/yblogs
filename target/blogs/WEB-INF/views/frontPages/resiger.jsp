<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312"%>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册</title>
    <link href="${root}/resources/login/css/demo.css" rel="stylesheet" type="text/css">
    <script src="${root}/resources/login/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <!--Framework-->
    <script src="${root}/resources/login/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${root}/resources/login/js/jquery-ui.js" type="text/javascript"></script>
    <!--End Framework-->
    <script src="${root}/resources/login/js/jquery.ffform.js" type="text/javascript"></script>
</head>
<body>
<section id="getintouch" class="flipInX animated">
    <div class="container" style="border-bottom: 0;">
        <h1>
            <span>注 册</span>
        </h1>
    </div>
    <div class="container">
        <form class="contact"  action="${root }/admin/resiger"  method="post" id="form">
            <div class="row clearfix">
                <div class="lbl">
                    <label for="username">
                        用户名</label>
                </div>
                <div class="ctrl" style="text-align: center">
                    <input type="text" id="username" name="username" data-required="true" data-validation="username"
                           data-msg="请输入用户名" placeholder="请输入用户名..." style="width: 250px">
                </div>
            </div><br/>
            <div class="row clearfix" >
                <div class="lbl">
                    <label for="password">
                        密码</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="password" name="password" data-required="true" data-validation="password"
                           data-msg="请输入6-20位的密码" placeholder="请输入密码..." style="width: 250px">
                </div>
            </div><br/>
            <div class="row clearfix" >
                <div class="lbl">
                    <label for="name">
                        姓名</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="name" name="name" data-required="true" data-validation="name"
                           data-msg="请输入姓名.." placeholder="请输入姓名（选填）..." style="width: 250px">
                </div>
            </div><br/>
            <div class="row clearfix" >
                <div class="lbl">
                    <label for="email">
                        邮箱</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="email" name="email" data-required="true" data-validation="email"
                           data-msg="请输入邮箱.." placeholder="请输入邮箱..." style="width: 250px">
                </div>
            </div><br/>
            <div class="row clearfix" >
                <div class="lbl">
                    <label for="checkCode">
                        验证码</label>
                </div>
                <div class="ctrl" style="text-align: center">
                    <input type="text" style="width:150px;"   id ="checkCode" name="checkCode" placeholder="请输入左侧验证码..." ><img style="padding-left:36px;" src="${root }/admin/checkCode"/>
                </div>
            </div><br/>
            <div class="row  clearfix" style="text-align: center;width: 650px;">
                <div class="span10 offset2" style="text-align: center">
                    <input type="submit" name="submit" id="submit" class="submit" onclick="sure()" value="注册">
                </div>
            </div>
        </form>
        <div id="validation">
        </div>
    </div>
</section>
</body>
<%--<script type="text/javascript">--%>
    <%--function sure() {--%>
        <%--var username = $("#username").val();--%>
        <%--var password = $("#password").val();--%>
        <%--var name = $("#name").val();--%>
        <%--var email = $("#email").val();--%>
        <%--var checkCode = $("#checkCode").val();--%>
        <%--$.ajax({--%>
            <%--url : "${root }/admin/resiger",--%>
            <%--type : "post",--%>
            <%--data : {--%>
                <%--username:username,--%>
                <%--password:password,--%>
                <%--name:name,--%>
                <%--email:email,--%>
                <%--checkCode:checkCode--%>
            <%--},--%>
            <%--success : function(data) {--%>
                <%--window.location.href="${root}/frontPages/login"--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
<%--</script>--%>
</html>
