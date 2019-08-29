<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
    <title>链活后台管理系统</title>
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
            <div class="layui-tab-content calc-content">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main calc-view" style="padding:10px;margin-top:100px;">
	                    <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
						            <form class="layui-form" action="">
						            	<div style="width:130px;height:130px;background:url(<c:url value="/assets/static/image/code.jpg"></c:url>);background-size:cover;border-radius:50%;border:2px solid #1E9FFF;position:absolute;margin-top:-90px;left:50%;margin-left:-70px;"></div>
									    <div class="layui-form-item" style="padding-top:80px;text-align:center;font-size:22px;">
						            		猪猪啊猪猪
									    </div>
									    <div class="layui-form-item" style="padding-top:30px;font-size:22px;text-align:center;">
						            		<span style="width:100px;">当前等级：</span>
						            		<span>预报新手</span>
									    </div>
									    <div class="layui-form-item" style="font-size:22px;text-align:center;">
						            		<span style="width:100px;">关注测站：</span>
						            		<span>宁都、汾坑</span>
									    </div>
									    <div class="layui-form-item" style="padding-top:30px;text-align:center;font-size:22px;">
						            		预报次数
									    </div>
									    <div class="layui-form-item" style="padding:30px 80px 0 80px;text-align:center;font-size:22px;">
										    <div class="layui-progress layui-progress-big" lay-showpercent="true">
											  <div class="layui-progress-bar layui-bg-blue" lay-percent="5 / 10"></div>
											</div>
										</div>
										<div class="layui-form-item" style="padding-top:30px;text-align:center;">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">上传头像</a>
							            </div>
									</form>
					            </div>
					        </div>
				    	</div>
				    
					    <div class="layui-col-xs12 layui-col-sm6 layui-col-md9">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
					            	<div id="user-info-box" style="margin-top:100px;">
						            	<div class="layui-col-xs12 layui-col-sm4 layui-col-md4" style="height:1px;">
							            	
							            </div>
							            <div class="layui-col-xs12 layui-col-sm4 layui-col-md4">
							            	<div style="margin-top:20px;text-align:center;font-size:20px;">
							            		修改密码
							            	</div>
							            	<div style="margin-top:20px;">
							            		<input type="password" name="startTime" class="layui-input" placeholder="原始密码">
							            	</div>
							            	<div style="margin-top:20px;">
							            		<input type="password" name="startTime" class="layui-input" placeholder="新密码">
							            	</div>
							            	<div style="margin-top:20px;">
							            		<input type="password" name="startTime" class="layui-input" placeholder="确认新密码">
							            	</div>
							            	<div style="margin-top:20px;text-align:center;">
								                <a class="layui-btn layui-btn-primary">确定</a>
								            </div>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm4 layui-col-md4" style="height:1px;">
							            	
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