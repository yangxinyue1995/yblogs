<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
	<meta content="text/html;charset=Utf-8" http-equiv="Content-Type" />
	<meta name="copyright" content="Copyright (c) FanJianZhi">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta name="applicable-device" content="pc,mobile">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta name="renderer" content="webkit" />
	<meta name="referrer" content="never"/>
	<title>博客</title>
	<meta name="keywords" content="博客" />
	<meta name="description" content="博客"  />
	<meta property="qc:admins" content="1543437157611256216375" />
	<meta property="wb:webmaster" content="d550602d19a4cb44" />
	<xbasehref="${root}/resources/" />
	<!--[if IE]></base><![endif]-->
	<link href="${root}/resources/favicon.ico" tppabs="${root}/resources/favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${root}/resources/static/css/icon.css" tppabs="${root}/resources/static/css/icon.css" />
	<link href="${root}/resources/static/css/default/common.css-v=20150409.css" tppabs="${root}/resources/static/css/default/common.css?v=20150409" rel="stylesheet" type="text/css" />
	<link href="${root}/resources/static/css/default/link.css-v=20150409.css" tppabs="${root}/resources/static/css/default/link.css?v=20150409" rel="stylesheet" type="text/css" />
	<link href="${root}/resources/static/js/plug_module/style.css-v=20150409.css" tppabs="${root}/resources/static/js/plug_module/style.css?v=20150409" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${root}/resources/static/css/fox/fox.css" tppabs="${root}/resources/static/css/fox/fox.css" />
	<link rel="stylesheet" type="text/css" href="${root}/resources/layui/css/layui.css" tppabs="${root}/resources/static/css/fox/fox.css" />
	<script type="text/javascript">
		var _2AD97325483C5FFEC172CFCE478D563D="";
		var G_POST_HASH=_2AD97325483C5FFEC172CFCE478D563D;
		var G_INDEX_SCRIPT = "";
		var G_SITE_NAME = "博客";
		var G_BASE_URL = "${root}/resources/index.htm";
		var G_STATIC_URL = "${root}/resources/static/";
		var G_UPLOAD_URL = "${root}/resources/uploads/";
		var G_USER_ID = "";
		var G_USER_NAME = "";
		var G_UPLOAD_ENABLE = "N";
		var G_UNREAD_NOTIFICATION = 0;
		var G_NOTIFICATION_INTERVAL = 100000;
		var G_CAN_CREATE_TOPIC = "";
		var G_INDEX_EDITOR = "0";
		var G_ADVANCED_EDITOR_ENABLE = "Y";
		G_USER_ID=G_USER_ID==''?'':parseInt(G_USER_ID);

	</script>
	<script src="${root}/resources/static/js/jquery.2.js-v=20150409.js" tppabs="${root}/resources/static/js/jquery.2.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/jquery.form.js-v=20150409.js" tppabs="${root}/resources/static/js/jquery.form.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/plug_module/plug-in_module.js-v=20150409.js" tppabs="${root}/resources/static/js/plug_module/plug-in_module.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/jquery.easing.min.js-v=20150409.js" tppabs="${root}/resources/static/js/jquery.easing.min.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/aws.js-v=20150409.js" tppabs="${root}/resources/static/js/aws.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/aw_template.js-v=20150409.js" tppabs="${root}/resources/static/js/aw_template.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/app.js-v=20150409.js" tppabs="${root}/resources/static/js/app.js?v=20150409" type="text/javascript"></script>
	<script src="${root}/resources/static/js/jquery.lazyload.js-v=20150409.js" tppabs="${root}/resources/static/js/jquery.lazyload.js?v=20150409" type="text/javascript"></script>
	<script type="text/javascript" src="${root}/resources/static/js/compatibility.js" tppabs="${root}/resources/static/js/compatibility.js"></script>
	<script type="text/javascript" src="${root }/resources/static/js/jquery.ias.js" tppabs="${root }/resources/static/js/jquery.ias.js"></script>
	<script type="text/javascript" src="${root }/resources/static/js/scripts2.js" tppabs="${root }/resources/static/js/scripts2.js"></script>
	<script type="text/javascript" src="${root }/resources/login/js/bootstrapValidator.js" tppabs="${root }/resources/static/js/scripts2.js"></script>
	<script type="text/javascript" src="${root }/resources/layui/layui.js" tppabs="${root }/resources/static/js/scripts2.js"></script>
	<script type="text/javascript" src="${root }/resources/layui/lay/modules/layedit.js" tppabs="${root }/resources/static/js/scripts2.js"></script>
	<script type="text/javascript" src="${root }/resources/static/js/cursor-effects.js" tppabs="${root }/resources/static/js/cursor-effects.js"></script>
	<!--[if lte IE 8]>
	<script type="text/javascript" src="${root}/resources/static/js/respond.js" tppabs="${root}/resources/static/js/respond.js"></script>
	<![endif]-->
	<style>
		.ias_trigger{
			text-align: center;
		}
		#content-fox a:hover{
			color:#444;
		}
		#quotation{
			/*height: 100%;*/
		}
		.heder_right {
			float: right;
			width: 285px;
			height: 192px;
			margin-bottom: 10px;
			background-color: #fff;
			text-align: center;
			text-align: -webkit-center;
			padding: 5px;
			box-sizing: border-box;
			overflow: hidden;
			padding-top: 15px;
		}
		.heder_right:hover{
			box-shadow:0 0 8px #e5e5e5;
		}
		.img_box{
			width: 90px;
			height: 90px;
			border-radius: 50%;
			margin-bottom: 15px;
			overflow: hidden;
		}
		.img_box img{
			width: 100%;
			height: 100%;
		}
		.fixedff{
			right: 260px;
			top: 95px;
		}
		#us{
			color: #f35626;
			-webkit-animation: hue 6s infinite linear;
		}
		@-webkit-keyframes hue {
			from {
				color: #f35626;
			}
			to {
				color: #FFFF00;
			}
		}
		.tags-list a{ display:inline-block;margin-right:10px;line-height:14px;border:1px solid #eaeaea; border-radius:4px;padding:5px;margin:5px;}
		.tags-list a:hover{border-color:#ffbb33;}
		.fc-blue{color:#0077EE;}
		section {display: none}
	</style>
	<script type="text/javascript">
	(function fairyDustCursor() {

	var possibleColors = ["#D61C59", "#E7D84B", "#1B8798"]
	var width = window.innerWidth;
	var height = window.innerHeight;
	var cursor = { x: width / 2, y: width / 2 };
	var particles = [];

	function init() {
	bindEvents();
	loop();
	}

	// Bind events that are needed
	function bindEvents() {
	document.addEventListener('mousemove', onMouseMove);
	window.addEventListener('resize', onWindowResize);
	}

	function onWindowResize(e) {
	width = window.innerWidth;
	height = window.innerHeight;
	}

	function onMouseMove(e) {
	cursor.x = e.clientX;
	cursor.y = e.clientY;

	addParticle(cursor.x, cursor.y, possibleColors[Math.floor(Math.random() * possibleColors.length)]);
	}

	function addParticle(x, y, color) {
	var particle = new Particle();
	particle.init(x, y, color);
	particles.push(particle);
	}

	function updateParticles() {

	// Updated
	for (var i = 0; i < particles.length; i++) {
	particles[i].update();
	}

	// Remove dead particles
	for (var i = particles.length - 1; i >= 0; i--) {
	if (particles[i].lifeSpan < 0) {
	particles[i].die();
	particles.splice(i, 1);
	}
	}

	}

	function loop() {
	requestAnimationFrame(loop);
	updateParticles();
	}

	/**
	* Particles
	*/

	function Particle() {

	this.character = "*";
	this.lifeSpan = 120; //ms
	this.initialStyles = {
	"position": "fixed",
	"display": "inline-block",
	"top": "0px",
	"left": "0px",
	"pointerEvents": "none",
	"touch-action": "none",
	"z-index": "10000000",
	"fontSize": "25px",
	"will-change": "transform"
	};

	// Init, and set properties
	this.init = function (x, y, color) {

	this.velocity = {
	x: (Math.random() < 0.5 ? -1 : 1) * (Math.random() / 2),
	y: 1
	};

	this.position = { x: x + 10, y: y + 10 };
	this.initialStyles.color = color;

	this.element = document.createElement('span');
	this.element.innerHTML = this.character;
	applyProperties(this.element, this.initialStyles);
	this.update();

	document.querySelector('.js-cursor-container').appendChild(this.element);
	};

	this.update = function () {
	this.position.x += this.velocity.x;
	this.position.y += this.velocity.y;
	this.lifeSpan--;

	this.element.style.transform = "translate3d(" + this.position.x + "px," + this.position.y + "px, 0) scale(" + (this.lifeSpan / 120) + ")";
	}

	this.die = function () {
	this.element.parentNode.removeChild(this.element);
	}

	}

	/**
	* Utils
	*/

	// Applies css `properties` to an element.
	function applyProperties(target, properties) {
	for (var key in properties) {
	target.style[key] = properties[key];
	}
	}

	if (!('ontouchstart' in window || navigator.msMaxTouchPoints)) init();
	})();
	</script>
</head>
<noscript unselectable="on" id="noscript">
	<div class="aw-404 aw-404-wrap container"><img src="${root}/resources/static/common/no-js.jpg" tppabs="${root}/resources/static/common/no-js.jpg">
		<p>你的浏览器禁用了JavaScript, 请开启后刷新浏览器获得更好的体验!</p>
	</div>
</noscript>
<body>
<div class="aw-top-menu-wrap">
	<span class="js-cursor-container"></span>
	<div class="oldsite hidden-xs"><a href="javascript:StranBody()" id="kevin_language" title="點擊以繁體中文方式瀏覽">繁</a></div>
	<div class="container clearfix"> <a href="#" tppabs="" class="" title="个人博客"></a>
		<div class="aw-top-nav clearfix">
			<nav>
				<ul class="nav navbar-nav navbar-nav-fox">
					<li><a href="${root}" tppabs="${root}" title="首页" target="_self">
						首页            </a></li>
					<%--<li><a href="${root}/gaoxiao/index" title="" target="_self">--%>
						<%--搞笑图片            </a><sup class="sup-beta">beta</sup></li>--%>
					<%--<li><a href="" tppabs="" title="" target="_self">--%>
						<%--技术分类            </a><sup class="sup-new">new</sup></li>--%>
					<li> <a><i class="icon icon-ul"></i>
						轻松一刻            </a>
						<div class="dropdown-list flip pull-right">
							<ul id="extensions-nav-list">
								<li><a href="${root}/photos/index" tppabs="${root}/photos/index" target="_self"><i class="icon icon-cart"></i>
									美图                  </a></li>
								<li><a href="${root}/music/index" tppabs="${root}/music/index"><i class="icon icon-headphones"></i>
									音乐                  </a></li>
								<li><a href="${root}/gaoxiao/index" tppabs="" title="" target="_self"><i class="icon icon-picture"></i>
									动图                  </a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<!-- end 导航 -->
		<!-- 用户栏 -->
		<div class="all-top-act clearfix">
			<!-- 搜索框 -->
			<div class="all-top-search">
				<form class="clearfix" action="${root}/blogs/search/" id="global_search_form" method="post" target="_blank">
					<fieldset>
						<input type="text" class="input-text all-top-search-input" name="q" placeholder="搜索" id="aw-search-query">
						<span title="搜索" id="global_search_btns" onClick="$('#global_search_form').submit();"><i class="icon icon-search submit-button all-top-search-button"></i></span>
						<div class="aw-dropdown">
							<div class="mod-body">
								<p class="title">
									搜索
								</p>
								<ul class="aw-dropdown-list hide"></ul>
								<p class="search">
									<span>搜索 :</span>
									<a onClick="$('#global_search_form').submit();"></a>
								</p>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
				<!-- end 搜索框 -->
				<div class="unland">
					<c:choose>
						<c:when test="${empty manager}">
							<%--<a class="register" id="foxlogin" href="${root}/frontPages/login" tppabs="${root}/frontPages/login/">--%>
								<%--登录--%>
							<%--</a>--%>
							<a class="register" id="foxlogin" href="#" tppabs="${root}/frontPages/login/" onclick="jump(window.location.href)">
								登录
							</a>
							<span class="or">|</span>
							<a href="${root}/frontPages/resiger" tppabs="${root}/frontPages/resiger/" target="_blank">
								注册
							</a>
						</c:when>
						<c:otherwise>
							<c:if test="${manager.id==1}">
								<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#xe642;</i>
								<a class="register" id="foxlogin" href="${root}/edit/index/" tppabs="${root}/edit/index/">
									写博客
								</a>
								&nbsp;&nbsp;
							</c:if>
							欢迎您，${manager.userName}<span class="or">&nbsp;&nbsp;&nbsp;</span><a href="${root }/frontPages/logout" title="登出">登出</a>
						</c:otherwise>
					</c:choose>
					<!-- end 登陆&注册栏 -->
				</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function jump(url) {
		setCookie(url,window.location.href);
		window.open(encodeURI("${root}/frontPages/login"));
	}

	// var a_idx = 0;
	// jQuery(document).ready(function($) {
	// 	$("body").click(function(e) {
	// 		var a = new Array("❤","❤","❤","❤","❤","❤","❤","❤","❤","❤","❤");
	// 		var $i = $("<span></span>").text(a[a_idx]);
	// 		a_idx = (a_idx + 1) % a.length;
	// 		var x = e.pageX,
	// 				y = e.pageY;
	// 		$i.css({
	// 			"z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
	// 			"top": y - 20,
	// 			"left": x,
	// 			"position": "absolute",
	// 			"font-weight": "bold",
	// 			"color": "rgb("+~~(255*Math.random())+","+~~(255*Math.random())+","+~~(255*Math.random())+")"
	// 		});
	// 		$("body").append($i);
	// 		$i.animate({
	// 					"top": y - 180,
	// 					"opacity": 0
	// 				},
	// 				1500,
	// 				function() {
	// 					$i.remove();
	// 				});
	// 	});
	// });
</script>