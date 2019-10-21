<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</div>
<div class="SW_footer">
	<div class="ft layout clearfix hidden-xs">
		<div class="foot-nav clearfix">
			<dl class="first">
				<dt class="fc-gray">www.坑你.com</dt>
				<dd><a href="#" target="_blank">关于我们</a></dd>
				<dd><a href="#" tppabs="${root}/resources/page/lianxi" target="_blank">联系我们</a></dd>
			</dl>
			<%--<dl>--%>
				<%--<dt class="fc-gray">帮助</dt>--%>
				<%--<dd><a href="" tppabs="${root}/resources/help" target="_blank">帮助中心</a></dd>--%>
			<%--</dl>--%>
			<dl>
				<dt class="fc-gray">友情链接</dt>
				<dd><a href="#" target="_blank">站点1</a></dd>
				<dd><a href="#" target="_blank">站点2</a></dd>
				<dd><a href="#" target="_blank">站点3</a></dd>
				<dd><a href="#" target="_blank">站点4</a></dd>
			</dl>
			<dl class="short">
				<dt class="fc-gray">官方</dt>
					<dd class="wx"><a>微信号</a>
					<div class="wxshow"><img src="${root}/resources/static/css/fox/img/QR.png" tppabs="${root}/resources/static/css/fox/img/QR.png"><span>◆</span></div>
				</dd>
			</dl>
		</div>
		<div class="foot-copyright">
			<a href="${root}/resources/index.htm" tppabs="${root}/resources/" target="_self">
				<%--<img src="" tppabs="" width="90" height="40">--%>
			</a>
			<p>Copyright © 2010-2019 <a href="${root}/resources/index.htm" tppabs="${root}/resources/" title="" target="blank"博客></a> 保留所有权利.<span class="sp"></span>
		</div>
	</div>
	<div class="clearfix hidden-xs hidden-sm" id="trans_news">
		<ul id="contain">
			<li>拒绝电疗，用爱发电</li>
		</ul>
	</div>
</div>
<a href="javascript:;" id="gotoTop" class="show hidden-xs"><i></i></a>
<div id="aw-ajax-box" class="aw-ajax-box hidden-xs"></div>
<script type="text/javascript" language="javascript"> var Default_isFT = 0 </script>
<SCRIPT language=Javascript src="${root}/resources/static/js/fox_language.js" tppabs="${root}/resources/static/js/fox_language.js"></SCRIPT>
<div style="display:none;" id="__crond">
	<script type="text/javascript">
		//无限滚动翻页
		jQuery.ias({
			history: false,
			container: '.content',
			item: '.aw-item',
			pagination: '.pagination',
			next: '.next-page a',
			trigger: '加载更多',
			loader: '<div class="pagination-loading" style="text-align: center"><img src="${root}/resources/img/loading0.gif"/></div>',
			triggerPageThreshold: 3,
			loadOnScroll:true
		});
	</script>
</div>