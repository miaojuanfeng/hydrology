<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../../common/HeaderCommon.jsp" %>
</head>
<body class="body">
<style>
.layui-elem-quote .layui-form-item{
	margin-bottom:0;
}
</style>

<form class="layui-form layui-form-pane" action="post">
	<blockquote class="layui-elem-quote layui-quote-nm">
		<p>用户基本信息</p>
		<hr>
		<div class="layui-row">
		    <div class="layui-col-xs12 layui-col-md4">
		      	<div class="layui-form-item">
			        <label class="layui-form-label layui-bg-green">用户ID</label>
			        <div class="layui-input-block">
			            <input type="tel" name="userId" lay-verify="required|number" autocomplete="off" class="layui-input" readonly value="${userAsset.userId}">
			        </div>
			    </div>
		    </div>
		    <div class="layui-col-xs12 layui-col-md4">
		    	<div class="layui-form-item">
			        <label class="layui-form-label layui-bg-green">用户昵称</label>
			        <div class="layui-input-block">
			            <input type="tel" name="nickName" lay-verify="required" autocomplete="off" class="layui-input" disabled value="${userAsset.users.nickName}">
			        </div>
			    </div>
		    </div>
		    <div class="layui-col-xs12 layui-col-md4">
		    	<div class="layui-form-item">
			        <label class="layui-form-label layui-bg-green">用户手机号</label>
			        <div class="layui-input-block">
			            <input type="tel" name="phoneNumber" lay-verify="required|number" autocomplete="off" class="layui-input" disabled value="${userAsset.users.phoneNumber}">
			        </div>
			    </div>
		    </div>
		</div>
	</blockquote>
    <div class="layui-row">
	    <div class="layui-col-xs12 layui-col-md4">
	      	<div class="layui-form-item">
		        <label class="layui-form-label">修改贡献值</label>
		        <div class="layui-input-block">
		            <input type="tel" name="integral" lay-verify="required|number" autocomplete="off" class="layui-input layui-number-update-tips" value="0">
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">真实等级</label>
		        <div class="layui-input-block">
		            <select name="level" lay-verify="required">
		                <option value="1" <c:if test="${userAsset.level == 1}">selected</c:if>>青铜</option>
		                <option value="2" <c:if test="${userAsset.level == 2}">selected</c:if>>白银</option>
		                <option value="3" <c:if test="${userAsset.level == 3}">selected</c:if>>黄金</option>
		                <option value="4" <c:if test="${userAsset.level == 4}">selected</c:if>>铂金</option>
		                <option value="5" <c:if test="${userAsset.level == 5}">selected</c:if>>钻石</option>
		            </select>
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">修改余额</label>
		        <div class="layui-input-block">
		        	<input type="tel" name="money" lay-verify="required|number" autocomplete="off" class="layui-input layui-number-update-tips" value="0">
		        </div>
		    </div>
	    </div>
	</div>
	<div class="layui-row">
	    <div class="layui-col-xs12 layui-col-md4">
	      	<div class="layui-form-item">
		        <label class="layui-form-label">修改活力果</label>
		        <div class="layui-input-block">
		            <input type="tel" name="vtbActive" lay-verify="required|number" autocomplete="off" class="layui-input layui-number-update-tips" value="0">
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">修改冻果</label>
		        <div class="layui-input-block">
		            <input type="tel" name="vtbFreeze" lay-verify="required|number" autocomplete="off" class="layui-input layui-number-update-tips" value="0">
		        </div>
		    </div>
	    </div>
	    <div class="layui-col-xs12 layui-col-md4">
	    	<div class="layui-form-item">
		        <label class="layui-form-label">修改活跃值</label>
		        <div class="layui-input-block">
		        	<input type="tel" name="vtbUnlock" lay-verify="required|number" autocomplete="off" class="layui-input layui-number-update-tips" value="0">
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
		    	  url: '<c:url value="/cms/users/edit/asset/'+data.field.userId+'"></c:url>',//发送请求
		    	  data: data.field,
		    	  dataType : "html",
		    	  success: function(result) {
		    		  var obj = eval('(' + result + ')'); 
		    		  if( !obj.code ){
		    			  var index = parent.layer.getFrameIndex(window.name);
						  parent.layer.close(index);
						  //parent.location.reload();
		    		  }else{
		    			  layer.msg(obj.msg);
		    		  }
		    	  }
		    });
            return false;
        });


        // you code ...
        $(".layui-number-update-tips").on("focus", function(){
        	layer.tips('增加数值请输入正数，减少数值请输入负数。', $(this), {
       		  	tips: [2, '#3595CC'],
       		  	time: 4000
       		});
        });

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
</script>
</body>
</html>