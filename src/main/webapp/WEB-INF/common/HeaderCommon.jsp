<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="layui-header my-header">
        <a class="my-header-link" href="index.html">
            <!--<img class="my-header-logo" src="" alt="logo">-->
            <div class="my-header-logo">链活2.0后台管理系统</div>
        </a>
        <!-- div class="my-header-btn">
            <button class="layui-btn layui-btn-small btn-nav"><i class="layui-icon">&#xe65f;</i></button>
        </div -->

        <!-- 顶部左侧添加选项卡监听 -->
        <ul class="layui-nav" lay-filter="side-top-left">
        	<li class="layui-nav-item"><a href="<c:url value="/cms/station/1"></c:url>"><span class="tname">首页</span></a></li>
            <li class="layui-nav-item">
            	<a href="javascript:;" class="selected"><span class="tname">预报中心</span></a>
            	<dl class="layui-nav-child">
                    <dd><a href="<c:url value="/cms/calc/index"></c:url>">新建预报</a></dd>
                    <dd><a href="<c:url value="/cms/result/1"></c:url>">预报成果</a></dd>
                    <dd><a href="<c:url value="/cms/plan/1"></c:url>">预报方案</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
            	<a href="javascript:;"><span class="tname">个人中心</span></a>
            	<dl class="layui-nav-child">
                    <dd><a href="<c:url value="/cms/user/info"></c:url>">我的账户</a></dd>
                    <dd><a href="<c:url value="/cms/user/setting"></c:url>">我的预报站</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="<c:url value="/cms/step/index"></c:url>"><span class="tname">数据分步计算</span></a></li>
            <!-- li class="layui-nav-item">
                <a href="javascript:;"><i class="layui-icon">&#xe621;</i>基础中心</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" href-url="demo/btn.html">按钮</a></dd>
                    <dd><a href="javascript:;" href-url="demo/form.html">表单</a></dd>
                </dl>
            </li-->
             
        </ul>

        <!-- 顶部右侧添加选项卡监听 -->
        <ul class="layui-nav my-header-user-nav" lay-filter="side-top-right">
            <!-- li class="layui-nav-item">
                <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>主题</a>
                <dl class="layui-nav-child">
                    <dd data-skin="0"><a href="javascript:;">默认</a></dd>
                    <dd data-skin="1"><a href="javascript:;">纯白</a></dd>
                    <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
                </dl>
            </li -->
            <li class="layui-nav-item">
            	<div id="time-week">${date}</div>
            </li>
            <li class="layui-nav-item">
			    <div id="notify" style="position:relative">
			    	<a href="javascript:;">预警消息<span class="layui-badge">9</span></a>
			    	<div id="notify-list">
			    		<ul>
			    			<li>
			    				<a href="http://www.baidu.com">
			    					<div>
			    						<div class="title">已达加报水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位200米，已达加报水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达警戒水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位300米，已达警戒水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达加报水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位200米，已达加报水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达警戒水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位300米，已达警戒水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达加报水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位200米，已达加报水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达警戒水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位300米，已达警戒水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达加报水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位200米，已达加报水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达警戒水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位300米，已达警戒水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达加报水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位200米，已达加报水位</span>
			    				</a>
			    			</li>
			    			<li>
			    				<a href="javascript:;">
			    					<div>
			    						<div class="title">已达警戒水位！</div>
			    						<span class="time">2019-07-31 10:23:23</span>
			    						<div class="clear"></div>
			    					</div>
			    					<span class="desc">宁都站即时水位300米，已达警戒水位</span>
			    				</a>
			    			</li>
			    		</ul>
			    	</div>
			    	<script type="text/javascript" src="<c:url value="/assets/static/js/jquery.min.js"></c:url>"></script>
			    	<script>
			    	$(document).ready(function(){
			    		$("#notify").click(
		    				function(){
		    					if( $("#notify-list").is(":hidden") ){
		    						$("#notify-list").stop().fadeIn().show();
		    					}else{
		    						$("#notify-list").stop().fadeIn().hide();
		    					}
				    		}
				    	);
			    	});
			    	</script>
			    	<style>
			    	.clear{
			    		float:none;
			    		clear:both;
			    	}
			    	#notify #notify-list{
			    		width:300px;
			    		background:#fff;
			    		position:absolute;
			    		margin-left:-150px;
			    		margin-top:-8px;
			    		left:52px;
			    		border:1px solid #ccc;
			    		/*border-radius: 15px;*/
			    		display:none;
			    		
			    		-moz-box-shadow:0px 0px 5px #ccc;
						-webkit-box-shadow:0px 0px 5px #ccc;
						box-shadow:0px 0px 5px #ccc;
			    	}
			    	#notify #notify-list:before{
			    		position:absolute;
					    content:"";
					    width:0;
					    height:0;
					    border:10px solid transparent;
					    border-bottom-color:#ccc;
					    top:-20px;
					    left:140px;
			    	}
			    	#notify #notify-list:after{
			    		position:absolute;
					    content:"";
					    width:0;
					    height:0;
					    border:10px solid transparent;
					    border-bottom-color:#fff;
					    top:-19px;
					    left:140px;
			    	}
			    	#notify #notify-list ul li{
			    		border-bottom:1px solid #eee;
			    		
			    	}
			    	#notify #notify-list ul a{
			    		color: #747474;
			    		line-height: 22px;
			    		padding: 0;
			    		padding:8px 10px;
			    	}
			    	#notify #notify-list ul a:hover{
			    		background:#eee;
			    	}
			    	#notify #notify-list ul a span{
			    		font-size:12px;
			    		display:block;
			    	}
			    	#notify #notify-list ul a .title{
			    		font-weight: bold;
			    		color:#ff0000;
			    		float:left;
			    	}
			    	#notify #notify-list ul a span.time{
			    		float:right;
			    		color:#aaa;
			    	}
			    	</style>
			    </div>
			</li>
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><img src="<c:url value="/assets/static/image/code.jpg"></c:url>" alt="logo"><span>预报新手 - M.J.F</span></a>
                <dl class="layui-nav-child">
                    <!-- dd><a href="javascript:;" href-url="demo/login.html"><i class="layui-icon">&#xe621;</i>登录页</a></dd>
                    <dd><a href="javascript:;" href-url="demo/map.html"><i class="layui-icon">&#xe621;</i>图表</a></dd-->
                    <dd><a href="/"><i class="layui-icon">&#x1006;</i>退出</a></dd>
                </dl>
            </li>
        </ul>

    </div>