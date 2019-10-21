<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tags/resources-tag.tld" prefix="resources"%>
<resources:javascript name="resources/js/ueditor/ueditor.config"></resources:javascript>
<resources:javascript name="resources/js/ueditor/ueditor.all.min"></resources:javascript>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<resources:javascript name="resources/js/ueditor/lang/zh-cn/zh-cn"></resources:javascript>

