<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
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
                <span>登 录</span>
            </h1>
        </div>
        <div class="container">
            <script type="text/javascript">
                $(function () {
                    //获取Cookie
                    function getCookie(name) {
                        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
                        if (arr = document.cookie.match(reg)) {
                            return unescape(arr[2]);
                        } else {
                            return null;
                        }
                    }
                    var url = getCookie(url);
                    console.log(url);
                })
            </script>
            <form class="contact"  action="${root }/admin/managerLoginDo" method="post" id="form">
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
                        <label for="password">
                            验证码</label>
                    </div>
                    <div class="ctrl" style="text-align: center">
                        <input type="text" style="width:150px;"   id="checkCode" name="checkCode" placeholder="请输入左侧验证码..." ><img style="padding-left:36px;" src="${root }/admin/checkCode"/>
                    </div>
                </div><br/>
            <div class="row  clearfix" style="text-align: center;width: 650px;">
                <div class="span10 offset2" style="text-align: center">
                    <input type="submit" name="submit" id="submit" class="submit"  value="登录">
                </div>
            </div>
            </form>
            <div id="validation">
            </div>
        </div>
    </section>
</body>
</html>
