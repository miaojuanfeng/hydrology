<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="<c:url value="/assets/layui/css/layui.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/static/css/style.css"></c:url>">
    <link rel="icon" href="<c:url value="/assets/static/image/code.png"></c:url>">
</head>
<body style="background:url(<c:url value="/assets/static/image/login_bg.jpg"></c:url>);background-size:cover;">

<div class="login-main">
    <header class="layui-elip" style="font-weight:bold;color:#1E9F95;margin-top:100px;">用户注册</header>
    <p><c:if test="${reason != null}">${reason}</c:if></p>
    <form class="layui-form" action="<c:url value="/cms/login"></c:url>" method="post">
        <div class="layui-input-inline">
        	<span style="width:38px;height:38px;display:block;float:left;background:url(<c:url value="/assets/static/image/login_u.png"></c:url>);background-size:cover;"></span>
            <input style="width:312px;border:none;" type="text" name="username" required lay-verify="required" placeholder="手机号" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
        	<span style="width:38px;height:38px;display:block;float:left;background:url(<c:url value="/assets/static/image/login_p.png"></c:url>);background-size:cover;"></span>
            <input style="width:312px;border:none;" type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
        	<span style="width:38px;height:38px;display:block;float:left;background:url(<c:url value="/assets/static/image/login_p.png"></c:url>);background-size:cover;"></span>
            <input style="width:312px;border:none;" type="password" name="password" required lay-verify="required" placeholder="确认密码" autocomplete="off" class="layui-input">
        </div>
        <!-- div class="layui-input-inline">
        	<div class="layui-col-xs8">
            	<input type="text" name="password" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-col-xs4">
            	<img width="100%" height="38" src="<c:url value="/assets/static/image/v.png"></c:url>" alt="">
            </div>
        </div -->
	    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
	        <div class="layui-input-inline">
	            <a type="submit" class="layui-btn layui-btn-normal" style="width:100%;background-color:#1E9F95;">下一步</a>
	        </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
	        <p style="margin-bottom:15px;text-align:center;">已有账号？<a style="text-decoration:underline" href="<c:url value="/cms/user/login"></c:url>">立即登录</a></p>
	    </div>
        <hr/>
        <!--<div class="layui-input-inline">
            <a type="button" class="layui-btn layui-btn-primary">QQ登录</a>
        </div>
        <div class="layui-input-inline">
            <a type="button" class="layui-btn layui-btn-normal">微信登录</a>
        </div>-->
        <p>
        	<span href="javascript:;" class="fl">赣州洪水预报系统</span><span href="javascript:;" class="fr">青年版</span>
        </p>
    </form>
</div>


<script src="<c:url value="/assets/layui/layui.js"></c:url>"></script>
<script type="text/javascript">
    layui.use(['form'], function () {

        // 操作对象
        var form = layui.form
                , $ = layui.jquery;

        // you code ...


    });
</script>
</body>
</html>