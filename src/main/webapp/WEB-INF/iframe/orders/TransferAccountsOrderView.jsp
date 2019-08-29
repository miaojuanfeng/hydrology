<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../common/HeaderCommon.jsp"%>
</head>
<body class="body">

	<!-- 工具集 -->
	<div class="my-btn-box">
		<div class="layui-row">
			
			<div class = "layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">转出用户</span>
				</div>
				<div class = "layui-col-md8">
					<select class="layui-input" id = "expendUserSelect" lay-verify="">
						<option value="">全部</option>
					</select>
				</div>
			</div>
			<div class = "layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">转账对象</span>
				</div>
				<div class = "layui-col-md8">
					<select class="layui-input" id = "incomeUserSelect" lay-verify="">
						<option value="">全部</option>
					</select>
				</div>
			</div>
			<div class="layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">转账时间</span>
				</div>
				<div class = "layui-col-md8">
					<input type="text" class="layui-input" id="createTimeRange" placeholder=" - ">
				</div>
			</div>

			<div class = "layui-col-md1" style="text-align: right;">
				<button class="layui-btn mgl-20" id = "btn-search">查询</button>
			</div>
			
			<div class="layui-col-md1" style="float: right;"> 
				<a style="float: right;" class="layui-btn btn-add btn-default" id="btn-refresh"><i class="layui-icon">&#xe669;</i></a>
			</div>
		</div>
	</div>

	<!-- 表格 -->
	<div id="dataTable" lay-filter="table"></div>
	<%-- <script type="text/javascript"
		src="<c:url value="/assets/static/js/DateTimeFormat.js"></c:url>"></script> --%>
	<script type="text/javascript">
		layui.config({
			base : '<c:url value="/assets/static/js/"></c:url>' // 模块目录
		}).extend({ // 模块别名
			vip_nav : 'vip_nav',
			vip_tab : 'vip_tab',
			vip_table : 'vip_table'
		});

		// layui方法
		layui.use([ 'table', 'form', 'layer', 'vip_table','laydate','util' ],
			function() {

				// 操作对象
				var form = layui.form, table = layui.table, layer = layui.layer, vipTable = layui.vip_table,laydate = layui.laydate,util = layui.util, $ = layui.jquery;

				//填充user下拉框
				$.ajax({
					type : "post",
					url : "<c:url value="/cms/order/getIncomeUser"></c:url>",
					dataType : "json",
					success : function(data) {
						var html = "";
						if(data != null){
							$.each(data, function (index, item) {
								html += "<option value='" + item.iUserId + "'>" + item.iName + "</option>";
							})
							$("#incomeUserSelect").append(html);
							form.render('select');
						}
					}
				});
				
				//填充user下拉框
				$.ajax({
					type : "post",
					url : "<c:url value="/cms/order/getExpendUser"></c:url>",
					dataType : "json",
					success : function(data) {
						var html = "";
						if(data != null){
							$.each(data, function (index, item) {
								html += "<option value='" + item.eUserId + "'>" + item.eName + "</option>";
							})
							$("#expendUserSelect").append(html);
							form.render('select');
						}
					}
				});
				
				
				//日期控件
				 laydate.render({
				    elem: '#createTimeRange'
				    ,range: '~'
				  });
				
				// 表格渲染
				var tableIns = table
						.render({
							elem : '#dataTable' //指定原始表格元素选择器（推荐id选择器）
							,
							height : vipTable.getFullHeight() //容器高度
							,
							cols : [ [ //标题栏
									{
										field : 'orderNo',
										title : '订单号',
										width : 330
									},
									{
										field : 'expendName',
										title : '转出用户',
										width : 100
									},
									{
										field : 'incomeName',
										title : '转账对象',
										width : 180,
									},
									{
										field : 'totalMoney',
										title : '转账数量',
										width : 180
									},
									{
										field : 'createTime',
										title : '转账时间',
										width : 180,
										templet : '<div>{{ layui.util.toDateString(d.createTime) }}</div>'
									}, {
										fixed : 'right',
										title : '操作',
										width : 240,
										align : 'left',
										toolbar : '#barOption'
									} //这里的toolbar值是模板元素的选择器
							] ],
							url : '<c:url value="/cms/order/transferAccountRecord"></c:url>',
							method : 'post',
							page : true,
							limits : [ 30, 60, 90, 150, 300 ],
							limit : 30 //默认采用30
							,
							loading : false,
							done : function(res, curr, count) {
								$("[data-field='id']").css(
										'display', 'none');
								//如果是异步请求数据方式，res即为你接口返回的信息。
								//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
								/* console.log(res);

								//得到当前页码
								console.log(curr);

								//得到数据总量
								console.log(count); */
							}
							,id : 'dataTable'
						});

				// 获取选中行
				table.on('checkbox(dataTable)', function(obj) {
					/* layer.msg('123');
					console.log(obj.checked); //当前是否选中状态
					console.log(obj.data); //选中行的相关数据
					console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one */
				});

				// 刷新
				$('#btn-refresh').on('click', function() {
					$('form')[0].reset();
					$('#btn-search').click();
				});
				
				$('#btn-search').on('click',function(){
					/* var title = $('productName').val();
					var type = $('#productType').val();
					var userId = $('#userSelect').val();
					var timeRange = $('#createTimeRange').val(); */
					table.reload('dataTable',{
						where:{
							expendUserId : $('#expendUserSelect').val(),
							incomeUserId : $('#incomeUserSelect').val(),
							timeRange : $('#createTimeRange').val()
						}
					})
					
				});

				table.on('tool(table)',
					function(obj) {
						var data = obj.data;
						if (obj.event === 'buyDetail') {
							layer.open({
								type : 2,
								skin : 'layui-layer-rim', //加上边框
								area : [ '70%',
										'640px' ], //宽高
								shadeClose : true,
								title : '购买记录',
								content : '<c:url value="/cms/product/toBuyDetail"></c:url>',
								success : function(
										layero,
										index) {
									// 获取子页面的iframe
									var iframe = window['layui-layer-iframe'
											+ index];
									// 向子页面的全局函数child传参
									iframe
											.loadData(data.id);
								}
							});
						} else if (obj.event === 'snatchDetail') {
							layer.open({
								type : 2,
								skin : 'layui-layer-rim', //加上边框
								area : [ '50%',
										'640px' ], //宽高
								shadeClose : true,
								title : '夺宝记录',
								content : '<c:url value="/cms/product/toSnatchDetail"></c:url>',
								success : function(
										layero,
										index) {
									// 获取子页面的iframe
									var iframe = window['layui-layer-iframe'
											+ index];
									// 向子页面的全局函数child传参
									iframe.loadData(data.id);
								}
							});
						}else if (obj.event === 'snatchResult') {
							layer.open({
								type : 2,
								skin : 'layui-layer-rim', //加上边框
								area : [ '50%',
										'640px' ], //宽高
								shadeClose : true,
								title : '夺宝记录',
								content : '<c:url value="/cms/product/toSnatchResult"></c:url>',
								success : function(
										layero,
										index) {
									// 获取子页面的iframe
									var iframe = window['layui-layer-iframe'
											+ index];
									// 向子页面的全局函数child传参
									iframe.loadData(data.id);
								}
							});
						} else if(obj.event === 'LowerShelf'){
							layer.open({
								type : 1,
								title : '填写下架理由',
								content :` <div class="layui-form-item layui-form-text">
								      		<textarea placeholder="请输入内容" id = "reason" class="layui-textarea"></textarea>
										  </div>`,
							 	btn: ['确认', '关闭'],
							 	yes: function(index, layero){
							 		$.ajax({
										type : "post",
										url : "<c:url value="/cms/product/updateProductStatus"></c:url>",
										dataType : "json",
										data :{
											id : data.id,
											reason : $('#reason').val(),
											activeStatus : 2
										},
										success : function(data) {
											 layer.close(index);
											 layer.open({
												  type: 1, 
												  content: '<div style="padding: 20px 100px;">'+ data.msg +'</div>',
												  cancel: function(index, layero){ 
													  tableIns.reload();
												  }
											});
										}
									});
							 	}
							});
						}else if(obj.event === 'shelves'){
					 		$.ajax({
								type : "post",
								url : "<c:url value="/cms/product/updateProductStatus"></c:url>",
								dataType : "json",
								data :{
									id : data.id,
									activeStatus : 0
								},
								success : function(data) {
									 layer.open({
										  type: 1, 
										  content: '<div style="padding: 20px 100px;">'+ data.msg +'</div>',
										  cancel: function(index, layero){ 
											  tableIns.reload();
										  }
									});
								}
							});
						}
					});

		});
	</script>
	<!-- 表格操作按钮集 -->
	<script type="text/html" id="barOption">
  	 	
</script>

</body>
</html>