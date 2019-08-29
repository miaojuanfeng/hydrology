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

<script type="text/javascript">

	function loadData(userId){
		layui.config({
		    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
		}).extend({                         // 模块别名
		    vip_nav: 'vip_nav'
		    , vip_tab: 'vip_tab'
		    , vip_table: 'vip_table'
		});
	
	    // layui方法
	    layui.use(['table', 'form', 'layer', 'vip_table'], function () {
	
	        // 操作对象
	        var form = layui.form
	                , table = layui.table
	                , layer = layui.layer
	                , vipTable = layui.vip_table
	                , $ = layui.jquery;
	
	        // 表格渲染
	        var tableIns = table.render({
	            elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
	            , height: vipTable.getFullHeight()    //容器高度
	            , cols: [[                  //标题栏
	                {field: 'uiId', title: 'ID', width: 80}
	                , {field: 'name', title: '姓名', width: 120}
	                , {field: 'nationality', title: '民族', width: 80}
	                , {field: 'num', title: '身份证号码', width: 180}
	                , {field: 'sex', title: '性别', width: 80}
	                , {field: 'birth', title: '出生日期', width: 120}
	                , {field: 'address', title: '地址', width: 200}
	                , {field: 'startDate', title: '有效期起始时间', width: 120}
	                , {field: 'endDate', title: '有效期截止时间', width: 120}
	                , {field: 'issue', title: '签发机关', width: 120}
	                , {field: 'createTime', title: '创建时间', width: 140}
	            ]]
	            , id: 'dataCheck'
	            , url: '<c:url value="/cms/users/IDCard"></c:url>'
	            , method: 'post'
	            , where: {userId, userId}
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
	
	    });
	}
</script>
</body>
</html>