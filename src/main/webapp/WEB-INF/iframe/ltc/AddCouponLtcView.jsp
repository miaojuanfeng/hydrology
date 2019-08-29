<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../common/HeaderCommon.jsp" %>
</head>
<body class="body">

<form class="layui-form layui-form-pane" action="post">
	<div class="layui-row">
	    <div class="layui-col-xs12 layui-col-md4">
	      	<div class="layui-form-item">
		        <label class="layui-form-label">用户手机号</label>
		        <div class="layui-input-block">
		            <input type="tel" name="phoneNumber" lay-verify="required|phone" autocomplete="off" class="layui-input">
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">树券等级</label>
		        <div class="layui-input-block">
		            <select name="ltcId" lay-verify="required">
		                <option value=""></option>
		                <option value="1">青铜</option>
		                <option value="2">白银</option>
		                <option value="3">黄金</option>
		                <option value="4">铂金</option>
		                <option value="5">钻石</option>
		            </select>
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">树券数量</label>
		        <div class="layui-input-block">
		            <input type="tel" name="ulcQty" lay-verify="required|number" autocomplete="off" class="layui-input">
		        </div>
		    </div>
	    </div>
	</div>
    
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    
</form>

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

        // 操作对象
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTable = layui.vip_table
                , $ = layui.jquery;

      	//监听提交
        form.on('submit(demo1)', function(data){
            $.ajax({
		    	  type: 'POST',
		    	  url: '<c:url value="/cms/ltc/addCoupon"></c:url>',//发送请求
		    	  data: data.field,
		    	  dataType : "html",
		    	  success: function(result) {
		    		  var obj = eval('(' + result + ')'); 
		    		  if( !obj.code ){
		    			  var index = parent.layer.getFrameIndex(window.name);
						  parent.layer.close(index);
						  parent.location.reload();
		    		  }else{
		    			  layer.msg(obj.msg);
		    		  }
		    	  }
		    });
            return false;
        });

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
</script>
</body>
</html>