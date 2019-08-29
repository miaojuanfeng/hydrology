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
.layui-form-pane .layui-form-checkbox {
    margin-left:0;
}
.layui-form-pane .layui-form-radio, .layui-form-pane .layui-form-switch{
	margin-left:0;
}
</style>

<form class="layui-form layui-form-pane" action="post">
	<blockquote class="layui-elem-quote layui-quote-nm">
		<div class="layui-row">
		    <textarea rows="15" name="commentContent" placeholder="请输入内容" class="layui-textarea">${infoComment.commentContent}</textarea>
		</div>
	</blockquote>
	<div class="layui-row">
	    <div class="layui-col-xs12 layui-col-md12">
	    	<div class="layui-form-item">
		        <input type="checkbox" name="delete" title="删除评论">
		    </div>
	    </div>
	</div>
	<div class="layui-row">
	    <div class="layui-col-xs12 layui-col-md12">
	    	<div class="layui-form-item">
		        <input type="checkbox" name="cmfDelete" title="确认删除">
		    </div>
	    </div>
	</div>
    <div class="layui-form-item">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
		    	  url: '<c:url value="/cms/info/edit/comment/'+${infoComment.id}+'"></c:url>',//发送请求
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