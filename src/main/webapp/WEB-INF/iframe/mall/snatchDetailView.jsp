<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../common/HeaderCommon.jsp" %>
</head>
<body class="body">

<!-- 表格 -->
<div id="dateTable"></div>

<%-- <script type="text/javascript" src="<c:url value="/assets/static/js/DateTimeFormat.js"></c:url>"></script> --%>

<script type="text/javascript">

	function loadData(productId){
		layui.config({
		    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
		}).extend({                         // 模块别名
		    vip_nav: 'vip_nav'
		    , vip_tab: 'vip_tab'
		    , vip_table: 'vip_table'
		});
	
	    // layui方法
	    layui.use(['table', 'form', 'layer', 'vip_table','util'], function () {
	
	        // 操作对象
	        var form = layui.form
	                , table = layui.table
	                , layer = layui.layer
	                , vipTable = layui.vip_table
	                ,util = layui.util
	                , $ = layui.jquery;
	
	        // 表格渲染
	        var tableIns = table.render({
	            elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
	            , height: vipTable.getFullHeight()    //容器高度
	            , cols: [[                  //标题栏
	                {field: 'nick', title: '昵称', width: 80}
	                , {field: 'snatchNum', title: '夺宝号', width: 330}
	                , {field: 'currentRound', title: '期数', width: 120}
	                , {field: 'createTime', title: '夺宝时间', width: 120, templet: '<div>{{ layui.util.toDateString(d.buyTime) }}</div>'}
	            ]]
	            , id: 'dataCheck'
	            , url: '<c:url value="/cms/product/snatchDetailByProductId"></c:url>'
	            , method: 'post'
	            , where: {productId : productId}
	            , page: true
	            , limits: [30, 60, 90, 150, 300]
	            , limit: 30 //默认采用30
	            , loading: false
	            , done: function (res, curr, count) {
	            	$("[data-field='id']").css('display','none');
	                //如果是异步请求数据方式，res即为你接口返回的信息。
	                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	                /* console.log(res);
	
	                //得到当前页码
	                console.log(curr);
	
	                //得到数据总量
	                console.log(count); */
	            }
	        });
	
	    });
	}
</script>

</body>
</html>