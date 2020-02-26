<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .layui-progress-text {
        min-width: 40px;
        display: block;
    }
</style>
<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
    <div class="layui-collapse">
        <div class="layui-colla-item">
            <form class="layui-form" action="">
                <div style="width:130px;height:130px;background:url(<c:url value="${sessionScope.user.userHead}"></c:url>);background-size:cover;border-radius:50%;border:2px solid #FFF;position:absolute;margin-top:-90px;left:50%;margin-left:-70px;"></div>
                <div class="layui-form-item" style="padding-top:80px;text-align:center;font-size:22px;">
                    ${sessionScope.user.userName}
                </div>
                <div class="layui-form-item" style="padding-top:30px;font-size:22px;text-align:center;">
                    <span style="width:100px;">当前等级：</span>
                    <span>${sessionScope.user.userLevelName}</span>
                </div>
                <div class="layui-form-item" style="font-size:22px;text-align:center;">
                    <span style="width:100px;">关注测站：</span>
                    <span>${userStationName}</span>
                </div>
                <div class="layui-form-item" style="padding-top:30px;text-align:center;font-size:22px;">
                    预报次数
                </div>
                <div class="layui-form-item" style="padding:30px 80px 0 80px;text-align:center;font-size:22px;">
                    <div class="layui-progress layui-progress-big" lay-showpercent="true">
                        <div class="layui-progress-bar layui-bg-blue" lay-percent="${userlevelProcess}"></div>
                    </div>
                </div>
                <div class="layui-form-item" style="padding-top:30px;text-align:center;">
                    <a class="layui-btn layui-btn-primary layui-btn-radius">上传头像</a>
                </div>
            </form>
        </div>
    </div>
</div>