<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../inc/header_inc.jsp"/>
<link rel="stylesheet" type="text/css" href="${root}/resources/layui/css/layui.css" tppabs="${root}/resources/static/css/fox/fox.css" />
<script type="text/javascript" charset="utf-8" src="${root}/resources/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/resources/utf8-jsp/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/resources/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<style>
    #edit_div{
        position: relative;
        z-index: 1;
        margin-bottom: 10px;
        background-color: #fff;
        border-bottom: 3px solid #e5e5e5;
        min-height: 120px;
        border-radius: 4px;
    }
    #title {
        display: inline-block;
        width: 92%;
        height: 40px;
        line-height: 40px;
        box-sizing: border-box;
        padding-left: 16px;
        border: none;
        margin-top: 10px;
        margin-left: 25px;
        background-color: #efefef;
        color: #4d4d4d;
    }
    .layui-input {
        margin-left: 29px;
    }
</style>
<script type="text/javascript">
$(function () {
    //实例化编辑器
    var ue = UE.getEditor('container', {
        toolbars: [
            [
                'undo', //撤销
                'bold', //加粗
                'underline', //下划线
                'preview', //预览
                'horizontal', //分隔线
                'inserttitle', //插入标题
                'cleardoc', //清空文档
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                // 'insertimage', //多图上传
                // 'attachment', //附件
                // 'music', //音乐
                // 'inserttable', //插入表格
                'emotion', //表情
                // 'insertvideo', //视频
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'fullscreen', //全屏
                'edittip ', //编辑提示
                'customstyle', //自定义标题
                'template', //模板
            ]
        ]
    });

    $("#title").bind('input porpertychange', function (e) {
        max_lengthSet($(this).val().length);
    });
    function max_lengthSet(unm) {
        $('.max-length-change').text(unm).addClass(unm === 100 ? 'toMax' : '').removeClass(unm < 100 ? 'toMax' : '');
    }
    max_lengthSet($("#title").val().length);

    layui.use("form", function() {
        var form = layui.form;
        var textarea = $("#edit").val();
        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符吧';
                }
            },
            tag : function(value) {
                var tag = $("#tag").val();
                var message = "";
                if (tag.length< 0) {
                    message = "请给文章添加标签";
                }
                return message;
            },
            content: function(value) {
                return layedit.sync(index);
            }
        });
        // 监听提交
        form.on("submit(btn_commit)", function(data) {
            var html = ue.getContent();
            $("#tex").val(escape2Html(html));
            console.log($("#tex").val());
                $.ajax({
                    type: 'POST',
                    data: $("#subForm").serialize(),
                    url: '${root}/edit/saveBlogs/',
                    success: function (data) {
                        console.log(data)
                        console.log(data.msg)
                        if(data.code==0){
                            console.log(data.msg)
                            layer.msg(data.msg);
                            window.location.reload();
                        }
                    },
                    error: function (data) {
                        layer.msg("提交失败！！")
                    }
                });
                return false;
        });
        function escape2Html(str) {
            var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
            return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
        }
    })
})

</script>
<div class="aw-container-wrap">
    <div class="page-body clearfix">
        <div class="aw-main-content" style="margin-top:10px">
            <div class="aw-mod">
                <div class="mod-body" id="s" style="width: 1000px">
                    <div class="aw-common-list">
                        <div class="content">
                            <form class="layui-form" action="${root}/edit/saveBlogs/" method="post" id="subForm">
                                <div class="edit" id="edit_div">
                                    <div>
                                        <input type="text" class="layui-input" id="title" name="title" maxlength="100" placeholder="请输入文章标题" lay-verify="required" >
                                        <span class="max-length"><span class="max-length-change">0</span>/100</span>
                                    </div>
                                    <br />
                                    <div style="height: 600px;">
                                        <!-- 加载编辑器的容器 -->
                                        <script id="container" name="content" type="text/plain">
                                            开始你的创作吧！~
                                    </script>
                                        <textarea style="display: none" id="tex" name="edit"></textarea>
                                    </div>
                                    <br/>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">类型</label>
                                        <div class="layui-input-block">
                                            <input type="radio" name="type" value="原创" title="原创" checked="">
                                            <input type="radio" name="type" value="转载" title="转载">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">标签</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="tag" id="tag" lay-verify="required" placeholder="请输入文章标签" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-form-item" style="text-align: center;margin-left: -75px;">
                                        <div class="layui-input-block">
                                            <button type="submit" class="layui-btn" lay-submit="" lay-filter="btn_commit">立即提交</button>
                                            <button type="reset" class="layui-btn layui-btn-primary">重置内容</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer_inc.jsp"/>
</html>
