var width = $(window).width();
// 解决页面加载的时候 布局错乱的问题 用layer的样式
var html = "<div class='layui-layer-shade' id='topLoadingDiv' style='z-index:19891014; background-color:#000; opacity:0.3; filter:alpha(opacity=30);'></div><div class='layui-layer layui-anim layui-layer-loading' id='topLoading' type='loading' contype='string' style='z-index: 19891029; top: 193.5px; left:'"
		+ ((width / 2) - 125)
		+ "'px;'><div class='layui-layer-content layui-layer-loading1'></div><span>加载资源中。。。</span></div>";
document.write(html);
window.onload = function() {
	var mask = document.getElementById('topLoading');
	var maskDiv = document.getElementById('topLoadingDiv');
	mask.parentNode.removeChild(mask);
	maskDiv.parentNode.removeChild(maskDiv);
};