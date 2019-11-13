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
   
    <!-- body -->
    <div class="layui-body my-body">
        <div class="layui-tab my-tab" lay-filter="card" lay-allowClose="true">
            <div class="layui-tab-content calc-content">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main calc-view" style="padding:10px">
	                    <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        <div class="layui-collapse">
					            <div class="layui-colla-item" style="overflow-y:auto">
					            <form class="layui-form" action="">
					            	<div class="layui-form-item">
										<div class="layui-calc-title">
										    <span>基本设置</span>
										</div>
									</div>
						            <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报站</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
							            	<select lay-filter="sType">
								                <option value="基本站">基本站</option>
								                <option value="水库站">水库站</option>
								            </select>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md5">
											<select lay-filter="sName" id="sName">
												<c:forEach items="${stationList}" var="station" varStatus="id">
													<option value="${station.userStcd}">${station.stname}</option>
												</c:forEach>
											</select>
										</div>
								    </div>
								    <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报方案</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<select id="plan" name="plan" lay-filter="plan">
												<c:forEach items="${planList}" var="plan" varStatus="id">
													<option value="${plan.id}">${plan.name}</option>
												</c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
						               		<label class="layui-form-label"><span>预报时间</span></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							                <input type="text" name="startTime" id="startTime" lay-verify="date" autocomplete="off" class="layui-input">
							            </div>
							        </div>
							        <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>影响时间</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<input type="text" name="endTime" id="endTime" lay-verify="date" autocomplete="off" class="layui-input">
							            </div>
								    </div>
								    <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报天数</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<select name="quiz1">
								                <option value="3">3天</option>
								                <option value="5">5天</option>
								            </select>
								        </div>
								   	</div>
								    <div class="layui-form-item">
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
								        	<label class="layui-form-label"><span>预报雨量</span></label>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<select name="quiz1">
								                <option value="实测雨量">实测雨量</option>
								                <option value="欧洲台">欧洲台</option>
								                <option value="日本台">日本台</option>
								            </select>
								        </div>
								    </div>
								    <div class="layui-form-item">
										<div class="layui-calc-title">
										    <span>参数调整</span>
										</div>
									</div>
									<div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						               		<label class="layui-form-label"><b>SM</b></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="text" id="SM" name="SM" class="layui-input plan-var" value="${plan.SM}">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>CI</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="text" id="CI" name="CI" class="layui-input plan-var" value="${plan.CI}">
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>CS</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="CS" name="CS" class="layui-input plan-var" value="${plan.CS}">
										</div>
								    </div>
								    <div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						               		<label class="layui-form-label"><b>L</b></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="text" id="L" name="L" class="layui-input plan-var" value="${plan.l}">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>KE</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="text" id="KE" name="KE" class="layui-input plan-var" value="${plan.KE}">
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>XE</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="XE" name="XE" class="layui-input plan-var" value="${plan.XE}">
										</div>
								    </div>
								    <div class="layui-form-item" style="margin-top:25px;">
									    <div class="layui-calc-title">
										    <span>初始状态调整</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WU0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="WU0" name="WU0" class="layui-input plan-var" value="${plan.WU0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WL0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="WL0" name="WL0" class="layui-input plan-var" value="${plan.WL0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WD0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="WD0" name="WD0" class="layui-input plan-var" value="${plan.WD0}">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>S0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="S0" name="S0" class="layui-input plan-var" value="${plan.s0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>FR0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="FR0" name="FR0" class="layui-input plan-var" value="${plan.FR0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRs0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="QRs0" name="QRs0" class="layui-input plan-var" value="${plan.QRs0}">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRss0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="QRss0" name="QRss0" class="layui-input plan-var" value="${plan.QRss0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRg0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="text" id="QRg0" name="QRg0" class="layui-input plan-var" value="${plan.QRg0}">
										</div>
									</div>
								    <div class="layui-form-item" style="margin-bottom:20px;">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md3 xaj-col-button">
											<a class="layui-btn layui-btn-primary layui-btn-radius">重置</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md3 xaj-col-button">
											<a class="layui-btn layui-btn-primary layui-btn-radius">导出</a>
										</div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md3 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">保存</a>
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md3 xaj-col-button">
											<a class="layui-btn layui-btn-normal layui-btn-radius">预报</a>
										</div>
							        </div>
								</form>
					            </div>
					        </div>
				    	</div>
				    
					    <div class="layui-col-xs12 layui-col-sm6 layui-col-md9">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
						            <div id="div-nav" style="height:58px;border-bottom:1px solid #eee;">
									    <div style="padding:10px;">
											<a class="selected" href="javascript:;">
    											<span>宁都</span>
    										</a>
    										<a href="javascript:;">
    											<span>石城</span>
    										</a>
    										<a href="javascript:;">
    											<span>汾坑</span>
    										</a>
										</div>
									</div>
									<div id="div-iframe">
					             		<iframe id="iframe7" width="100%" frameborder="0" scrolling="no"></iframe>
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

        form.on('select(sType)', function (data) {
            $("#sName").html('');
            $.post(
                "<c:url value="/cms/common/station"></c:url>",
                {
                    type: data.value
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var arr = obj.data;
                    var html = '';
                    for(var i=0;i<arr.length;i++){
                        html += '<option value="'+arr[i].stcd+'">'+arr[i].stname+'</option>';
                    }
                    $("#sName").html(html);
                    layui.form.render('select');
                }
            );
        });
        form.on('select(sType)');

        form.on('select(sName)', function (data) {
            $("#plan").html('');
            $.post(
                "<c:url value="/cms/common/plan"></c:url>",
                {
                    stcd: data.value
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var arr = obj.data;
                    var html = '';
                    for(var i=0;i<arr.length;i++){
                        html += '<option value="'+arr[i].id+'">'+arr[i].name+'</option>';
                    }
                    $("#plan").html(html);
                    layui.form.render('select');
                }
            );
        });
        form.on('select(sName)');

        form.on('select(plan)', function (data) {
            $(".plan-var").val('');
            $.post(
                "<c:url value="/cms/common/plan/detail"></c:url>",
                {
                    id: data.value
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var data = obj.data;
                    $("#SM").val(data.SM);
                    $("#CI").val(data.CI);
                    $("#CS").val(data.CS);
                    $("#L").val(data.L);
                    $("#KE").val(data.KE);
                    $("#XE").val(data.XE);
					$("#WU0").val(data.WU0);
                    $("#WL0").val(data.WL0);
                    $("#WD0").val(data.WD0);
                    $("#S0").val(data.S0);
                    $("#FR0").val(data.FR0);
                    $("#QRs0").val(data.QRs0);
                    $("#QRss0").val(data.QRss0);
                    $("#QRg0").val(data.QRg0);
                }
            );
        });
        form.on('select(sName)');

        $(document).ready(function(){
        	var contentHeight = $(window).height() - 60 - 22;
           	var viewHeight = contentHeight;
           	$(".layui-colla-item").css("height", viewHeight);
           	// $("#div-iframe").css("height", viewHeight);
           	$("#div-iframe").css("height", viewHeight-59);
        	
           	$('#iframe7').attr('src', '<c:url value="/cms/iframe/7"></c:url>');
           	
        	$(".layui-collapse").fadeIn();
        });
    });
    
</script>
</body>
</html>