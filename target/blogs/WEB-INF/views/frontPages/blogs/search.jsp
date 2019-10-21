<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../inc/header_inc.jsp"/>
<style>
    .aw-common-list .aw-item{
        padding:10px 10px 10px 60px;
        overflow: hidden;
    }
    ul li{
        padding: 5px;
    }
    .aw-question-content{
        left: 55px;
    }
</style>
<div class="aw-container-wrap">
    <div class="page-body clearfix">
        <div class="aw-main-content" style="margin-top:10px">
            <div class="aw-mod">
                <div class="mod-body" id="s" style="width: 1000px">
                    <div class="aw-common-list">
                        <div class="content">
                            <c:forEach items="${bodyList}" var="list">
                                <c:if test="${fn:length(bodyList)>0 }">
                                    <div class="aw-item" id="aw-item_0" style="overflow: hidden">
                                        <div class="aw-user-imgs">
                                            <a class="aw-user-name aw-user-name-o hidden-xs" data-id="2" href="" tppabs="">
                                                <img src="${root}/resources/img/touxiang.jpg" tppabs="${root}/resources/img/touxiang.jpg" alt="管理员"/>
                                                <p>管理员</p>
                                            </a>
                                            <div class="userline hidden-xs" id="userline_0"></div>
                                        </div>
                                        <div class="aw-question-content" id="aw-question-content_0">
                                            <h4>
                                                <a class="listlink" href="${root}/blogs/getListById?id=${list.id}" tppabs="" title="${list.title}">${list.title}</a>
                                                <span class="spantopics">
                                            <a class="aw-question-topics" href="" tppabs="#">${list.type}</a>
                                        </span>
                                            </h4>
                                            <div class="inner clearfix">
                                                <div class="inners clearfix">
                                                    <div class="row">
                                                        <div class="shibox shiboximg col-xs-4 col-sm-4 col-md-4">
                                                            <a href="${root}/blogs/getListById?id=${list.id}" tppabs="${root}/blogs/getListById?id=${list.id}" target="_self" title="${list.title}">
                                                                    ${list.title}
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="awfo-box">
                                                    <a class="aw-question-tags" href="#" tppabs="#">${list.type}</a>
                                                    <a href="#" tppabs="#" class="aw-user-name">${list.author}</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${fn:length(bodyList)<0 }">
                                    <div class="aw-item" id="aw-item_0">
                                        什么都没有搜索到哦！
                                    </div>
                                </c:if>
                            </c:forEach>
                            <nav class="pagination" style="display: none;">
                                <ul>
                                    <li class="prev-page"></li>
                                    <li class="next-page"><a href="${root}/blogs/getPage?page=${nextPage}" tppabs="${root}/blogs/getPage?page=${nextPage}">下一页</a></li>
                                    <li><span>共 ${totalPage} 页</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //无限滚动翻页
    jQuery.ias({
        history: false,
        container: '.content',
        item: '.aw-item',
        pagination: '.pagination',
        next: '.next-page a',
        trigger: '加载更多',
        loader: '<div class="pagination-loading" style="text-align: center"><img src="${root}/../resources/img/loading0.gif"/></div>',
        triggerPageThreshold: 3,
        loadOnScroll:true
    });
</script>
<jsp:include page="../inc/footer_inc.jsp"/>
</html>
