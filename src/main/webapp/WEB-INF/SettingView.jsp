<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
</head>
<body style="background:url(<c:url value="/assets/static/image/bg.jpg"></c:url>);">

<!-- layout admin -->
<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <%@ include file="common/HeaderCommon.jsp" %>
    <!-- side -->
   
    <!-- body -->
    <div class="layui-body my-body">
        <div class="layui-tab my-tab" lay-filter="card" lay-allowClose="true">
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main calc-view" style="padding:10px;margin-top:100px">
						<%@ include file="common/UserCommon.jsp" %>
				    
					    <div class="layui-col-xs12 layui-col-sm6 layui-col-md9">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
					            	<div class="layui-form-item" style="text-align:center;font-size:24px;margin-top:5px;font-style:italic;">
						               	选择关注的预报站
					            	</div>
					            	<div class="layui-form-item" style="margin-bottom:10px;">
										<%--<form class="layui-form" style="float:left;margin-left:10px;">--%>
											<%--<div class="layui-inline">--%>
												<%--<a class="layui-btn layui-btn-normal layui-btn-radius">保存</a>--%>
											<%--</div>--%>
										<%--</form>--%>
					            		<form class="layui-form" style="float:right">
										    <div class="layui-inline">
												<div class="layui-input-inline" style="margin-right:0">
												  	<select name="quiz1" lay-verify="required" lay-search="">
												  		<option value="">全部行政区</option>
										                <option value="赣州市章贡区">赣州市章贡区</option>
										            </select>
												</div>
										    </div>
										    <div class="layui-inline">
								                <a class="layui-btn layui-btn-primary layui-btn-radius">查询</a>
								            </div>
								    	</form>
						            </div>
						            <div style="margin:10px;">
					            		<table class="layui-hide" id="table" lay-filter="setting"></table>
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
</div>

<!-- script type="text/javascript" src="<c:url value="/assets/static/js/vip_comm.js"></c:url>"></script-->
<script type="text/javascript">
	layui.config({
	    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
	}).extend({                         // 模块别名
	    vip_nav: 'vip_nav'
	    , vip_tab: 'vip_tab'
	    , vip_table: 'vip_table'
	});
	
    layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , element = layui.element
                , $ = layui.jquery
                , laydate = layui.laydate;
        
      	//日期
        laydate.render({
            elem: '#startTime',
            type: 'datetime'
        });
      //日期
        laydate.render({
            elem: '#endTime',
            type: 'datetime'
        });


        table.render({
            elem: '#table'
            ,url:'<c:url value="/cms/user/setting"></c:url>'
            ,height: 'full-408'
            ,method:'post'
            ,cols: [[
              {type:'checkbox'}
              ,{field:'id', width:'10%', title: '序号', sort: true, align: 'center'}
              ,{field:'stcd', width:'22%', title: '站码', align: 'center'}
              ,{field:'stname', width:'22%', title: '站名', align: 'center'}
              ,{field:'type', width:'22%', title: '站点类型', align: 'center'}
              ,{field:'area', title: '行政区', align: 'center'}
            ]]
            ,page: true
       });

        table.on('checkbox(setting)', function(obj){
            var checkStatus = table.checkStatus('table');
            var data = checkStatus.data;
            var stcd = "";
            for(var i=0; i<data.length; i++){
                if( stcd == "" ){
                    stcd = data[i].stcd;
                }else{
                    stcd = stcd + "," + data[i].stcd;
                }
			}
			if( stcd != "" ) {
                $.ajax({
                    type: "post",
                    cache: false,
                    async: false,
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    dataType: "json",
                    url: "<c:url value="/cms/user/setting/update"></c:url>",
                    data: {
                        stcds: stcd
                    },
                    success: function (data) {
                        layer.msg("保存成功", {icon: 1});
                    },
                    error: function (xhr, ts, et) {
                        layer.msg('保存失败', {icon: 2});
                    }
                });
            }else{
                layer.msg('请至少选择一个站', {icon: 2});
            }
            console.log(stcd);
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
           	$(".layui-colla-item").css("height", viewHeight-105-105);
           	
        	$(".layui-collapse").fadeIn();
        });
    });
    
</script>
</body>
</html>