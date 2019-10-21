<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312"%>
<!DOCTYPE html>
<html lang="zh">
<jsp:include page="../inc/header_inc.jsp"/>
<body>
<link rel="stylesheet" type="text/css" href="${root}/resources/static/css/fox/musicbox/music.css" tppabs="${root}/resources/static/css/fox/musicbox/music.css" />
<div class="aw-container-wrap">
    <div class="container">
<div id="player" class="section"><span class="rc-tl round"></span>
<span class="rc-tr round"></span>
<span class="rc-bl round"></span>
<span class="rc-br round"></span> 
     <div class="tab pagetab" pagecount="1" pageunit="6" page="0">
       <b page="-1" class="button prev inactive">上一页</b>  
      <b page="1" class="button next inactive">下一页</b>
         <a pid="suixinting_fm" hidefocus="hidefocus" href="http://fm.baidu.com/?embed=www.medz.cn" tppabs="http://fm.baidu.com/?embed=www.medz.cn" target="player-frame" page="0" title="百度随心听 " class="item" style="">
             <img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/suixintingicon.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/suixintingicon.png" />
             <span class="title">百度随心听 </span>
         </a>
      <a pid="kuwo_mb" hidefocus="hidefocus" href="http://player.kuwo.cn/webmusic/web/play" tppabs="http://player.kuwo.cn/webmusic/web/play" target="player-frame" page="0" title="酷我音乐 " class="item" style="">
<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/kuwomb.png" tppabs="${root}/  resources/static/css/fox/musicbox/logo/kuwomb.png">
<span class="title">酷我音乐 </span>
</a>
<a pid="kugou_mb" hidefocus="hidefocus" href="http://web.kugou.com/hao123.html" tppabs="http://web.kugou.com/hao123.html" target="player-frame" page="0" title="酷狗音乐 " class="item" style="">
<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/newkugou-mb.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/newkugou-mb.png"/>
<span class="title">酷狗音乐 </span>
</a>
 <a pid="douban_fm" hidefocus="hidefocus" href="http://douban.fm/partner/playerhao123" tppabs="http://douban.fm/partner/playerhao123" target="player-frame" page="0" title="豆瓣FM " class="item" style="">
     <img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/douban.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/douban.png"/>
     <span class="title">豆瓣FM </span>
 </a>
 <a pid="1ting_mb" hidefocus="hidefocus" href="http://www.1ting.com/api/hao123/" tppabs="http://www.1ting.com/api/hao123/" target="player-frame" page="0" title="一听音乐 " class="item">
     <img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/1ting.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/1ting.png" />
     <span class="title">一听音乐 </span>
 </a>
 <a pid="qq_mb" hidefocus="hidefocus" href="http://y.qq.com/webplayer/index.html" tppabs="http://y.qq.com/webplayer/index.html" target="player-frame" page="0" title="QQ音乐 " class="item">
     <img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/qq1.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/qq1.png" />
     <span class="title">QQ音乐 </span>
 </a>
<%--<a pid="yinyuetai_fm" hidefocus="hidefocus" href="http://www.yinyuetai.com/baidu" tppabs="http://www.yinyuetai.com/baidu" target="player-frame" page="0" title="音悦TV " class="item" style="">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/green-64.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/green-64.png"/>--%>
<%--<span class="title">音悦TV </span>--%>
<%--</a>--%>
<%--<a pid="xiami_mb" hidefocus="hidefocus" href="https://www.xiami.com/billboard" tppabs="https://www.xiami.com/billboard" target="player-frame" page="0" title="虾米音乐 " class="item" style="">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/xiami.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/xiami.png" />--%>
<%--<span class="title">虾米音乐 </span>--%>
<%--</a>--%>

<%--<a pid="beva_fm" hidefocus="hidefocus" href="http://app.beva.com/hao123/fm/index.html" tppabs="http://app.beva.com/hao123/fm/index.html" target="player-frame" page="0" title="贝瓦电台 " class="item" style="">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/beva.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/beva.png" />--%>
<%--<span class="title">贝瓦电台 </span>--%>
<%--</a>--%>

<a pid="duomi_mb" hidefocus="hidefocus" href="http://app.duomiyy.com/webradio/hao123/" tppabs="http://app.duomiyy.com/webradio/hao123/" target="player-frame" page="1" title="多米音乐 " class="item" style="">
<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/duomi.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/duomi.png" />
<span class="title">多米音乐 </span>
</a>

<%--<a pid="kugou_fm" hidefocus="hidefocus" href="http://topic.kugou.com/radio/" tppabs="http://topic.kugou.com/radio/" target="player-frame" page="1" title="酷狗电台 " class="item" style="display:none;">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/kugou-fm.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/kugou-fm.png"/>--%>
<%--<span class="title">酷狗电台 </span>--%>
<%--</a> --%>

<%--<a pid="xiami_fm" hidefocus="hidefocus" href="http://fm.qq.com/" tppabs="http://fm.qq.com/" target="player-frame" page="1" title="虾米电台 " class="item" style="display:none;">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/qqfm.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/qqfm.png" />--%>
<%--<span class="title">QQ FM </span>--%>
<%--</a>--%>
<%--<a pid="kuwo_fm" hidefocus="hidefocus" href="http://player.kuwo.cn/webmusic/kuwodt/diantai123.html" tppabs="http://player.kuwo.cn/webmusic/kuwodt/diantai123.html" target="player-frame" page="1" title="酷我电台 " class="item" style="display:none;">--%>
<%--<img class="logo" src="${root}/resources/static/css/fox/musicbox/logo/kuwoIcon.png" tppabs="${root}/resources/static/css/fox/musicbox/logo/kuwoIcon.png" />--%>
<%--<span class="title">酷我电台 </span>--%>
<%--</a>--%>
 
   </div>    
  <div class="wrapper" style="display:none;">  
    <iframe id="player-frame" name="player-frame" src="about:blank" frameborder="0" scrolling="no"></iframe>
  </div>  
  </div>
  </div>  
  </div>
 <script src="${root}/resources/static/css/fox/musicbox/music.js" tppabs="${root}/resources/static/css/fox/musicbox/music.js" ></script>
<jsp:include page="../inc/footer_inc.jsp"/>
<a href="javascript:;" id="gotoTop" class="show hidden-xs"><i></i></a>
<div id="aw-ajax-box" class="aw-ajax-box hidden-xs"></div>
<script type="text/javascript" language="javascript"> var Default_isFT = 0 </script>
<SCRIPT language=Javascript src="${root}/resources/static/js/fox_language.js" tppabs="${root}/resources/static/js/fox_language.js"></SCRIPT>
</body></html>