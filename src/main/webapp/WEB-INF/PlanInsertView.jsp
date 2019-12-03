<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <div class="">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main" style="padding:10px">
                    	<div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
					        <div class="layui-collapse">
					            <div class="layui-colla-item" style="overflow-y:auto">
					            	<div id="div-nav" style="height:58px;border-bottom:1px solid #eee;">
									    <div style="padding:10px;">
											<a id="div-nav-a-1" step="1" class="selected" href="javascript:;">
    											<span>1.基本信息</span>
    										</a>
    										<a id="div-nav-a-2" step="2" href="javascript:;">
    											<span>2.区间参数</span>
    										</a>
    										<a id="div-nav-a-3" step="3" href="javascript:;">
    											<span>3.演进参数</span>
    										</a>
    										<a id="div-nav-a-4" step="4" href="javascript:;">
    											<span>4.预览</span>
    										</a>
										</div>
									</div>
					            	<form id="form-plan" class="layui-form" style="margin:10px;">
										<input type="hidden" id="id" name="id" value="${plan.id}"/>
					            		<div id="step1" class="box-step">
											<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
											    <legend>方案基本信息</legend>
											</fieldset>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">预报站 </label>
										            <div class="layui-input-block">
														<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
															<%--<select lay-filter="sType">--%>
																<%--<option value="基本站">基本站</option>--%>
																<%--<option value="水库站">水库站</option>--%>
															<%--</select>--%>
															<input type="text" class="layui-input" value="基本站" readonly>
														</div>
														<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
															<%--<select name="stcd" id="sName" lay-filter="sName">--%>
																<%--<c:forEach items="${stationList}" var="station" varStatus="id">--%>
																	<%--<option value="${station.station.stcd}" <c:if test="${station.station.stcd == plan.stcd || station.station.stcd == stcd}">selected</c:if>>${station.station.stname}</option>--%>
																<%--</c:forEach>--%>
															<%--</select>--%>
															<input type="hidden" name="stcd" value="${station.stcd}">
															<input type="text" class="layui-input" value="${station.stname}" readonly>
														</div>
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">方案名称 </label>
										            <div class="layui-input-block">
										                <input type="text" name="name" class="layui-input" value="${plan.name}">
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">预报模型 </label>
										            <div class="layui-input-block">
										                <%--<select>--%>
											                <%--<option value="新安江模型">新安江模型</option>--%>
											            <%--</select>--%>
														<input type="text" class="layui-input" value="新安江模型" readonly>
										            </div>
										        </div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<div style="margin-left:80px;float:left;">
														<a step="2" class="div-button layui-btn layui-btn-normal layui-btn-radius">下一步</a>
													</div>
												</div>
										    </div>
										</div>
										<div id="step2" class="box-step" style="display:none;">
											<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
											    <legend>新安江模型参数</legend>
											</fieldset>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WU0 = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WU0" class="layui-input" value="${plan.WU0}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WL0 = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WL0" class="layui-input" value="${plan.WL0}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WD0 = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WD0"  class="layui-input" value="${plan.WD0}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WUM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WUM"  class="layui-input" value="${plan.WUM}" >
										            </div>
										        </div>
										   	</div>
										   	<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WLM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WLM"  class="layui-input" value="${plan.WLM}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WDM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="WDM"  class="layui-input" value="${plan.WDM}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">B = </label>
										            <div class="layui-input-block">
										                <input type="number" name="b"  class="layui-input" value="${plan.b}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">K = </label>
										            <div class="layui-input-block">
										                <input type="number" name="k"  class="layui-input" value="${plan.k}" >
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">C = </label>
										            <div class="layui-input-block">
										                <input type="number" name="c"  class="layui-input" value="${plan.c}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">SM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="SM"  class="layui-input" value="${plan.SM}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">EX = </label>
										            <div class="layui-input-block">
										                <input type="number" name="EX"  class="layui-input" value="${plan.EX}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">KSS = </label>
										            <div class="layui-input-block">
										                <input type="number" name="KSS"  class="layui-input" value="${plan.KSS}" >
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">KG = </label>
										            <div class="layui-input-block">
										                <input type="number" name="KG"  class="layui-input" value="${plan.KG}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">IM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="IM"  class="layui-input" value="${plan.IM}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">XE = </label>
										            <div class="layui-input-block">
										                <input type="number" name="XE"  class="layui-input" value="${plan.XE}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">KE = </label>
										            <div class="layui-input-block">
										                <input type="number" name="KE"  class="layui-input" value="${plan.KE}" >
										            </div>
										        </div>
											</div>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">CS = </label>
													<div class="layui-input-block">
														<input type="number" name="CS"  class="layui-input" value="${plan.CS}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">CI = </label>
													<div class="layui-input-block">
														<input type="number" name="CI"  class="layui-input" value="${plan.CI}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">CG = </label>
													<div class="layui-input-block">
														<input type="number" name="CG"  class="layui-input" value="${plan.CG}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">L = </label>
													<div class="layui-input-block">
														<input type="number" name="l"  class="layui-input" value="${plan.l}" >
													</div>
												</div>
											</div>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">T = </label>
													<div class="layui-input-block">
														<input type="number" name="t"  class="layui-input" value="${plan.t}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">F = </label>
													<div class="layui-input-block">
														<input type="number" name="f"  class="layui-input" value="${plan.f}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">S0 = </label>
													<div class="layui-input-block">
														<input type="number" name="s0"  class="layui-input" value="${plan.s0}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">FR0 = </label>
													<div class="layui-input-block">
														<input type="number" name="FR0"  class="layui-input" value="${plan.FR0}" >
													</div>
												</div>
											</div>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">QRs0 = </label>
													<div class="layui-input-block">
														<input type="number" name="QRs0"  class="layui-input" value="${plan.QRs0}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">QRss0 = </label>
													<div class="layui-input-block">
														<input type="number" name="QRss0"  class="layui-input" value="${plan.QRss0}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label">QRg0 = </label>
													<div class="layui-input-block">
														<input type="number" name="QRg0"  class="layui-input" value="${plan.QRg0}" >
													</div>
												</div>
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<label class="layui-form-label"></label>
													<div class="layui-input-block">

													</div>
												</div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<div style="margin-left:80px;float:left;">
														<a step="1" class="div-button layui-btn layui-btn-primary layui-btn-radius">上一步</a>
													</div>
											        <div style="margin-left:10px;float:right;">
											        	<a step="3" class="div-button layui-btn layui-btn-normal layui-btn-radius">下一步</a>
											        </div>
										        </div>
										    </div>
										</div>
									    <div id="step3" class="box-step" style="display:none;">
										    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
											    <legend>河道演进参数</legend>
											</fieldset>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
													<div style="margin:10px 50px;">
									            		<table class="layui-table" style="margin:0;">
														    <colgroup>
														      <col width="150">
														      <col width="150">
														      <col width="150">
														      <col width="150">
														    </colgroup>
														    <thead>
														      <tr>
														        <th>输入站</th>
														        <th>KE</th>
														        <th>XE</th>
														      </tr> 
														    </thead>
														    <tbody>
															  <c:forEach items="${qtStationList}" var="qtStation" varStatus="vs">
																<tr>
                                                                    <input type="hidden" name="qtStcd" value="${qtStation.stcd}">
																	<td>${qtStation.stname}</td>
																	<td style="padding:0;"><input style="border:0;" type="number" name="ke" index="${vs.index+1}"  class="layui-input" value="${qtStation.ke}"></td>
																	<td style="padding:0;"><input style="border:0;" type="number" name="xe" index="${vs.index+1}"  class="layui-input" value="${qtStation.xe}"></td>
																</tr>
															  </c:forEach>
															  <c:if test="${fn:length(qtStationList) == 0}">
																  <tr>
																	<td style="text-align: center" colspan="3">无</td>
																  </tr>
															  </c:if>
															</tbody>
														</table>
									            	</div>
								            	</div>
								            	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
													<div style="margin-left:80px;float:left;">
														<a step="2" class="div-button layui-btn layui-btn-primary layui-btn-radius">上一步</a>
													</div>
													<div style="margin-left:10px;float:right;">
														<a step="4" class="div-button layui-btn layui-btn-normal layui-btn-radius">下一步</a>
													</div>
										        </div>
										    </div>
									    </div>
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    
									    <div id="step4" class="box-step" style="display:none;">
											<div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
												<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
												    <legend>基本信息</legend>
												</fieldset>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md12">
											            <label class="layui-form-label">预报站 </label>
											            <div class="layui-input-block">
											            	<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
																<input type="text" class="layui-input" value="基本站" readonly>
													        </div>
													        <div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
																<input type="text" id="stcd" class="layui-input" value="${station.stname}" readonly>
													        </div>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md12">
											            <label class="layui-form-label">方案名称 </label>
											            <div class="layui-input-block">
											                <input type="text" id="name" class="layui-input" value="${plan.name}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md12">
											            <label class="layui-form-label">预报模型 </label>
											            <div class="layui-input-block">
															<input type="text" class="layui-input" value="新安江模型" readonly>
											            </div>
											        </div>
											    </div>
											</div>
											<div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
												<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
												    <legend>新安江模型参数</legend>
												</fieldset>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WU0 = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WU0" class="layui-input" value="${plan.WU0}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WL0 = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WL0" class="layui-input" value="${plan.WL0}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WD0 = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WD0" class="layui-input" value="${plan.WD0}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WUM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WUM" class="layui-input" value="${plan.WUM}" readonly>
											            </div>
											        </div>
											   	</div>
											   	<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WLM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WLM" class="layui-input" value="${plan.WLM}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WDM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="WDM" class="layui-input" value="${plan.WDM}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">B = </label>
											            <div class="layui-input-block">
											                <input type="text" id="b" class="layui-input" value="${plan.b}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">K = </label>
											            <div class="layui-input-block">
											                <input type="text" id="k" class="layui-input" value="${plan.k}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">C = </label>
											            <div class="layui-input-block">
											                <input type="text" id="c" class="layui-input" value="${plan.c}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">SM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="SM" class="layui-input" value="${plan.SM}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">EX = </label>
											            <div class="layui-input-block">
											                <input type="text" id="EX" class="layui-input" value="${plan.EX}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">KSS = </label>
											            <div class="layui-input-block">
											                <input type="text" id="KSS" class="layui-input" value="${plan.KSS}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">KG = </label>
											            <div class="layui-input-block">
											                <input type="text" id="KG" class="layui-input" value="${plan.KG}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">IM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="IM" class="layui-input" value="${plan.IM}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">XE = </label>
											            <div class="layui-input-block">
											                <input type="text" id="XE" class="layui-input" value="${plan.XE}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">KE = </label>
											            <div class="layui-input-block">
											                <input type="text" id="KE" class="layui-input" value="${plan.KE}" readonly>
											            </div>
											        </div>
											    </div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">CS = </label>
														<div class="layui-input-block">
															<input type="text" id="CS" class="layui-input" value="${plan.CS}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">CI = </label>
														<div class="layui-input-block">
															<input type="text" id="CI" class="layui-input" value="${plan.CI}" readonly>
														</div>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">CG = </label>
														<div class="layui-input-block">
															<input type="text" id="CG" class="layui-input" value="${plan.CG}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">L = </label>
														<div class="layui-input-block">
															<input type="text" id="l" class="layui-input" value="${plan.l}" readonly>
														</div>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">T = </label>
														<div class="layui-input-block">
															<input type="text" id="t" class="layui-input" value="${plan.t}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">F = </label>
														<div class="layui-input-block">
															<input type="text" id="f" class="layui-input" value="${plan.f}" readonly>
														</div>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">S0 = </label>
														<div class="layui-input-block">
															<input type="text" id="s0" class="layui-input" value="${plan.s0}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">FR0 = </label>
														<div class="layui-input-block">
															<input type="text" id="FR0" class="layui-input" value="${plan.FR0}" readonly>
														</div>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">QRs0 = </label>
														<div class="layui-input-block">
															<input type="text" id="QRs0" class="layui-input" value="${plan.QRs0}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">QRss0 = </label>
														<div class="layui-input-block">
															<input type="text" id="QRss0" class="layui-input" value="${plan.QRss0}" readonly>
														</div>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label">QRg0 = </label>
														<div class="layui-input-block">
															<input type="text" id="QRg0" class="layui-input" value="${plan.QRg0}" readonly>
														</div>
													</div>
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
														<label class="layui-form-label"></label>
														<div class="layui-input-block">

														</div>
													</div>
												</div>
											    <div class="layui-form-item">
											    	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6" style="height:1px;">
											    	
											    	</div>
											    	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6" style="text-align:center;padding-top:50px;">
														<div style="margin-left:0px;float:left;">
															<a step="3" class="div-button layui-btn layui-btn-primary layui-btn-radius">上一步</a>
														</div>
														<div style="margin-left:10px;float:right;">
															<a id="save" class="layui-btn layui-btn-normal layui-btn-radius">保存</a>
														</div>
											        </div>
											    </div>
											</div>
											<div class="layui-col-xs12 layui-col-sm12 layui-col-md5" style="padding-left:50px;">
												<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
												    <legend style="margin-left:0;padding-left:0;">河道演进参数</legend>
												</fieldset>
												<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md12">
									            		<table class="layui-table" style="margin:0;">
														    <colgroup>
														      <col width="150">
														      <col width="150">
														      <col width="150">
														      <col width="150">
														    </colgroup>
														    <thead>
														      <tr>
														        <th>输入站</th>
														        <th>KE</th>
														        <th>XE</th>
														      </tr> 
														    </thead>
														    <tbody>
															  <c:forEach items="${qtStationList}" var="qtStation" varStatus="vs">
																  <tr>
																	  <td>${qtStation.stname}</td>
																	  <td style="padding:0;"><input style="border:0;" type="number" id="ke-${vs.index+1}"  class="layui-input" value="${qtStation.ke}" readonly></td>
																	  <td style="padding:0;"><input style="border:0;" type="number" id="xe-${vs.index+1}"  class="layui-input" value="${qtStation.xe}" readonly></td>
																  </tr>
															  </c:forEach>
															  <c:if test="${fn:length(qtStationList) == 0}">
																  <tr>
																	  <td style="text-align: center" colspan="3">无</td>
																  </tr>
															  </c:if>
															</tbody>
														</table>
									            	</div>
											    </div>
											</div>
									    </div>
									</form>
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

	layui.use(['form','layer','table','vip_nav'], function () {

    // 操作对象
    var form 		= layui.form
    	,layer      = layui.layer
    	,table		= layui.table
        ,vipNav     = layui.vip_nav
        ,$          = layui.jquery;

    
    table.render({
        elem: '#table'
        ,url:'<c:url value="/cms/forecast/plan/insert"></c:url>'
        ,height: 'full-350'
        ,cols: [[
          {field:'id', width:80, title: '序号', sort: true, align: 'center'}
          ,{field:'name', width:280, title: '站名', align: 'center'}
          ,{field:'peakTime', width:280, title: '洪峰时间', align: 'center'}
          ,{field:'peakFlow', width:280, title: '洪峰流量', align: 'center'}
          ,{field:'username', width:280, title: '预报员', align: 'center'}
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

	form.on('select(sName)', function(data){
		var value = data.value;
		if( value == 62303350 ){
            $("#stcd").val("宁都");
		}else if( value == 62303500 ){
            $("#stcd").val("汾坑");
		}else if( value == 62303650 ){
            $("#stcd").val("石城");
        }
	});

    $(document).ready(function(){
    	var contentHeight = $(window).height() - 60 - 22;
       	var viewHeight = contentHeight;
       	$(".layui-colla-item").css("height", viewHeight);
    	
    	$(".layui-collapse").fadeIn();
    	
    	
    	$("#div-nav a, .div-button").click(function(){
    		var step = $(this).attr("step");
    		$("#div-nav a").removeClass("selected");
    		$("#div-nav-a-"+step).addClass("selected");
    		$(".box-step").hide();
    		$("#step"+step).show();
    	});

    	$("#save").click(function () {
    	    var ok = true;
    	    var name  = "";
    	    $("input").each(function () {
				if( $(this).attr("name") != "id" && $(this).val() == "" ){
				    ok = false;
				    name = $(this).attr("name");
				}
            });
    	    if( ok ) {
    	        var id = $("#id").val();
    	        var url = "<c:url value="/cms/forecast/plan/insert"></c:url>";
    	        if( id != '' ){
					url = "<c:url value="/cms/forecast/plan/update"></c:url>";
				}
                $.ajax({
                    type: "post",
                    cache: false,
                    async: false,
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    dataType: "json",
                    url: url,
                    data: $("#form-plan").serialize(),
                    success: function (data) {
                        layer.msg("保存成功", {icon: 1}, function(){
                            window.location.href="<c:url value="/cms/forecast/plan/${stcd}"></c:url>";
						});
                    },
                    error: function (xhr, ts, et) {
                        layer.msg('保存失败', {icon: 2});
                    }
                });
            }else{
                layer.msg('请填写'+name, {icon: 2});
			}
        });

    	$("input, select").on("change", function () {
			var value = $(this).val();
			var id = $(this).attr("name");
			var index = $(this).attr("index");
			if( index != undefined ){
                $("#"+id+"-"+index).val(value);
            }else {
                $("#"+id).val(value);
            }
		});
    });

});
</script>
<style>
.layui-form-item{
	margin-bottom:30px;
}
</style>
</body>
</html>