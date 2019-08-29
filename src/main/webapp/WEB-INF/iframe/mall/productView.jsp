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
					<span class="layui-form-label" style="text-align: left;">商品名称</span>
				</div>
				<div class = "layui-col-md7">
					<div class="layui-input-inline">
						<input type="text" autocomplete="off" id = "productName" placeholder="请输入商品名称"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class = "layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">商品类型</span>
				</div>
				<div class = "layui-col-md8">
					<select id="productType" class="layui-input" lay-verify="">
						<option value="">全部</option>
						<option value="0">活力商品</option>
						<option value="1">夺宝商品</option>
						<option value="2">溯源商品</option>
					</select>
				</div>
			</div>
			<div class = "layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">上架人</span>
				</div>
				<div class = "layui-col-md8">
					<select name="productType" class="layui-input" id = "userSelect" lay-verify="">
						<option value="">全部</option>
					</select>
				</div>
			</div>
			<div class = "layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">商品状态</span>
				</div>
				<div class = "layui-col-md8">
					<select id="activeSelect" class="layui-input" lay-verify="">
						<option value="">全部</option>
						<option value="0">待审核</option>
						<option value="1">上架中</option>
						<option value="2">下架</option>
						<option value="3">已删除</option>
						<option value="4">未通过</option>
					</select>
				</div>
			</div>
			<div class="layui-col-md2">
				<div class="layui-col-md4">
					<span class="layui-form-label" style="text-align: left;">上架时间</span>
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
					url : "<c:url value="/cms/product/getUserSelect"></c:url>",
					dataType : "json",
					success : function(data) {
						var html = "";
						if(data != null){
							$.each(data, function (index, item) {
								html += "<option value='" + item.userId + "'>" + item.name + "</option>";
							})
							$("#userSelect").append(html);
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
										field : 'id',
										title : 'ID',
										width : 80
									},
									{
										field : 'title',
										title : '商品名称',
										width : 180
									},
									{
										field : 'vender',
										title : '厂商',
										width : 100
									},
									{
										field : 'productionTime',
										title : '生产日期',
										width : 180,
										sort : false,
										fixed : false,
										templet : '<div>{{ layui.util.toDateString(d.productionTime) }}</div>'
									},
									{
										field : 'description',
										title : '商品描述',
										width : 180
									},
									{
										field : 'price',
										title : '人民币价格',
										width : 80
									},
									{
										field : 'fruitPrice',
										title : '活力果价格',
										width : 80
									},
									{
										field : 'storeCount',
										title : '库存',
										width : 80
									},
									{
										field : 'saleCount',
										title : '购买人数',
										width : 80
									},
									{
										field : 'certificateCount',
										title : '认证次数',
										width : 87
									},
									{
										field : 'type',
										title : '商品类型',
										width : 100,
										templet:'#typeTemp'
									},
									{
										field : 'userName',
										title : '上架人',
										width : 100
									},
									{
										field : 'createTime',
										title : '上架时间',
										width : 160,
										sort : false,
										fixed : false,
										templet : '<div>{{ layui.util.toDateString(d.createTime) }}</div>'
									}, {
										field : 'activeStatus',
										title : '商品状态',
										width : 100,
										templet : '#statusTemp'
									}, {
										fixed : 'right',
										title : '操作',
										width : 300,
										align : 'left',
										toolbar : '#barOption'
									} //这里的toolbar值是模板元素的选择器
							] ],
							url : '<c:url value="/cms/product/getProductList"></c:url>',
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
							title : $('#productName').val(),
							type : $('#productType').val(),
							userId : $('#userSelect').val(),
							activeStatus : $('#activeSelect').val(),
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
										  </div> `,
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
						}else if(obj.event === 'AuditFailed'){
							layer.open({
								type : 1,
								title : '填写未通过理由',
								content :` <div class="layui-form-item layui-form-text">
								      		<textarea placeholder="请输入内容" id = "reason" class="layui-textarea"></textarea>
										  </div> `,
							 	btn: ['确认', '关闭'],
							 	yes: function(index, layero){
							 		$.ajax({
										type : "post",
										url : "<c:url value="/cms/product/updateProductStatus"></c:url>",
										dataType : "json",
										data :{
											id : data.id,
											reason : $('#reason').val(),
											activeStatus : 4
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
						}else if(obj.event === 'deleteShelves'){
							layer.open({
								type : 1,
								title : '填写删除理由（删除后将不可以上架）',
								content :` <div class="layui-form-item layui-form-text">
								      		<textarea placeholder="请输入内容" id = "reason" class="layui-textarea"></textarea>
										  </div> `,
							 	btn: ['确认', '关闭'],
							 	yes: function(index, layero){
							 		$.ajax({
										type : "post",
										url : "<c:url value="/cms/product/updateProductStatus"></c:url>",
										dataType : "json",
										data :{
											id : data.id,
											reason : $('#reason').val(),
											activeStatus : 3
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
									activeStatus : 1
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
  	 	{{# if(d.type != 1){ }}
				<a class="layui-btn layui-btn-sm" lay-event="buyDetail">购买记录</a>
		{{# }else{ }}
				<a class="layui-btn layui-btn-sm" lay-event="snatchDetail">夺宝记录</a>
				<a class="layui-btn layui-btn-sm" lay-event="snatchResult">开奖记录</a>
		{{# } }}
		{{# if(d.activeStatus == 0){ }}
				<a class="layui-btn layui-btn-sm" lay-event="shelves">上架</a>
				<a class="layui-btn layui-btn-sm" lay-event="AuditFailed">未通过</a>
		{{# }else if(d.activeStatus == 1){ }}
				<a class="layui-btn layui-btn-sm" lay-event="LowerShelf">下架</a>
				<a class="layui-btn layui-btn-sm" lay-event="deleteShelves">删除</a>
		{{# }else if(d.activeStatus == 2){ }}
				<a class="layui-btn layui-btn-sm" lay-event="shelves">上架</a>
				<a class="layui-btn layui-btn-sm" lay-event="deleteShelves">删除</a>
		{{# } }}
</script>

<script type="text/html" id="statusTemp">
	{{# if(d.activeStatus == 0){ }}
		<span>待审核</span>
	{{# } }}
	{{# if(d.activeStatus == 1){ }}
		<span>上架中</span>
	{{# } }}
	{{# if(d.activeStatus == 2){ }}
		<span>下架</span>
	{{# } }}
	{{# if(d.activeStatus == 3){ }}
		<span>已删除</span>
	{{# } }}
	{{# if(d.activeStatus == 4){ }}
		<span>未通过</span>
	{{# } }}
</script>

<script type="text/html" id = "typeTemp">
	{{# if(d.type == 0){ }}
		<span>活力商品</span>
	{{# } }}
	{{# if(d.type == 1){ }}
		<span>夺宝商品</span>
	{{# } }}
	{{# if(d.type == 2){ }}
		<span>溯源商品</span>
	{{# } }}
</script>


</body>
</html>