<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../common/HeaderCommon.jsp" %>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>

<div class="layui-form">
    <div class="layui-container">
        <div class="layui-row" style="margin-top: 10px">
            <div class="layui-col-xs5">
                <label class="layui-form-label">类别名称<span style="color: red; vertical-align: sub">*</span>:</label>
            </div>
            <div class="layui-col-xs7">
                <input type="text" name="title" required lay-verify="title" placeholder="请输入类别名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-row" style="margin-top: 10px;">
            <div class="layui-col-xs5">
                <label class="layui-form-label">是否农特产:</label>
            </div>
            <div class="layui-col-xs7">
                <input type="radio" name="farm" value="1" title="是" checked>
                <input type="radio" name="farm" value="0" title="否">
            </div>
        </div>

        <div style="text-align: right; margin-top: 10px;">
            <button class="layui-btn layui-btn-warm" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-ok" style="font-size: 30px;"></i>
            </button>
        </div>

    </div>
</div>

<style type="text/css">
    element.style {
        vertical-align: sub;
    }
</style>

<script type="text/javascript">
    layui.use('form',() => {
        layui.form.render('radio'); //框架Bug, 二次刷新 radio 组件才会显示
    });

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    layui.use('form', function(){
        let form = layui.form;

        form.verify({
            title: function(value){
                let charLen = Utils.getCharLen(value);
                if(charLen < 2 || charLen > 30){
                    return '请输入2至30个字符';
                }
            }
        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        form.on('submit(formDemo)', function(data){
            alert(122);
            layer.msg(JSON.stringify(data.field));
            return false;
        });

    });

</script>
<script src="/assets/static/js/Utils.js"></script>
</body>
</html>
