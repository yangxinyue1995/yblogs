<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<jsp:include page="inc/header_inc.jsp"/>
<div class="aw-container-wrap">
    <div class="page-body clearfix">
        <div class="aw-main-content aw-article-bj" style="overflow:hidden;">
            <div class="ad-all clearfix">
                <div id="carousel-example" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example" data-slide-to="0" class=""></li>
                        <li data-target="#carousel-example" data-slide-to="1" class=""></li>
                        <li data-target="#carousel-example" data-slide-to="2" class="active"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item">
                            <a href="#" tppabs="${root}/resources/" target="_blank"><img alt="" src="${root}/resources/img/1.jpg" tppabs="${root}/resources/img/1.jpg"></a>
                        </div>
                        <div class="item">
                            <a href="#" tppabs="${root}/resources/" target="_blank"><img alt="" src="${root}/resources/img/2.jpg" tppabs="${root}/resources/img/2.jpg"></a>
                        </div>
                        <div class="item active">
                            <a href="#" tppabs="${root}/resources/" target="_blank"><img alt="" src="${root}/resources/img/3.jpg" tppabs="${root}/resources/img/3.jpg"></a>
                        </div>
                    </div>
                    <a class="left carousel-control" href="#carousel-example" data-slide="prev">
                        <span class="icon icon-left" style="font-size:18px;"></span></a>
                    <a class="right carousel-control" href="#carousel-example" data-slide="next">
                        <span class="icon icon-right" style="font-size:18px;"></span></a>
                </div>
                <script>
                    $(function(){
                        $('.carousel').carousel();
                    });
                </script>

            </div>
        </div>
        <c:choose>
            <c:when test="${!empty manager}">
                <div class="heder_right">
                    <div class="img_box">
                        <img src="${root}/resources/img/touxiang.jpg" tppabs="${root}/resources/img/touxiang.jpg" alt="${user.userName}"/>
                    </div>
                    <p style="color: #444444" id="us">${manager.userName}</p>
                    <span>${manager.userSign}</span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="heder_right" id="aw-site-announce">
                    <div class="mod-head">
                        <h3>
                            <a class="pull-right" href="javascript:;" onclick="$('#aw-site-announce').fadeOut(); $.cookie('announce_close', ANNOUNCE_CLOSE, { expires: 30 });"><i class="icon icon-delete text-color-999"></i></a>
                            公告				</h3>
                    </div>
                    <div class="mod-body">
                        我变秃了，也变强了.
                        <br /><br />
                        按 Ctrl+D 键，<br>
                        把本站加入收藏夹
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

            <div class="aw-main-content" style="margin-top:10px">
                <div class="aw-mod">
                    <div class="mod-body" id="s">
                        <div class="aw-common-list">
                                <div class="content">
                                    <c:forEach items="${bodyList}" var="list">
                                        <div class="aw-item" id="aw-item_0">
                                            <div class="aw-user-imgs">
                                                <a class="aw-user-name aw-user-name-o hidden-xs" data-id="2" href="" tppabs="">
                                                    <img src="${root}/resources/img/touxiang.jpg" tppabs="${root}/resources/img/touxiang.jpg" alt="管理员"/>
                                                    <p>管理员</p>
                                                </a>
                                                <div class="userline hidden-xs" id="userline_0"></div>
                                            </div>
                                            <div class="aw-question-content" id="aw-question-content_0" style="overflow: auto">
                                                <h4>
                                                    <a class="listlink" href="${root}/blogs/getListById?id=${list.id}" tppabs="" title="${list.title}">${list.title}</a>
                                                    <span class="spantopics">
                                                <%--<a class="aw-question-topics" href="#" tppabs="#" style="font-size: 10px">--%>
                                                    <%--<c:if test="${!empty list.tags}">--%>
                                                        <%--${list.tags}--%>
                                                    <%--</c:if>--%>
                                                    <%--<c:if test="${empty list.tags}">--%>
                                                        <%--文章--%>
                                                    <%--</c:if>--%>
                                                <%--</a>--%>
                                            </span>
                                                </h4>
                                                <div class="inner clearfix">
                                                    <div class="inners clearfix">
                                                        <div class="row">
                                                            <div style="height: 100px;overflow: hidden">
                                                                <a href="${root}/blogs/getListById?id=${list.id}" tppabs="${root}/blogs/getListById?id=${list.id}" target="" title="${list.title}">
                                                                ${list.content}
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="awfo-box">
                                                        <%--<c:if test="${!empty list.thirdName}">--%>
                                                            <%--<a class="aw-question-tags" href="#" tppabs="#">${list.thirdName}</a>--%>
                                                        <%--</c:if>--%>
                                                        <a class="aw-question-topics" href="#" tppabs="#" style="font-size: 12px">
                                                            <c:if test="${!empty list.tags}">
                                                                ${list.tags}
                                                            </c:if>
                                                            <c:if test="${empty list.tags}">
                                                                文章
                                                            </c:if>
                                                        </a>
                                                        <a class="aw-question-tags" href="#" tppabs="#">${list.type}</a>
                                                        <a href="#" tppabs="" class="aw-user-name">${list.author}</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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

        <!-- 侧边栏 -->
        <div class="aw-side-bar">
            <div class="aw-side-bar hidden-xs hidden-sm">
                <div class="aw-bgm" id="redian">
                    <div class="mod-head">
                        <h2 class="h-title">
                            热点话题
                        </h2>
                    </div>
                    <div class="topics-list">
                        <c:forEach items="${weighList}" var="wlist">
                            <a href="${root}/blogs/getListById?id=${wlist.id}">${wlist.title}</a>
                        </c:forEach>
                    </div>
                </div>


                <div class="aw-bgm aw-text-align-justify" id="saybox">
                    <div class="mod-head">
                        <h3 class="h-title">
                            最近更新    </h3>
                    </div>
                    <div class="mod-body" id="quotation">
                        <ul>
                            <c:forEach items="${createList}" var="list">
                                <li> <span class="side-comment-time">${list.creatime}</span>
                                    <a class="dark aw-user-name" data-id="16" href="" tppabs="${root}/resources/img/touxiang.jpg">${list.author}</a>
                                    :
                                    <div class="side-comment"><a class="dark" href="${root}/blogs/getListById?id=${list.id}" tppabs="${root}/blogs/getListById?id=${list.id}">${list.title}</a></div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="cover"></div>

                <div class="aw-bgm">
                    <div class="mod-head">
                        <h2 class="h-title">技术标签</h2>
                    </div>
                    <div class="b">
                        <div class="tags-list" style="padding: 10px;">
                            <form class="clearfix" action="${root}/blogs/search/" id="global_search_form" method="post" target="_blank">
                                <input type="hidden" class="input-text all-top-search-input" name="q" placeholder="搜索" id="aw-search-query">
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">JAVA</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Python</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Vue.js</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Linux</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">JavaScript</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">前端</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">后端</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">微服务</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">C，C#，C++</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">SpringBoot</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Redis</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">数据库</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">爬虫</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Node.js</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Go</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">Android</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">微信小程序</a>
                                <a class="fc-blue" href="#" target="#" onclick="getSearch(this)">MySQL</a>
                            </form>
                        </div>
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
                        // var rollSet = $('#gonggao');// 检查对象，#sidebar-tab是要随滚动条固定的ID，可根据需要更改
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
                    function getSearch(f){
                        $("#aw-search-query").val(f.innerText);
                        $('#global_search_form').submit()
                    }
                </script>
                <div class="cover"></div>
            </div>
        </div>
        <!-- end 侧边栏 -->
    </div>
</div>
<jsp:include page="inc/footer_inc.jsp"/>
</body>
</html>