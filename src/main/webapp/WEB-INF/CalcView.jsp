<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
</head>
<style>
.layui-layer-loading{
	margin-left: 11.5% !important;
}
</style>
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
							            	<label class="layui-form-label"><span>预报站点</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
							            	<select lay-filter="sType">
								                <option value="基本站">基本站</option>
								                <option value="水库站">水库站</option>
								            </select>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md5">
											<select id="sName" name="stcd" lay-filter="sName">
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
							            	<select id="plan" name="id" lay-filter="plan">
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
						               	<div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							                <input type="text" name="forecastTime" id="forecastTime" lay-verify="date" autocomplete="off" class="layui-input" value="${forecastTime}">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>影响时间</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<input type="text" name="affectTime" id="affectTime" lay-verify="date" autocomplete="off" class="layui-input" value="${affectTime}">
							            </div>
								    </div>
								    <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报天数</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<select name="day">
								                <option value="3">3天</option>
								                <option value="5">5天</option>
								            </select>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
								        	<label class="layui-form-label"><span>未来降雨</span></label>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<select name="unitname">
								                <option value="0">实测雨量</option>
								                <option value="2">日本台</option>
								                <option value="6">欧洲台</option>
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
							                <input type="number" id="KE" name="KE" class="layui-input plan-var reset-parameter layui-btn-disabled" value="${plan.KE}" default="${plan.KE}" disabled>
							            </div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<label class="layui-form-label"><b>XE</b></label>
										</div>
										<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
											<input type="number" id="XE" name="XE" class="layui-input plan-var reset-parameter layui-btn-disabled" value="${plan.XE}" default="${plan.XE}" disabled>
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
											<a id="step-prev" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-disabled">上一步</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a id="liuliang" type="1" style="background:#FF44A5;" class="forecastPost layui-btn layui-btn-normal layui-btn-radius layui-bg-red">预报流量</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a id="step-next" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-disabled">下一步</a>
										</div>
									</div>
								    <div class="layui-form-item" style="margin-bottom:20px;">
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a id="daochu" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-disabled">导出参数</a>
										</div>
										<div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
											<a id="shuiwei" type="2" style="background:#26D0CE;" class="forecastPost layui-btn layui-btn-normal layui-btn-radius layui-bg-green">预报水位</a>
										</div>
							            <div class="layui-col-xs12 layui-col-sm4 layui-col-md4 xaj-col-button">
							                <a id="baocun" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-disabled">保存结果</a>
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
										<div id="nav" style="padding:10px;">
											${stationProgress}
										</div>
									</div>
									<div id="div-iframe">
										<div id="div-box">
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
        
      	// //日期
        // laydate.render({
        //     elem: '#forecastTime',
        //     type: 'datetime',
			// format: 'yyyy-MM-dd HH:00:00'
        // });
        // //日期
        // laydate.render({
        //     elem: '#affectTime',
        //     type: 'datetime',
        //     format: 'yyyy-MM-dd HH:00:00'
        // });

		var stcd_ningdu = 62303350;
		var stcd_shicheng = 62303650;
		var stcd_fenkeng = 62303500;

        form.on('select(sType)', function (data) {
            postForecast = false;
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
            step = 1;
            forecastGet();
        });
        form.on('select(sType)');

        form.on('select(sName)', function (data) {
            postForecast = false;
            if( data.value != stcd_fenkeng){
                $("#step-prev").addClass("layui-btn-disabled");
                $("#step-next").addClass("layui-btn-disabled");

                $("#KE").addClass("layui-btn-disabled");
                $("#KE").attr("disabled", true);
                $("#XE").addClass("layui-btn-disabled");
                $("#XE").attr("disabled", true);
            }else{
                $("#KE").removeClass("layui-btn-disabled");
                $("#KE").attr("disabled", false);
                $("#XE").removeClass("layui-btn-disabled");
                $("#XE").attr("disabled", false);
            }
            step = 1;
            //
            getPlanList(data.value);
            //
            $("#nav").html('');
            $.post(
                "<c:url value="/cms/common/nav"></c:url>",
                {
                    stcd: data.value
                },
                function(data){
                    $("#nav").html(data);
                }
            );
            forecastGet();
        });
        form.on('select(sName)');

        form.on('select(plan)', function (data) {
            postForecast = false;
            $(".plan-var").val('');
            if( data.value != stcd_fenkeng ) {
                getPlanDetail(data.value);
            }else{
                getPlanChild(data.value, stcd_ningdu);
            }
            step = 1;
            forecastGet();
        });
        form.on('select(plan)');

        function getPlanList(id) {
            $("#plan").html('');
            $.post(
                "<c:url value="/cms/common/plan"></c:url>",
                {
                    stcd: id
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var arr = obj.data;
                    var html = '';
                    var defaultPlanId = 0;
                    for(var i=0;i<arr.length;i++){
                        if( defaultPlanId == 0 ){
                            defaultPlanId = arr[i].id;
                        }
                        html += '<option value="'+arr[i].id+'">'+arr[i].name+'</option>';
                    }
                    $("#plan").html(html);
                    layui.form.render('select');
                    //
					if( id != stcd_fenkeng ) {
                        getPlanDetail(defaultPlanId);
                    }else{
					    getPlanChild(defaultPlanId, stcd_ningdu);
					}
                }
            );
		}
        
        function getPlanDetail(id) {
            $.ajaxSettings.async = false;
            $.post(
                "<c:url value="/cms/common/plan/detail"></c:url>",
                {
                    id: id
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var data = obj.data;
                    $("#SM").val(data.SM).attr("default", data.SM);
                    $("#CI").val(data.CI).attr("default", data.CI);
                    $("#CS").val(data.CS).attr("default", data.CS);
                    $("#L").val(data.L).attr("default", data.L);
                    $("#KE").val(data.KE).attr("default", data.KE);
                    $("#XE").val(data.XE).attr("default", data.XE);
                    $("#WU0").val(data.WU0).attr("default", data.WU0);
                    $("#WL0").val(data.WL0).attr("default", data.WL0);
                    $("#WD0").val(data.WD0).attr("default", data.WD0);
                    $("#S0").val(data.S0).attr("default", data.S0);
                    $("#FR0").val(data.FR0).attr("default", data.FR0);
                    $("#QRs0").val(data.QRs0).attr("default", data.QRs0);
                    $("#QRss0").val(data.QRss0).attr("default", data.QRss0);
                    $("#QRg0").val(data.QRg0).attr("default", data.QRg0);
                }
            );
            $.ajaxSettings.async = true;
        }

        function getPlanChild(id, stcd) {
            $.ajaxSettings.async = false;
            $.post(
                "<c:url value="/cms/common/plan/child"></c:url>",
                {
                    id: id,
					stcd: stcd
                },
                function(data){
                    var obj = $.parseJSON(data);
                    var data = obj.data;
                    $("#SM").val(data.SM).attr("default", data.SM);
                    $("#CI").val(data.CI).attr("default", data.CI);
                    $("#CS").val(data.CS).attr("default", data.CS);
                    $("#L").val(data.L).attr("default", data.L);
                    $("#KE").val(data.KE).attr("default", data.KE);
                    $("#XE").val(data.XE).attr("default", data.XE);
                    $("#WU0").val(data.WU0).attr("default", data.WU0);
                    $("#WL0").val(data.WL0).attr("default", data.WL0);
                    $("#WD0").val(data.WD0).attr("default", data.WD0);
                    $("#S0").val(data.S0).attr("default", data.S0);
                    $("#FR0").val(data.FR0).attr("default", data.FR0);
                    $("#QRs0").val(data.QRs0).attr("default", data.QRs0);
                    $("#QRss0").val(data.QRss0).attr("default", data.QRss0);
                    $("#QRg0").val(data.QRg0).attr("default", data.QRg0);
                }
            );
            $.ajaxSettings.async = true;
        }

        var type = 1;
        var step = 1;
        var postForecast = false;
        $(document).ready(function(){
        	var contentHeight = $(window).height() - 60 - 22;
           	var viewHeight = contentHeight;
           	$(".layui-colla-item").css("height", viewHeight);
           	// $("#div-iframe").css("height", viewHeight);
           	$("#div-iframe").css("height", viewHeight);
            $("#div-box").css("height", viewHeight-20);

            forecastGet();
           	
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

            $(".forecastPost").click(function () {
                var ok = true;
                $("input").each(function () {
                    if( $(this).val() == "" ){
                        if( $(this).attr("name") != "KE" && $(this).attr("name") != "XE" ){
                            ok = false;
                        }
                    }
                });
                if( ok ) {
                    type = $(this).attr("type");
                    // step = 1;
                    // $("#KE,#XE").removeAttr("disabled");
                    // $("#step-prev").addClass("layui-btn-disabled");
                    forecast();
                    if( $("#sName").val() == stcd_fenkeng && step != 3 ){
						$("#step-next").removeClass("layui-btn-disabled");
                    }
                    postForecast = true;
                    $("#shuiwei").removeClass("layui-btn-disabled");
                }else{
                    layer.msg('请填妥相关信息', {icon: 2});
                }
            });
            
            $("#step-prev").click(function () {
                var stcd = $("#sName").val();
                var planId = $("#plan").val();
                if (stcd != stcd_fenkeng || step == 1 || !postForecast){
                    return;
                }
                step--;
                $("#KE,#XE").removeAttr("disabled");

                $("#step-next").removeClass("layui-btn-disabled");
                if( step == 1 ){
                    $("#step-prev").addClass("layui-btn-disabled");
                    $("#nav a").removeClass("selected");
                    $("#nav-ningdu").addClass("selected");
                    if( stcd != stcd_fenkeng ) {
                        getPlanDetail(planId);
                    }else{
                        getPlanChild(planId, stcd_ningdu);
                    }
                }else if( step == 2 ){
                    $("#nav a").removeClass("selected");
                    $("#nav-shicheng").addClass("selected");
                    if( stcd != stcd_fenkeng ) {
                        getPlanDetail(planId);
                    }else{
                        getPlanChild(planId, stcd_shicheng);
                    }
                }
                
                forecast();
                // forecastGet();
            });

            $("#step-next").click(function () {
                var stcd = $("#sName").val();
                var planId = $("#plan").val();
                if (stcd != stcd_fenkeng || step == 3 || !postForecast) {
                    return;
                }
                step++;

                $("#step-prev").removeClass("layui-btn-disabled");
                if( step == 2 ){
                    $("#nav a").removeClass("selected");
                    $("#nav-shicheng").addClass("selected");
                    if( stcd != stcd_fenkeng ) {
                        getPlanDetail(planId);
                    }else{
                        getPlanChild(planId, stcd_shicheng);
                    }
                }else if( step == 3 ){
                    $("#step-next").addClass("layui-btn-disabled");
                    $("#KE,#XE").attr("disabled", "disabled");
                    $("#nav a").removeClass("selected");
                    $("#nav-fenkeng").addClass("selected");

                    $("#daochu").removeClass("layui-btn-disabled");
                    $("#baocun").removeClass("layui-btn-disabled");
					getPlanDetail(planId);
                }

                forecast();
                // forecastGet();
            });
            
            function forecast() {
                layer.load();
                $('#div-iframe').hide();
                var url = "<c:url value="/cms/iframe/calc"></c:url>" + "?type=" + type + "&step=" + step + "&" + $("#form-forecast").serialize();
                $('#iframe7').attr('src', url);
                $('#div-iframe').show();
            }

            var iframe = document.getElementById("iframe7");
            if (iframe.attachEvent) {
                iframe.attachEvent("onload", function() {
                    layer.closeAll('loading');
                });
            } else {
                iframe.onload = function() {
                    layer.closeAll('loading');
                };
            }
        });

        function forecastGet() {
            $('#iframe7').attr('src', '<c:url value="/cms/iframe/7"></c:url>');
        }
    });
    
</script>
</body>
</html>