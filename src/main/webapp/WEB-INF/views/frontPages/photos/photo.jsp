<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312"%>
<!DOCTYPE html>
<html lang="zh">
<jsp:include page="../inc/header_inc.jsp"/>
<link rel='stylesheet' href="${root}/resources/static/css/fox/pics/style.css" tppabs="${root}/resources/static/css/fox/pics/style.css" media='screen' />
<script src="${root}/resources/static/css/fox/pics/jquery.lazyload.min.js" tppabs="${root}/resources/static/css/fox/pics/jquery.lazyload.min.js" type="text/javascript"></script>
<script src="${root}/resources/static/css/fox/pics/blocksit.min.js" tppabs="${root}/resources/static/css/fox/pics/blocksit.min.js"></script>
<script src="${root}/resources/static/css/fox/pics/fresco.js" tppabs="${root}/resources/static/css/fox/pics/fresco.js"></script>
<link rel='stylesheet' href="${root}/static/css/fox/pic/fresco.css" tppabs="" media='screen' />
<div class="aw-container-wrap">
    <div class="page-body clearfix">
        <div class="container" style="padding-top:15px;">
            <div class="">
            </div>
        </div>
        <div class="aw-main-content" style="margin-top:10px">
            <div class="aw-mod">
                <div class="mod-body" id="s">
                    <div id="wrapper">
                        <div class="content">
                            <c:forEach items="${photos}" var="list">
                                <div class="grid aw-item">
                                    <div class="gridimgbox">
                                        <a href="" tppabs="" target="_blank">
                                            <div class="imgholder"><img class="lazy" src="${root}/photos/showImg?pathName=${list.imgUrl}" tppabs="" alt="${list.imgName}" title="${list.imgName}"/></div>
                                        </a>
                                    </div>
                                </div>
                                <br/>
                            </c:forEach>
                            <nav class="pagination" style="display: none">
                                <ul>
                                    <li class="prev-page"></li>
                                    <li class="next-page"><a href="${root}/photos/getImages?page=${nextPage}" tppabs="${root}/blogs/getPage?page=${nextPage}">ÏÂÒ»Ò³</a></li>
                                    <li><span>¹² ${totalPage} Ò³</span></li>
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
</body>
</html>