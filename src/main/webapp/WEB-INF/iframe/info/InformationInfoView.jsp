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
	            <input name="infoContent" type="text" autocomplete="off" placeholder="请输入趣闻内容" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <select name="isHot">
	                <option value="">请选择推荐状态</option>
	                <option value="0">不推荐</option>
	                <option value="1">推荐</option>
	            </select>
	        </div>
	        <div class="layui-input-inline">
	            <select name="infoStatus">
	                <option value="">请选择趣闻状态</option>
	                <option value="0">下线</option>
	                <option value="1">上线</option>
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
                {field: 'id', title: 'ID', width: 80}
                , {field: 'userNickName', title: '用户昵称', width: 120}
                , {field: 'userPhoneNumber', title: '用户手机号', width: 120}
                , {field: 'infoContent', title: '趣闻内容', width: 620}
                , {field: 'isHot', title: '是否推荐', width: 80}
                , {field: 'infoStatus', title: '状态', width: 80}
                , {field: 'praiseNum', title: '点赞数量', width: 100}
                , {field: 'relayNum', title: '转发数量', width: 100}
                , {field: 'commentNum', title: '评论数量', width: 100}
                , {field: 'createTime', title: '创建时间', width: 180}
                , {fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#barOption'}
            ]]
            , id: 'dataTable'
            , url: '<c:url value="/cms/info/information"></c:url>'
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
					infoContent : $("[name='infoContent']").val(),
					isHot : $("[name='isHot']").val(),
					infoStatus : $("[name='infoStatus']").val(),
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
                layer.open({
	             	type: 2,
	             	skin: 'layui-layer-rim', //加上边框
	             	area: ['90%', '640px'], //宽高
	             	shadeClose: true,
	             	title: '编辑趣闻',
	             	content: '<c:url value="/cms/info/edit/info/'+data.id+'"></c:url>',
	             	success: function (layero, index) {
	                    // 获取子页面的iframe
	                    var iframe = window['layui-layer-iframe' + index];
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
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
</script>
</body>
</html>