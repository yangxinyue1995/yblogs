<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tags/resources-tag.tld" prefix="resources"%>


<script type="text/javascript">
	var baseUrl = '${root}';
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		complete : function(XMLHttpRequest, textStatus) {
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
			if (sessionstatus == "timeout") {
				layer.alert("您的登陆已经超时，请重新登陆！", {
					icon : 7
				}, function() {
					window.location.href = baseUrl + "/login.html";
				});
			}
		}
	});
	
</script>