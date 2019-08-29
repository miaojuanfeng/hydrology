<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../common/HeaderCommon.jsp" %>
</head>
<body class="body">

<!-- 工具集 -->
<form class="layui-form layui-form-pane">
	<div class="my-btn-box">
	    <span class="fl">
	        <div class="layui-input-inline">
	            <input name="userId" type="text" autocomplete="off" placeholder="请输入用户ID" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <input name="ulId" type="text" autocomplete="off" placeholder="请输入矿机ID" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <input name="date" type="text" class="layui-input" placeholder="请选择日期">
	        </div>
	        <a class="layui-btn btn-add btn-default" id="btn-search">查询</a>
	    </span>
	    <span class="fr">
	        <a class="layui-btn btn-add btn-default" id="btn-refresh">刷新</a>
	    </span>
	</div>
</form>

<!-- 表格 -->
<div id="dataTable"></div>

<script type="text/javascript">

	layui.config({
	    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
	}).extend({                         // 模块别名
	    vip_nav: 'vip_nav'
	    , vip_tab: 'vip_tab'
	    , vip_table: 'vip_table'
	});

    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table', 'laydate'], function () {

        // 操作对象
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTable = layui.vip_table
                , laydate = layui.laydate
                , $ = layui.jquery;

        // 表格渲染
        var tableIns = table.render({
            elem: '#dataTable'                  //指定原始表格元素选择器（推荐id选择器）
            , height: vipTable.getFullHeight()    //容器高度
            , cols: [[                  //标题栏
                {field: 'ulId', title: 'ID', width: 80}
                , {field: 'userId', title: '用户ID', width: 80}
                , {field: 'userLtcType', title: '生长阶段', width: 120}
                , {field: 'userLtcYield', title: '总产量', width: 120}
                , {field: 'userLtcOutput', title: '今日产量', width: 120}
                , {fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#barOption'}
            ]]
            , id: 'dataTable'
            , url: '<c:url value="/cms/ltc/quartz"></c:url>'
            , method: 'post'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }
        });
        
        laydate.render({
            elem: '[name="date"]'
        });
        
        // 搜索
        $('#btn-search').on('click', function () {
            table.reload('dataTable', {
            	page: {curr: 1}
				, where:{
					userId : $("[name='userId']").val(),
					ulId : $("[name='ulId']").val(),
					date : $("[name='date']").val(),
				}
            });
        });

        // 刷新
        $('#btn-refresh').on('click', function () {
        	$('form')[0].reset();
        	$('#btn-search').click();
        });
        
        table.on('tool', function(obj){
            var data = obj.data;
            if(obj.event === 'fruitProduction'){
                layer.open({
	             	type: 2,
	             	skin: 'layui-layer-rim', //加上边框
	             	area: ['90%', '640px'], //宽高
	             	shadeClose: true,
	             	title: '产果记录',
	             	content: '<c:url value="/cms/ltc/fruitProduction"></c:url>',
	             	success: function (layero, index) {
	                    // 获取子页面的iframe
	                    var iframe = window['layui-layer-iframe' + index];
	                    // 向子页面的全局函数child传参
	                    iframe.loadData(data.ulId, $("[name='date']").val());
	                }
                });
            }
        });

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-sm" lay-event="fruitProduction">查看果子</a>
</script>
</body>
</html>