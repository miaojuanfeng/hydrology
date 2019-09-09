<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="layui-side-scroll">
        <!-- 左侧主菜单添加选项卡监听 -->
        <ul class="layui-nav layui-nav-tree" lay-filter="side-main">
            <c:forEach items="${stationList}" var="station">
                <li class="layui-nav-item">
                    <a href="<c:url value="/cms/station/${station.userStcd}"></c:url>" class="<c:if test="${station.userStcd == stcd}">selected</c:if>">${station.station.stname}站</a>
                </li>
            </c:forEach>
        </ul>
    </div>
