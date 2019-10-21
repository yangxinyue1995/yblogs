<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../inc/header_inc.jsp"/>
<title>${bodyList.title}</title>
<script type="text/javascript">
    $(function () {
        $('#referrer').val(window.location.href);
    })
</script>
<div class="aw-container-wrap">
    <div class="page-body clearfix">
        <div class="aw-main-content">
            <div class="aw-content-wraps">
                <div class="aw-mod aw-question-detail aw-item">
                    <div class="mod-head">
                        <div class="biaoboxs pull-right hidden-xs">
                        </div>
                        <h1> <span class="font24">${bodyList.title}</span> </h1>
                        <div class="author"> <a class="avatar" href="#"><img src="${root}/resources/img/touxiang.jpg" alt="${bodyList.author}"></a>
                            <dd class="info"><span class="tag">作者</span> <a class="aw-user-name" href="${root}/resources/user/xiaozhushou" data-id="2">${bodyList.author}</a>
                                <div class="shuju"><i class="icon icon-home22"></i><a href="${root}">首页</a><span class="sp">›</span><a href="#" class="text">${bodyList.type}</a>
                                    <i class="icon icon-time"></i><span class="publish-time">${bodyList.creatime}</span>
                                </div>
                            </dd>
                        </div>
                    </div>

                    <div class="mod-body">
                        <link href="${root}/resources/static/css/fox/prettify.css" rel="stylesheet" type="text/css" />
                        <script src="${root}/resources/static/js/prettify.js" type="text/javascript"></script>
                        <div id="content-fox" class="content markitup-box font16 line-bigs">
                            <br />
                            ${bodyList.content}
                        </div>
                    </div>
                </div>
            </div>
            <div class="aw-mod aw-content-wraps" style="margin-top:15px">
                <div class="mod-head">
                    <ul class="nav nav-tabs aw-nav-tabs active">
                        <h2 class="hidden-xs">
                            <i class="icon icon-topic"></i>
                            ${count} 条评论
                        </h2>
                    </ul>
                </div>
                <div class="mod-body aw-feed-list">
                    <c:forEach items="${comments}" var="list">
                        <div class="aw-feed-list" id="comments_list">
                            <li style="list-style-type: none">
                                <span class="side-comment-time">${list.createDateTime}</span>
                                <span class="dark aw-user-name" data-id="16" href="" tppabs="${root}/resources/img/touxiang.jpg">
                                    <img alt="${list.userName}" src="${root}/resources/static/common/avatar-mid-img.png" />
                                    <span style="font-size: 15px;"> ${list.userName}</span>
                                </span>
                                :
                                <div class="side-comment"><span class="dark">${list.comments}</span></div>
                            </li>
                        </div>
                    </c:forEach>
                </div>
                <div class="mod-footer">
                </div>
                <!-- end 帖子详细模块 -->
                <!-- 评论编辑器 -->
                <c:if test="${empty manager}">
                    <div class="aw-mod aw-replay-box question"> <span name="answer_form"></span>
                        <p align="center">
                            游客您好，马上
                            <a href="${root}/frontPages/login/" class="fc-blue">登录
                            </a>-<a href="${root}/frontPages/resiger/"  class="fc-blue">免费注册</a>
                            &nbsp;&nbsp;&nbsp;即可评论哦
                        </p>
                    </div>
                </c:if>
                <c:if test="${!empty manager}">
                    <!-- 评论编辑器 -->
                    <div class="aw-mod aw-replay-box question"> <a name="answer_form"></a>
                        <form action="${root}/comments/saveComments/" onsubmit="return false;" method="post" id="answer_form" class="question_answer_form">
                            <input type="hidden" name="userId" value="${manager.id}" />
                            <input type="hidden" name="blogsId" value="${bodyList.id}" />
                            <input type="hidden" name="userName" value="${manager.userName}" />
                            <div class="mod-head">
                                <a href="#" class="aw-user-name">
                                    <img alt="${manager.userName}" src="${root}/resources/static/common/avatar-mid-img.png" /><span style="font-size: 15px;">${manager.userName}</span>
                                </a>
                            </div>
                            <div class="mod-body">
                                <div class="aw-mod aw-editor-box">
                                    <div class="mod-head">
                                        <div class="wmd-panel">
                                            <textarea class="wmd-input form-control autosize editor" id="wmd-input" rows="4" name="answer_content" placeholder="请自觉遵守相关的政策法规，严禁发布色情、暴力、反动的言论。"></textarea>
                                        </div>
                                    </div>
                                    <div class="mod-body clearfix">
                                        <a href="javascript:;" onclick="AWS.ajax_post($('#answer_form'), AWS.ajax_processer, 'reply');" class="btn btn-normal btn-success pull-right btn-reply">
                                            发表评论
                                        </a>
                                        <span class="pull-right text-color-999" id="answer_content_message">&nbsp;</span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- end 评论编辑器 -->
                </c:if>
                <!-- end 评论编辑器 -->
            </div>
        </div>
        <jsp:include page="../inc/right_inc.jsp"/>
    </div>
</div>
<jsp:include page="../inc/footer_inc.jsp"/>
</body>
</html>
