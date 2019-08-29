<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
    <title>链活后台管理系统</title>
</head>
<body>

<!-- layout admin -->
<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <%@ include file="common/HeaderCommon.jsp" %>
    <!-- side -->
	<%@ include file="common/SideCommon.jsp" %>
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
                    		
	                    	<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						        <div class="layui-collapse">
						            <div class="layui-colla-item">
						            	<iframe id="iframe1" width="100%" frameborder="0" scrolling="no"></iframe>
						            </div>
						        </div>
						    </div>
						    
						    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
						        <div class="layui-collapse">
						        	<div class="layui-colla-item">
								    	<iframe id="iframe2" width="100%" frameborder="0" scrolling="no"></iframe>
									</div>
						        </div>
						    </div>
						    
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						        <div class="layui-collapse">
						            <div class="layui-colla-item">
					                    <iframe id="iframe3" width="100%" frameborder="0" scrolling="no"></iframe>
						            </div>
						        </div>
						    </div>
						    
						    <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						        <div class="layui-collapse">
						            <div class="layui-colla-item">
						                <iframe id="iframe4" width="100%" frameborder="0" scrolling="no"></iframe>
						            </div>
						        </div>
						    </div>
						    
						    <div class="layui-col-xs12 layui-col-sm6 layui-col-md8">
						        <div class="layui-collapse">
						            <div class="layui-colla-item">
						                <iframe id="iframe5" width="100%" frameborder="0" scrolling="no"></iframe>
						            </div>
						        </div>
						    </div>

					</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/assets/static/js/vip_comm.js"></c:url>"></script>
<script type="text/javascript">
	layui.config({
	    base: '<c:url value="/assets/static/js/"></c:url>'   // 模块目录
	}).extend({                         // 模块别名
	    vip_nav: 'vip_nav'
	    , vip_tab: 'vip_tab'
	    , vip_table: 'vip_table'
	});

	layui.use(['layer','vip_nav'], function () {

    // 操作对象
    var layer       = layui.layer
        ,vipNav     = layui.vip_nav
        ,$          = layui.jquery;

    // 顶部左侧菜单生成 [请求地址,过滤ID,是否展开,携带参数]
    vipNav.top_left('./json/nav_top_left.json','side-top-left',false);
    // 主体菜单生成 [请求地址,过滤ID,是否展开,携带参数]
    vipNav.main('./json/nav_main.json','side-main',true);


    
    
    
    
    
    
    
    

    $(document).ready(function(){
    	var contentHeight = $(window).height() - 60 - 34;
       	var viewHeight = contentHeight / 2;
       	$(".layui-colla-item").css("height", viewHeight);
    	
    	$('#iframe1').attr('src', '<c:url value="/cms/iframe/1?station=${station}"></c:url>');
    	$('#iframe2').attr('src', '<c:url value="/cms/iframe/2"></c:url>');
    	$('#iframe3').attr('src', '<c:url value="/cms/iframe/3"></c:url>');
    	$('#iframe4').attr('src', '<c:url value="/cms/iframe/4"></c:url>');
    	$('#iframe5').attr('src', '<c:url value="/cms/iframe/5"></c:url>');
    	
    	$(".layui-collapse").fadeIn();
    });

});
</script>
</body>
</html>