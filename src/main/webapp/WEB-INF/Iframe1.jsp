<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.river{
	text-align:center;
}
.river .title{
	font-size:18px;
	color:#008acd;
	margin:0;
}
.river .desc{
	font-size:12px;
	margin-top:5px;
	color:#aaa;
}
</style>
<div class="river" style="height:100%;background:url(<c:url value="/assets/images/${station.fileCd}"></c:url>);background-position:center;">
	<p class="title">汇流示意</p>
	<p class="desc">${station.stname}</p>
</div>
