<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 侧边栏 -->
<div class="aw-side-bar">
    <div class="aw-side-bar hidden-xs hidden-sm">
        <div class="aw-bgm">
            <div class="mod-head">
                <h2 class="h-title">
                    热点话题
                </h2>
            </div>
            <div class="topics-list">
                <c:forEach items="${weighList}" var="wlist">
                    <a href="${root}/blogs/getListById?id=${wlist.id}" target="_self">${wlist.title}</a>
                </c:forEach>
            </div>
        </div>

        <div class="aw-bgm aw-text-align-justify" id="gengxin">
            <div class="mod-head">
                <h3 class="h-title">
                   最近更新    </h3>
            </div>
            <div class="mod-body" id="quotation">
                <ul>
                    <c:forEach items="${createList}" var="list">
                        <li> <span class="side-comment-time">${list.creatime}</span>
                            <a class="dark aw-user-name" data-id="16" href="" tppabs="${root}/resources/img/touxiang.jpg" target="_self">${list.author}</a>
                            :
                            <div class="side-comment"><a class="dark" href="${root}/blogs/getListById?id=${list.id}" tppabs="${root}/blogs/getListById?id=${list.id}">${list.title}</a></div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                var scrtime;
                $("#quotation").hover(function(){
                    clearInterval(scrtime);
                },function(){
                    scrtime = setInterval(function(){
                        var obj = $('#quotation ul li').last();
                        obj.hide();
                        $('#quotation ul').prepend(obj);
                        $('#quotation ul li').first().slideDown(500);
                    },5000);
                }).trigger("mouseleave");

                // var fixfox="fixedff";
                // var rollSet = $('#gengxin');// 检查对象，#sidebar-tab是要随滚动条固定的ID，可根据需要更改
                // var offset = rollSet.offset();
                // console.log(offset)
                // $(window).scroll(function () {
                //     // 检查对象的顶部是否在游览器可见的范围内
                //     var scrollTop = $(window).scrollTop();
                //     if(offset.top < scrollTop + 0){
                //         rollSet.addClass(fixfox);
                //     }else{
                //         rollSet.removeClass(fixfox);
                //     }
                // });
            });

        </script>
	  <div class="cover"></div>
    </div>
</div>
<!-- end 侧边栏 -->