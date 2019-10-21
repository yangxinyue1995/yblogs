//页面加载
$('body').show();

//页面加载时给img和a标签添加draggable属性
(function () {
    $('img').attr('draggable', 'false');
    $('a').attr('draggable', 'false');
})();
 
//设置Cookie
function setCookie(name, value, time) {
    var strsec = getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec * 1);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
//获取Cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}
//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}
function getsec(str) {
    var str1 = str.substring(1, str.length) * 1;
    var str2 = str.substring(0, 1);
    if (str2 == "s") {
        return str1 * 1000;
    } else if (str2 == "h") {
        return str1 * 60 * 60 * 1000;
    } else if (str2 == "d") {
        return str1 * 24 * 60 * 60 * 1000;
    }
}
 
jQuery(document).ready(function ($) {
$("img.thumb").lazyload({
    placeholder: "./images/occupying.png",
    effect: "fadeIn"
});
$(".single .content img").lazyload({
    placeholder: "./images/occupying.png",
    effect: "fadeIn"
});
});
//IE6-9禁止用户选中文本
document.body.onselectstart = document.body.ondrag = function () {
    return false;
};
 
//启用工具提示
$('[data-toggle="tooltip"]').tooltip();

//无限滚动翻页
    jQuery.ias({
    history: false,
    container: '.content',
    item: '.aw-item',
    pagination: '.pagination',
    next: '.next-page a',
    trigger: '加载更多',
    loader: '<div class="pagination-loading" style="text-align: center"><img src="resources/img/loading0.gif"/></div>',
    triggerPageThreshold: 3,
    loadOnScroll:true
});
 
//鼠标滚动超出侧边栏高度绝对定位
$(window).scroll(function () {
    var sidebar = $('#sidebar-tab');
    var sidebarHeight = sidebar.height();
    var windowScrollTop = $(window).scrollTop();
    if (windowScrollTop > sidebarHeight - 60 && sidebar.length) {
        $('.content').css({
            'position': 'fixed',
            'top': '70px',
            'width': '360px'
        });
    } else {
        $('.content').removeAttr("style");
    }
});

/*禁止键盘操作*/
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if((e.keyCode === 123) || (e.ctrlKey) || (e.ctrlKey) && (e.keyCode === 85)){
		return false;
	}
}; 
