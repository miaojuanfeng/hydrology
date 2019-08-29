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
	            <input name="phoneNumber" type="text" autocomplete="off" placeholder="请输入手机号" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <select name="level">
	                <option value="">请选择真实等级</option>
	                <option value="1">青铜</option>
	                <option value="2">白银</option>
	                <option value="3">黄金</option>
	                <option value="4">铂金</option>
	                <option value="5">钻石</option>
	            </select>
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
    layui.use(['table', 'form', 'layer', 'vip_table'], function () {
    	
    	//搜索变量
    	var phoneNumber = null;

        // 操作对象
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTable = layui.vip_table
                , $ = layui.jquery;

        // 表格渲染
        var tableIns = table.render({
            elem: '#dataTable'                  //指定原始表格元素选择器（推荐id选择器）
            , height: vipTable.getFullHeight()    //容器高度
            , cols: [[                  //标题栏
                {field: 'userId', title: 'ID', width: 80}
                , {field: 'userNickName', title: '用户昵称', width: 120}
                , {field: 'userPhoneNumber', title: '用户手机号', width: 120}
                , {field: 'integral', title: '贡献值', width: 80}
                , {field: 'level', title: '真实等级', width: 100}
                , {field: 'money', title: '账显余额', width: 100}
                , {field: 'realMoney', title: '提现余额', width: 100}
                , {field: 'vtbActive', title: '活力果余额', width: 100}
                , {field: 'vtbFreeze', title: '冻果余额', width: 100}
                , {field: 'vtbUnlock', title: '活跃值', width: 100}
                , {field: 'vtbActivePick', title: '活力果采摘总数', width: 120}
                , {field: 'vtbFreezePick', title: '冻果采摘总数', width: 120}
                , {field: 'vtbUnlockTotal', title: '活跃值累积总数', width: 120}
                , {fixed: 'right', title: '操作', width: 120, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
            ]]
            , id: 'dataTable'
            , url: '<c:url value="/cms/users/asset"></c:url>'
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

     	// 搜索
        $('#btn-search').on('click', function () {
            table.reload('dataTable', {
            	page: {curr: 1}
				, where:{
					phoneNumber : $("[name='phoneNumber']").val(),
					level : $("[name='level']").val(),
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
            if(obj.event === 'edit'){
            	// you code ...
                layer.open({
	             	type: 2,
	             	skin: 'layui-layer-rim', //加上边框
	             	area: ['90%', '640px'], //宽高
	             	shadeClose: true,
	             	title: '编辑用户资产',
	             	content: '<c:url value="/cms/users/edit/asset/'+data.userId+'"></c:url>',
	             	success: function (layero, index) {
	                    // 获取子页面的iframe
	                    var iframe = window['layui-layer-iframe' + index];
	                    // 向子页面的全局函数child传参
	                    iframe.loadData(data.userId);
	                },
	                end: function(){
	                	$('#btn-search').click();
	                }
                });
            }
        });

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑资产</a>
</script>
</body>
</html>