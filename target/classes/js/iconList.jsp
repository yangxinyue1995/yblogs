<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>选取图标  - jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="./easyui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="./easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="./easyui/themes/iconExtension.css">
	<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
	
	
	<style type="text/css">
	.tree-indent {
		width: 5px;
	}
	</style>
	<script type="text/javascript">
function getStyleSheets() {
	var data = [];
	data[data.length] = {id: -1, text: "全部"};
	for ( var i = 0; i < document.styleSheets.length; i++) {
		var url = document.styleSheets[i].href;
		if (url) {
			data[data.length] = {
				id: i, 
				text: url.substr(url.lastIndexOf("/") + 1)
			};
		}
	}
	return data;
}

function getAllIcons() {
	var data = [];
	for ( var i = 0; i < document.styleSheets.length; i++) {
		data = $.merge(data, getIcons(i));
	}
	return data;
}

// 该jsp 需要接收两个参数，一个是回填的文本域的id,一个是关闭的窗口的id
function getIcons(index) {
	var data = [];

	var styleSheet = document.styleSheets[index];

	var rules;
	if (styleSheet.cssRules) {
		rules = styleSheet.cssRules;
	} else {
		rules = styleSheet.rules;
	}
	for ( var j = 0; j < rules.length; j++) {
		//  样式中，规约以".icon"开头的即为图标
		if (rules[j].selectorText.substr(0, 5) == ".icon") {
			data[data.length] = {
				id: index + "-" + j, 
				text: rules[j].selectorText.substr(1),
				iconCls: rules[j].selectorText.substr(1), 
				href: (styleSheet.href ? styleSheet.href : "")
			};
		}
	}

	return data;
}


$(function() {
	$("#data-grid").treegrid({
		idField: "id",
		treeField: "iconCls",
		rownumbers: true,
		striped: true,
		singleSelect: true,
		fit: true,
		fitColumns: true,
		autoRowHeight: true,
		onDblClickRow : function(index,field,value){
			parent.$("#${param.iconText}").val((index.text));
			parent.$("#${param.iconWin}").dialog("close");
		},
		columns: [[{
				field: "iconCls", 
				title: "IconCls", 
				halign: "center", 
				width: 60,
				formatter: function(value, row, index) {
					return "";
				}
			}, {
				field: "id", 
				title: "ID", 
				halign: "center", 
				width: 60,
				hidden: true
			}, {
				field: "text", 
				title: "Text", 
				halign: "center", 
				width: 100
			}, {
				field: "href", 
				title: "Href", 
				halign: "center", 
				width: 200
			}
		]]
	});

	$("#css-list").combotree({
		data: getStyleSheets(),	
		onClick: function(node) {
			var id = node.id;
			var data = id<0 ? getAllIcons() : getIcons(id);
			$("#data-grid").treegrid("loadData", data);
		},
		onLoadSuccess : function(node,data) {
			$('#css-list').combotree('setValue', -1);

			var data = getAllIcons();
			$("#data-grid").treegrid("loadData", data);
		}
	});
});
	</script>
</head>
<body>
	样式文件列表:<br />
	<input id="css-list" style="width:200px;">
	<br /> 
	EasyUI图标TreeGrid展示，双击回填数据:<br />
	<table id="data-grid"></table>
</body>
</html>