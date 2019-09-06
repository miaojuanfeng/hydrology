<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>自定义</title>
    <link rel="stylesheet" href="<c:url value="/assets/layui/css/layui.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/static/css/style.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/static/css/xaj.css"></c:url>">
    <link rel="icon" href="<c:url value="/assets/static/image/code.png"></c:url>">
</head>
<body style="background:url(<c:url value="/assets/static/image/login_bg.jpg"></c:url>);background-size:cover;">

<div class="login-main" style="width:70%;">
    <form class="layui-form" action="<c:url value="/cms/user/init"></c:url>" method="post" style="background-color:#fff;padding:50px 80px;color:#1E9F95;display:none;" id="initForm">
        <div class="layui-input-inline">
        	<p style="text-align:center;font-size:38px;margin:60px 0 50px 0;">选择你关注的预报站，自定义你的主页！</p>
        </div>
		<p style="margin-bottom:10px;text-align: center;color:#ff0000;"><c:if test="${reason != null}">${reason}</c:if></p>
        <div class="layui-input-inline">
        	<ul class="station-init">
        		<li><a class="station-a" href="javascript:;" stcd="62303350">宁都</a><input type="hidden" name="stcd"></li>
        		<li><a class="station-a" href="javascript:;" stcd="62303500">汾坑</a><input type="hidden" name="stcd"></li>
        		<li><a class="station-a" href="javascript:;" stcd="62303650">石城</a><input type="hidden" name="stcd"></li>
        		<li class="clear"></li>
        	</ul>
        	<style>
        		.station-init{
        			margin:40px 20px;
        		}
        		.station-init li{
        			float:left;
        			width:33%;
        			text-align:center;
        		}
        		.station-init li a{
        			width:200px;
        			height:200px;
        			line-height:200px;
        			margin:auto;
        			display:block;
        			border:1px solid #ccc;
        			border-radius:50%;
        			color:#1E9F95;
        			font-size:38px;
        		}
        		.station-init li a.selected{
        			background-color:#1E9FFF;
        			border-color:#1E9FFF;
        			color:#fff;
        		}
        	</style>
        	<script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
        	<script>
        	$(document).ready(function(){
        		$(".station-a").click(function(){
        			if( $(this).hasClass("selected") ){
        				$(this).removeClass("selected");
        				$(this).next("input").val("");
        			}else{
        				$(this).addClass("selected");
                        $(this).next("input").val($(this).attr("stcd"));
        			}
        		});
        		
        	});
        	</script>
        </div>
        <div class="layui-input-inline">
		    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
		    	<p style="text-align:center;margin:50px;">
		    		<a id="initButton" class="start-a" href="javascript:;" style="font-size:38px;color:#1E9F95;">
		    			开启我的预报之旅!
		    			<span style="margin-left:20px;display:inline-block;width:50px;height:35px;background:url(<c:url value="/assets/static/image/arrow.png"></c:url>);background-size:cover;"></span>
		    		</a>
		    	</p>
		    </div>
	    </div>
    </form>
</div>


<script src="<c:url value="/assets/layui/layui.js"></c:url>"></script>
<script type="text/javascript">
    layui.use(['form'], function () {

        // 操作对象
        var form = layui.form
                , $ = layui.jquery;

        // you code ...


        $(document).ready(function(){
        	var contentHeight = $(window).height();
        	var boxHeight = $(".layui-form").height()+100;
            console.log(contentHeight);
            console.log(boxHeight);
            $(".layui-form").css("marginTop", (contentHeight-boxHeight)/2).fadeIn();


			$("#initButton").click(function () {
				$("#initForm").submit();
			});
        });
    });
</script>
</body>
</html>