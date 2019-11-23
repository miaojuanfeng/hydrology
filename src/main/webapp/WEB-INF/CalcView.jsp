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
					            <form id="form-forecast" class="layui-form">
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
							                <input type="text" name="forecastTime" id="forecastTime" lay-verify="date" autocomplete="off" class="layui-input">
							            </div>
							        </div>
							        <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>影响时间</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<input type="text" name="affectTime" id="affectTime" lay-verify="date" autocomplete="off" class="layui-input">
							            </div>
								    </div>
								    <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报天数</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<select name="day">
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
							            	<select name="rainfall">
								                <option value="1">实测雨量</option>
								                <option value="2">欧洲台</option>
								                <option value="3">日本台</option>
								            </select>
								        </div>
								    </div>
								    <div class="layui-form-item">
										<div class="layui-calc-title">
										    <span>参数调整</span>
											<span style="float:right;margin-top:-4px;"><a id="reset-parameter" style="display:block;width:30px;height:30px;background: url(<c:url value="/assets/static/image/reset.png"></c:url>);background-size: cover;" title="重置"></a></span>
										</div>
									</div>
									<div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						               		<label class="layui-form-label"><b>SM</b></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="number" id="SM" name="SM" class="layui-input plan-var reset-parameter" value="${plan.SM}" default="${plan.SM}">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>CI</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="number" id="CI" name="CI" class="layui-input plan-var reset-parameter" value="${plan.CI}" default="${plan.CI}">
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>CS</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="CS" name="CS" class="layui-input plan-var reset-parameter" value="${plan.CS}" default="${plan.CS}">
										</div>
								    </div>
								    <div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						               		<label class="layui-form-label"><b>L</b></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="number" id="L" name="L" class="layui-input plan-var reset-parameter" value="${plan.l}" default="${plan.l}">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>KE</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							                <input type="number" id="KE" name="KE" class="layui-input plan-var reset-parameter" value="${plan.KE}" default="${plan.KE}">
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>XE</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="XE" name="XE" class="layui-input plan-var reset-parameter" value="${plan.XE}" default="${plan.XE}">
										</div>
								    </div>
								    <div class="layui-form-item" style="margin-top:25px;">
									    <div class="layui-calc-title">
										    <span>初始状态调整</span>
											<span style="float:right;margin-top:-4px;"><a id="reset-status" style="display:block;width:30px;height:30px;background: url(<c:url value="/assets/static/image/reset.png"></c:url>);background-size: cover;" title="重置"></a></span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WU0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="WU0" name="WU0" class="layui-input plan-var reset-status" value="${plan.WU0}" default="${plan.WU0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WL0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="WL0" name="WL0" class="layui-input plan-var reset-status" value="${plan.WL0}" default="${plan.WL0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>WD0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="WD0" name="WD0" class="layui-input plan-var reset-status" value="${plan.WD0}" default="${plan.WD0}">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>S0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="S0" name="S0" class="layui-input plan-var reset-status" value="${plan.s0}" default="${plan.s0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>FR0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="FR0" name="FR0" class="layui-input plan-var reset-status" value="${plan.FR0}" default="${plan.FR0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRs0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="QRs0" name="QRs0" class="layui-input plan-var reset-status" value="${plan.QRs0}" default="${plan.QRs0}">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRss0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="QRss0" name="QRss0" class="layui-input plan-var reset-status" value="${plan.QRss0}" default="${plan.QRss0}">
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>QRg0</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="QRg0" name="QRg0" class="layui-input plan-var reset-status" value="${plan.QRg0}" default="${plan.QRg0}">
										</div>
									</div>
									<div class="layui-form-item" style="margin-bottom:20px;">
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a class="layui-btn layui-btn-primary layui-btn-radius">上一步</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a id="forecast" style="background:#FF44A5;" class="layui-btn layui-btn-normal layui-btn-radius">&nbsp;&nbsp;预报&nbsp;&nbsp;</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a class="layui-btn layui-btn-primary layui-btn-radius">下一步</a>
										</div>
									</div>
								    <div class="layui-form-item" style="margin-bottom:20px;">
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md6 xaj-col-button">
											<a class="layui-btn layui-btn-primary layui-btn-radius">导出参数</a>
										</div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md6 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">保存结果</a>
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
            elem: '#forecastTime',
            type: 'datetime'
        });
        //日期
        laydate.render({
            elem: '#affectTime',
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
                    $("#SM").val(data.SM).attr("detail", data.SM);
                    $("#CI").val(data.CI).attr("detail", data.CI);
                    $("#CS").val(data.CS).attr("detail", data.CS);
                    $("#L").val(data.L).attr("detail", data.L);
                    $("#KE").val(data.KE).attr("detail", data.KE);
                    $("#XE").val(data.XE).attr("detail", data.XE);
					$("#WU0").val(data.WU0).attr("detail", data.WU0);
                    $("#WL0").val(data.WL0).attr("detail", data.WL0);
                    $("#WD0").val(data.WD0).attr("detail", data.WD0);
                    $("#S0").val(data.S0).attr("detail", data.S0);
                    $("#FR0").val(data.FR0).attr("detail", data.FR0);
                    $("#QRs0").val(data.QRs0).attr("detail", data.QRs0);
                    $("#QRss0").val(data.QRss0).attr("detail", data.QRss0);
                    $("#QRg0").val(data.QRg0).attr("detail", data.QRg0);
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

        	$('#reset-parameter').click(function () {
				$('.reset-parameter').each(function () {
                	$(this).val($(this).attr('default'));
                });
            });

            $('#reset-status').click(function () {
                $('.reset-status').each(function () {
                    $(this).val($(this).attr('default'));
                });
            });

            $("#forecast").click(function () {
                $('#iframe7').attr('src', '<c:url value="/cms/iframe/calc?id=1"></c:url>');

                <%--var ok = true;--%>
                <%--var name  = "";--%>
                <%--$("input").each(function () {--%>
                    <%--if( $(this).attr("name") != "id" && $(this).val() == "" ){--%>
                        <%--ok = false;--%>
                        <%--name = $(this).attr("name");--%>
                    <%--}--%>
                <%--});--%>
                <%--if( ok ) {--%>
                    <%--var url = "<c:url value="/cms/iframe/calc"></c:url>";--%>
                    <%--$.ajax({--%>
                        <%--type: "post",--%>
                        <%--cache: false,--%>
                        <%--async: false,--%>
                        <%--contentType: "application/x-www-form-urlencoded;charset=utf-8",--%>
                        <%--dataType: "json",--%>
                        <%--url: url,--%>
                        <%--data: $("#form-forecast").serialize(),--%>
                        <%--success: function (data) {--%>
                            <%--$('#iframe7').attr('src', '<c:url value="/cms/iframe/7"></c:url>');--%>
                        <%--},--%>
                        <%--error: function (xhr, ts, et) {--%>
                            <%--layer.msg('计算失败', {icon: 2});--%>
                        <%--}--%>
                    <%--});--%>
                <%--}else{--%>
                    <%--layer.msg('请填妥相关信息', {icon: 2});--%>
                <%--}--%>
            });
        });
    });
    
</script>
</body>
</html>