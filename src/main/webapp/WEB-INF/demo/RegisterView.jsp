<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="common/HeaderCommon.jsp" %>
</head>
<body>

<div class="login-main">
    <header class="layui-elip">注册页</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="account" required  lay-verify="required" placeholder="邮箱" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline login-btn">
            <button type="submit" class="layui-btn">注册</button>
        </div>
        <hr/>
        <p><a href="javascript:;" class="fl">已有账号？立即登录</a><a href="javascript:;" class="fr">忘记密码？</a></p>
    </form>
</div>

<script type="text/javascript">
    layui.use(['form'], function () {
        var form    = layui.form
            ,$      = layui.jquery;

        // you code ...


    });
</script>
</body>
</html>