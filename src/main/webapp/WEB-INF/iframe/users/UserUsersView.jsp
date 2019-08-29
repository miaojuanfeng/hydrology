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
				<input type="text" autocomplete="off" id = "phoneNumber" placeholder="请输入手机号" class="layui-input">
			</div>
			<div class="layui-input-inline">
				<input type="text" autocomplete="off" id = "nick" placeholder="请输入用户昵称" class="layui-input">
			</div>
			<div class="layui-input-inline">
				<input type="text" autocomplete="off" id = "name" placeholder="请输入用户姓名" class="layui-input">
			</div>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="createTimeRange" placeholder=" - ">
			</div>
			<a class="layui-btn btn-add btn-default" id="btn-search">查询</a>
		</span>
	    <span class="fr">
	        <a class="layui-btn btn-add btn-default" id="btn-refresh">刷新</a>
	    </span>
	</div>
</form>

<!-- 表格 -->
<div id="dataTable" lay-filter="table"></div>

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
                ,laydate = layui.laydate
                , $ = layui.jquery;

      //日期控件
		 laydate.render({
		    elem: '#createTimeRange'
		    ,range: '~'
		  });
        
        // 表格渲染
        var tableIns = table.render({
            elem: '#dataTable'                  //指定原始表格元素选择器（推荐id选择器）
            , height: vipTable.getFullHeight()    //容器高度
            , cols: [[                  //标题栏
                {field: 'userId', title: 'ID', width: 80}
                , {field: 'userNickName', title: '用户昵称', width: 120}
                , {field: 'name', title: '姓名', width: 120}
                , {field: 'parent', title: '邀请人', width: 120}
                , {field: 'userPhoneNumber', title: '用户手机号', width: 120}
                , {field: 'level', title: '等级', width: 80}
                , {field: 'sex', title: '性别', width: 80}
                , {field: 'agencyType', title: '类型', width: 100}
                , {field: 'inviteCode', title: '邀请码', width: 100}
                , {field: 'hasAuthen', title: '认证状态', width: 100}
                , {field: 'createTime', title: '注册时间', width: 180}
                , {fixed: 'right', title: '操作', width: 210, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
            ]]
            , id: 'dataTable'
            , url: '<c:url value="/cms/users/user"></c:url>'
            , method: 'post'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
               /*  console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count); */
            }
        });

        // 获取选中行
        table.on('checkbox(dataTable)', function (obj) {
            layer.msg('123');
           /*  console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one */
        });

        // 刷新
        $('#btn-refresh').on('click', function () {
        	$('form')[0].reset();
        	$('#btn-search').click();
        });

        
        $('#btn-search').on('click',function(){
			/* var title = $('productName').val();
			var type = $('#productType').val();
			var userId = $('#userSelect').val();
			var timeRange = $('#createTimeRange').val(); */
			table.reload('dataTable',{
				page: {curr: 1}
				, where:{
					phoneNumber: $('#phoneNumber').val(),
					nickName : $('#nick').val(),
					name : $('#name').val(),
					/* userFid : $('#userSelect').val(), */
					timeRange : $('#createTimeRange').val()
				}
			})
			
		});

        /*$('#btn-headUrl').on('click', function () {
        	var url = $(this).attr("url");
        	alert(url);
	        layer.open({
	       	  type: 2,
	       	  title: false,
	       	  area: ['630px', '360px'],
	       	  shade: 0.8,
	       	  closeBtn: 0,
	       	  shadeClose: true,
	       	  content: url
	       	});
        });*/
        
        table.on('tool(table)', function(obj){
            var data = obj.data;
            if(obj.event === 'IDCardDetail'){
            	// you code ...
                layer.open({
	             	type: 2,
	             	skin: 'layui-layer-rim', //加上边框
	             	area: ['90%', '640px'], //宽高
	             	shadeClose: true,
	             	title: '用户身份证',
	             	content: '<c:url value="/cms/users/IDCard"></c:url>',
	             	success: function (layero, index) {
	                    // 获取子页面的iframe
	                    var iframe = window['layui-layer-iframe' + index];
	                    // 向子页面的全局函数child传参
	                    iframe.loadData(data.userId);
	                }
                });
            }else if(obj.event === 'bankCardDetail'){
            	// you code ...
                layer.open({
	             	type: 2,
	             	skin: 'layui-layer-rim', //加上边框
	             	area: ['90%', '640px'], //宽高
	             	shadeClose: true,
	             	title: '用户银行卡',
	             	content: '<c:url value="/cms/users/bankCard"></c:url>',
	             	success: function (layero, index) {
	                    // 获取子页面的iframe
	                    var iframe = window['layui-layer-iframe' + index];
	                    // 向子页面的全局函数child传参
	                    iframe.loadData(data.userId);
	                }
                });
            }
        });

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-sm" lay-event="IDCardDetail">查看身份证</a>
	<a class="layui-btn layui-btn-sm" lay-event="bankCardDetail">查看银行卡</a>
</script>
</body>
</html>