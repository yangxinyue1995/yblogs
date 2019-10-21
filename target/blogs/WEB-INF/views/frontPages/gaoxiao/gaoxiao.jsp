<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../inc/header_inc.jsp"/>
<style>
    .aw-common-list .aw-item{
        padding:10px 10px 10px 60px;
    }
    ul li{
        padding: 5px;
    }
    .aw-question-content{
        left: 55px;
    }

    #imgs{
        height:auto;min-height:500px;width:800px;margin:0 auto;
    }
    #aw-item_0{
        height: auto;
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
                                    <div class="aw-item" id="aw-item_0">
                                        <div class="aw-question-content" id="aw-question-content_0">
                                            <h4>
                                                <a class="listlink" href="#" tppabs="" title="${list.imgName}">${list.imgName}</a>
                                                <span class="spantopics">
                                        </span>
                                            </h4>
                                            <div class="inner clearfix">
                                                <div class="inners clearfix">
                                                    <div class="row">
                                                        <div class="shibox shiboximg col-xs-4 col-sm-4 col-md-4" id="imgs">
                                                            <a href="#" tppabs="#" target="" title="${list.imgName}">
                                                                <c:if test="${!empty list.imgUrl}">
                                                                    <img class="img-polaroidst img-responsive" style="display: inline" src="${root}/photos/showImg?pathName=${list.imgUrl}" tppabs="#"  target="" alt="${list.imgName}"/>
                                                                </c:if>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </c:forEach>
                            <nav class="pagination" style="display: none;">
                                <ul>
                                    <li class="prev-page"></li>
                                    <li class="next-page"><a href="${root}/gaoxiao/getGaoxiao?page=${nextPage}" tppabs="${root}/gaoxiao/getGaoxiao?page=${nextPage}">下一页</a></li>
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
<jsp:include page="../inc/footer_inc.jsp"/>
</html>
