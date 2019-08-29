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
	            , cols: [[   
	            	{field: 'snatchNum', title: '中奖号', width: 120}
	                ,{field: 'nick', title: '中奖用户', width: 120}
	                , {field: 'currentRound', title: '期数', width: 80}
	                , {field: 'baseNum', title: '开奖基数', width: 120}
	                , {field: 'createTime', title: '开奖时间', width: 160, templet: '<div>{{ layui.util.toDateString(d.buyTime) }}</div>'}
	                , {field:'orderStatus', title: '订单状态', width: 120, templet:'#orderStatusTemp'}
	            ]]
	            , id: 'dataCheck'
	            , url: '<c:url value="/cms/product/snatchResultByProductId"></c:url>'
	            , method: 'post'
	            , where: {id : productId}
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
<script type="text/html" id="orderStatusTemp">
	{{# if(d.orderStatus == 0){ }}
			<span>未发货</span>
	{{# }else if(d.orderStatus == 1){ }}
			<span>已发货</span>
	{{# }else if(d.orderStatus == 2){ }}
			<span>已完成</span>
	{{# }else if(d.orderStatus == 3){ }}
			<span>已过期</span>
	{{# } }}
</script>

</body>
</html>