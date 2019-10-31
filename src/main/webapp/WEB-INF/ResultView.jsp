<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
</head>
<body>

<!-- layout admin -->
<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <%@ include file="common/HeaderCommon.jsp" %>
    <!-- side -->
	<%@ include file="common/ResultSideCommon.jsp" %>
    <!-- body -->
    <div class="layui-body my-body my-body-left">
        <div class="layui-tab my-tab" lay-filter="card" lay-allowClose="true">
            <!-- ul class="layui-tab-title">
                <li class="layui-this" lay-id="1"><span><i class="layui-icon">&#xe638;</i>欢迎页</span></li>
            </ul -->
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main" style="padding:10px;">
                    		
                    	<div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
					            	<div class="layui-form-item" style="text-align:center;font-size:24px;margin-top:5px;font-style:italic;">
						               	历史预报成果
					            	</div>
					            	<div class="layui-form-item" style="margin-bottom:10px;">
					            		<form class="layui-form" style="float:right">
							               	<div class="layui-inline" style="width:330px;">
												<div class="layui-input-inline" style="margin-right:20px;width:320px;">
												  	<input type="text" name="time" id="time" autocomplete="off" class="layui-input" placeholder="预报时间">
												</div>
										    </div>
										    <div class="layui-inline">
								                <a class="layui-btn layui-btn-primary layui-btn-radius">查询</a>
								            </div>
								    	</form>
						            </div>
					            	
					            	<div style="margin:10px;">
					            		<table class="layui-hide" id="table"></table>
					            	</div>
					            </div>
					        </div>
					    </div>
					    
					</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="edit">查看</a>
  <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">删除</a>
</script>

<!-- script type="text/javascript" src="<c:url value="/assets/static/js/vip_comm.js"></c:url>"></script-->
<script type="text/javascript">
	layui.config({
	    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
	}).extend({                         // 模块别名
	    vip_nav: 'vip_nav'
	    , vip_tab: 'vip_tab'
	    , vip_table: 'vip_table'
	});

	layui.use(['layer','vip_nav', 'table', 'laydate'], function () {

    // 操作对象
    var layer       = layui.layer
        ,vipNav     = layui.vip_nav
        ,table		= layui.table
        ,$          = layui.jquery
        , laydate 	= layui.laydate;
    
  	//日期时间
    laydate.render({
	    elem: '#time'
	    ,type: 'datetime'
	    ,range: true
	});

    table.render({
        elem: '#table'
        ,url:'<c:url value="/cms/result/data"></c:url>'
        ,height: 'full-198'
        ,cols: [[
          {field:'id', width:'10%', title: '序号', sort: true, align: 'center'}
          ,{field:'name', width:'15%', title: '站名', align: 'center'}
          ,{field:'peakTime', width:'15%', title: '洪峰时间', align: 'center'}
          ,{field:'peakFlow', width:'15%', title: '洪峰流量', align: 'center'}
          ,{field:'username', width:'15%', title: '预报员', align: 'center'}
          ,{field:'time', title: '预报时间', align: 'center'}
          ,{fixed:'right', title:'操作', width:150, toolbar: '#barDemo', align: 'center'}
        ]]
        ,page: true
   });

    table.on('tool(table)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
          layer.confirm('真的删除行么', function(index){
            obj.del();
            layer.close(index);
          });
        } else if(obj.event === 'edit'){
          layer.prompt({
            formType: 2
            ,value: data.email
          }, function(value, index){
            obj.update({
              email: value
            });
            layer.close(index);
          });
        }
      });

    
    
    
    
    
    
    
    

    $(document).ready(function(){
    	var contentHeight = $(window).height() - 60 - 22;
       	var viewHeight = contentHeight;
       	$(".layui-colla-item").css("height", viewHeight);
    	
    	$(".layui-collapse").fadeIn();
    });

});
</script>
</body>
</html>