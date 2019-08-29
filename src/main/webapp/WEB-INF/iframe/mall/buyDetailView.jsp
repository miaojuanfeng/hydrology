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
	                {field: 'id', title: 'ID', width: 80}
	                , {field: 'orderNo', title: '订单号', width: 330}
	                , {field: 'nick', title: '昵称', width: 120}
	                , {field: 'unitPrice', title: '单价', width: 80}
	                , {field: 'buyCount', title: '购买数量', width: 80}
	                , {field: 'totalPayment', title: '总价', width: 120}
	                , {field: 'orderStatus', title: '订单状态', width: 100,templet:'#orderStatusTemp'}
	                , {field: 'buyTime', title: '购买时间', width: 160, templet: '<div>{{ layui.util.toDateString(d.buyTime) }}</div>'}, 
	                {
						fixed : 'right',
						title : '操作',
						width : 180,
						align : 'left',
						toolbar : '#barOption'
					} //这里的toolbar值是模板元素的选择器
	            ]]
	            , id: 'dataCheck'
	            , url: '<c:url value="/cms/product/buyDetail"></c:url>'
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
	                */
	                //得到数据总量
	                console.log(count); 
	            }
	        });
	
	        /* table.on('tool(table)',
				function(obj) {
					var data = obj.data;
        		if(obj.event === 'getAddress'){
        			$.ajax({
						type : "post",
						url : "<c:url value="/cms/product/getAddressByOrderId"></c:url>",
						dataType : "json",
						data :{
							id : data.id,
						},
						success : function(data) {
							 layer.close(index);
							 layer.open({
								  type: 1, 
								  content: `<div style="padding: 20px 100px;">收货人：${data.name}</div></n></r>
									 		 <div style="padding: 20px 100px;">手机号：${data.phoneNumber}</div></n></r>
									 		 <div style="padding: 20px 100px;">手机号：${data.postCode}</div></n></r>
								 			<div style="padding: 20px 100px;">收货地址：${data.region}+${data.address}</div>`
							});
						}
        			});
	    		}
	        }); */
	
	
	    });
	}
</script>

<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
		<a class="layui-btn layui-btn-sm" lay-event="getAddress">收货地址</a>
		{{# if(d.orderStatus != 0 && d.orderStatus != 3){ }}
				<a class="layui-btn layui-btn-sm" lay-event="">快递信息</a>
		{{# } }}
</script>

<script type="text/html" id = "orderStatusTemp">
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