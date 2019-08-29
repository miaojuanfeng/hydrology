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
	            <input name="phoneNumber" type="text" autocomplete="off" placeholder="请输入邀请人手机号" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <input name="inviteePhoneNumber" type="text" autocomplete="off" placeholder="请输入被邀请人手机号" class="layui-input">
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
                {field: 'uiId', title: 'ID', width: 80}
                , {field: 'userNickName', title: '用户昵称', width: 180}
                , {field: 'userPhoneNumber', title: '用户手机号', width: 180}
                , {field: 'inviteePhoneNumber', title: '被邀请人', width: 180}
                , {field: 'createTime', title: '邀请时间', width: 180}
            ]]
            , id: 'dataTable'
            , url: '<c:url value="/cms/users/invite"></c:url>'
            , method: 'post'
            , where: {phoneNumber: phoneNumber}
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
					inviteePhoneNumber: $("[name='inviteePhoneNumber']").val(),
				}
            });
        });

        // 刷新
        $('#btn-refresh').on('click', function () {
        	$('form')[0].reset();
        	$('#btn-search').click();
        });

    });
</script>
</body>
</html>