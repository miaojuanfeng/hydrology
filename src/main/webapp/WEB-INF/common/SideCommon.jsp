<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="layui-side-scroll">
        <!-- 左侧主菜单添加选项卡监听 -->
        <ul class="layui-nav layui-nav-tree" lay-filter="side-main">
        	<li class="layui-nav-item">
                <a href="<c:url value="/cms/station/1"></c:url>" class="<c:if test="${station == 1}">selected</c:if>">宁都站</a>
            </li>
            <li class="layui-nav-item">
                <a href="<c:url value="/cms/station/2"></c:url>" class="<c:if test="${station == 2}">selected</c:if>">汾坑站</a>
            </li>
            <li class="layui-nav-item">
                <a href="<c:url value="/cms/station/3"></c:url>" class="<c:if test="${station == 3}">selected</c:if>">石城站</a>
            </li>
        </ul>
    </div>
