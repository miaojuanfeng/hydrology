<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="layui-header my-header">
        <a class="my-header-link" href="javascript:;">
            <!--<img class="my-header-logo" src="" alt="logo">-->
            <div class="my-header-logo">赣州洪水预报系统青年版</div>
        </a>
        <!-- div class="my-header-btn">
            <button class="layui-btn layui-btn-small btn-nav"><i class="layui-icon">&#xe65f;</i></button>
        </div -->

        <!-- 顶部左侧添加选项卡监听 -->
        <ul class="layui-nav" lay-filter="side-top-left">
        	<li class="layui-nav-item">
                <a href="<c:url value="/cms/station"></c:url>" <c:if test="${sessionScope.urlClass == 'station'}">class="selected"</c:if>><span class="tname">首页</span></a>
            </li>
            <li class="layui-nav-item">
            	<a href="javascript:;" <c:if test="${sessionScope.urlClass == 'forecast'}">class="selected"</c:if>><span class="tname">预报中心</span></a>
            	<dl class="layui-nav-child">
                    <dd><a href="<c:url value="/cms/forecast/calc"></c:url>">新建预报</a></dd>
                    <dd><a href="<c:url value="/cms/forecast/result"></c:url>">预报成果</a></dd>
                    <dd><a href="<c:url value="/cms/forecast/plan"></c:url>">预报方案</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
            	<a href="javascript:;" <c:if test="${sessionScope.urlClass == 'user'}">class="selected"</c:if>><span class="tname">个人中心</span></a>
            	<dl class="layui-nav-child">
                    <dd><a href="<c:url value="/cms/user/info"></c:url>">我的账户</a></dd>
                    <dd><a href="<c:url value="/cms/user/setting"></c:url>">我的预报站</a></dd>
                </dl>
            </li>
        </ul>

        <!-- 顶部右侧添加选项卡监听 -->
        <ul class="layui-nav my-header-user-nav" lay-filter="side-top-right">
            <li class="layui-nav-item">
            	<div id="time-week"><iframe allowtransparency="true" frameborder="0" width="565" height="60" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=1&v=2&d=3&bd=0&k=&f=ffffff&ltf=009944&htf=cc0000&q=0&e=0&a=0&c=${station.wea}<c:if test="${empty station.wea}">57993</c:if>&w=565&h=60&align=center"></iframe></div>
            </li>
            <li class="layui-nav-item" style="margin-right: 20px;">
			    <div id="notify" style="position:relative">
			    	<a href="javascript:;">预警消息<span id="notice-count" class="layui-badge">0</span></a>
			    	<div id="notify-list">
			    		<ul id="warning-ul"></ul>
			    	</div>
			    	<script type="text/javascript" src="<c:url value="/assets/static/js/jquery.min.js"></c:url>"></script>
			    	<script>
			    	$(document).ready(function(){
                        $.post(
                            "<c:url value="/cms/common/notice"></c:url>",
                            {
                                stcd:"${stcd}"
                            },
                            function(result){
                                var obj = eval('(' + result + ')');
                                if( obj.code == 200 ){
                                    var data = obj.data;
                                    $("#notice-count").html(data.count);
                                }
                            }
                        );
			    		$("#notify").click(
		    				function(){
		    					if( $("#notify-list").is(":hidden") ){
                                    $.post(
                                        "<c:url value="/cms/common/warning"></c:url>",
										{
										    stcd:"${stcd}"
										},
										function(result){
                                        	var obj = eval('(' + result + ')');
                                        	if( obj.code == 200 ){
                                                var data = obj.data;
                                                var html = '';
                                                if( data.length > 0 ) {
                                                    for (var i = 0; i < data.length; i++) {
                                                        html += '<li>\n' +
                                                            '<a href="javascript:;">' +
                                                            '<div>' +
                                                            '<div class="title">已达' + data[i].type + '水位！</div>' +
                                                            '<span class="time">' + data[i].tm + '</span>' +
                                                            '<div class="clear"></div>' +
                                                            '</div>' +
                                                            '<span class="desc">' + data[i].stname + '站即时水位' + data[i].z + '米，已达' + data[i].type + '水位</span>' +
                                                            '</a>' +
                                                            '</li>';
                                                    }
                                                }else{
                                                    html = '<li><span class="none">暂无记录</span></li>';
                                                }
												$("#warning-ul").html(html);
                                            }
										}
                                    );
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
                    #notify #notify-list ul .none{
                        display: block;
                        color:#ff0000;
                        text-align: center;
                    }
                    </style>
			    </div>
			</li>
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><img src="<c:url value="${sessionScope.user.userHead}"></c:url>" alt="logo"><span>${sessionScope.user.userLevelName} - ${sessionScope.user.userName}</span></a>
                <dl class="layui-nav-child">
                    <!-- dd><a href="javascript:;" href-url="demo/login.html"><i class="layui-icon">&#xe621;</i>登录页</a></dd>
                    <dd><a href="javascript:;" href-url="demo/map.html"><i class="layui-icon">&#xe621;</i>图表</a></dd-->
                    <dd><a href="<c:url value="/cms/user/logout"></c:url>"><i class="layui-icon">&#x1006;</i>退出</a></dd>
                </dl>
            </li>
        </ul>

    </div>