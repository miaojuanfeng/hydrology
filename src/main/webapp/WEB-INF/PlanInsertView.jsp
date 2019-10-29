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
					            		<div id="step1" class="box-step">
											<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border:none;">
											    <legend>方案基本信息</legend>
											</fieldset>
											<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">预报站 </label>
										            <div class="layui-input-block">
														<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
															<select lay-filter="sType">
																<option value="基本站">基本站</option>
																<option value="水库站">水库站</option>
															</select>
														</div>
														<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
															<select name="stcd" id="sName" lay-filter="sName">
																<c:forEach items="${stationList}" var="station" varStatus="id">
																	<option value="${station.station.stcd}">${station.station.stname}</option>
																</c:forEach>
															</select>
														</div>
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">方案名称 </label>
										            <div class="layui-input-block">
										                <input type="text" name="name" class="layui-input">
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
										            <label class="layui-form-label">预报模型 </label>
										            <div class="layui-input-block">
										                <select>
											                <option value="新安江模型">新安江模型</option>
											            </select>
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
										            <label class="layui-form-label">E = </label>
										            <div class="layui-input-block">
										                <input type="number" name="e" class="layui-input" value="${stepCommon.PE}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="wm" class="layui-input" value="${stepCommon.Ek}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WUM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="wum"  class="layui-input" value="${para.IM}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">WLM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="wlm"  class="layui-input" value="${para.XE}" >
										            </div>
										        </div>
										   	</div>
										   	<div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">K = </label>
										            <div class="layui-input-block">
										                <input type="number" name="k"  class="layui-input" value="${para.KE}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">C = </label>
										            <div class="layui-input-block">
										                <input type="number" name="c"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">B = </label>
										            <div class="layui-input-block">
										                <input type="number" name="b"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">IM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="im"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">SM = </label>
										            <div class="layui-input-block">
										                <input type="number" name="sm"  class="layui-input" value="${para.KE}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">EX = </label>
										            <div class="layui-input-block">
										                <input type="number" name="ex"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">KI = </label>
										            <div class="layui-input-block">
										                <input type="number" name="ki"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">KG = </label>
										            <div class="layui-input-block">
										                <input type="number" name="kg"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										    </div>
										    <div class="layui-form-item">
												<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">CI = </label>
										            <div class="layui-input-block">
										                <input type="number" name="ci"  class="layui-input" value="${para.KE}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">CG = </label>
										            <div class="layui-input-block">
										                <input type="number" name="cg"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">CS = </label>
										            <div class="layui-input-block">
										                <input type="number" name="cs"  class="layui-input" value="${para.DT}" >
										            </div>
										        </div>
										        <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
										            <label class="layui-form-label">LAG = </label>
										            <div class="layui-input-block">
										                <input type="number" name="lag"  class="layui-input" value="${para.DT}" >
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
														        <th>DT</th>
														      </tr> 
														    </thead>
														    <tbody>
														      <tr>
														        <td>贤心</td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="ke" index="1" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="xe" index="1" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="dt" index="1" class="layui-input" value="${para.DT}"></td>
														      </tr>
														      <tr>
														        <td>张爱玲</td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="ke" index="2" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="xe" index="2" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="dt" index="2" class="layui-input" value="${para.DT}"></td>
														      </tr>
														      <tr>
														        <td>Helen Keller</td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="ke" index="3" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="xe" index="3" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="dt" index="3" class="layui-input" value="${para.DT}"></td>
														      </tr>
														      <tr>
														        <td>岳飞</td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="ke" index="4" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="xe" index="4" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="dt" index="4" class="layui-input" value="${para.DT}"></td>
														      </tr>
														      <tr>
														        <td>孟子</td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="ke" index="5" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="xe" index="5" class="layui-input" value="${para.DT}"></td>
														        <td style="padding:0;"><input style="border:0;" type="number" name="dt" index="5" class="layui-input" value="${para.DT}"></td>
														      </tr>
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
																<input type="text" id="stcd" class="layui-input" value="宁都" readonly>
													        </div>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md12">
											            <label class="layui-form-label">方案名称 </label>
											            <div class="layui-input-block">
											                <input type="text" id="name" class="layui-input" readonly>
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
											            <label class="layui-form-label">E = </label>
											            <div class="layui-input-block">
											                <input type="text" id="e" class="layui-input" value="${stepCommon.PE}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="wm" class="layui-input" value="${stepCommon.Ek}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WUM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="wum" class="layui-input" value="${para.IM}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">WLM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="wlm" class="layui-input" value="${para.XE}" readonly>
											            </div>
											        </div>
											   	</div>
											   	<div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">K = </label>
											            <div class="layui-input-block">
											                <input type="text" id="k" class="layui-input" value="${para.KE}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">C = </label>
											            <div class="layui-input-block">
											                <input type="text" id="c" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">B = </label>
											            <div class="layui-input-block">
											                <input type="text" id="b" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">IM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="im" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">SM = </label>
											            <div class="layui-input-block">
											                <input type="text" id="sm" class="layui-input" value="${para.KE}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">EX = </label>
											            <div class="layui-input-block">
											                <input type="text" id="ex" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">KI = </label>
											            <div class="layui-input-block">
											                <input type="text" id="ki" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">KG = </label>
											            <div class="layui-input-block">
											                <input type="text" id="kg" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
													<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">CI = </label>
											            <div class="layui-input-block">
											                <input type="text" id="ci" class="layui-input" value="${para.KE}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">CG = </label>
											            <div class="layui-input-block">
											                <input type="text" id="cg" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											    </div>
											    <div class="layui-form-item">
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">CS = </label>
											            <div class="layui-input-block">
											                <input type="text" id="cs" class="layui-input" value="${para.DT}" readonly>
											            </div>
											        </div>
											        <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
											            <label class="layui-form-label">LAG = </label>
											            <div class="layui-input-block">
											                <input type="text" id="lag" class="layui-input" value="${para.DT}" readonly>
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
														        <th>DT</th>
														      </tr> 
														    </thead>
														    <tbody>
														      <tr>
														        <td>贤心</td>
														       <td style="padding:0;"><input style="border:0;" type="text" id="ke-1"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="xe-1"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="dt-1"  class="layui-input" value="${para.DT}" readonly></td>
														      </tr>
														      <tr>
														        <td>张爱玲</td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="ke-2"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="xe-2"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="dt-2"  class="layui-input" value="${para.DT}" readonly></td>
														      </tr>
														      <tr>
														        <td>Helen Keller</td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="ke-3"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="xe-3"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="dt-3"  class="layui-input" value="${para.DT}" readonly></td>
														      </tr>
														      <tr>
														        <td>岳飞</td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="ke-4"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="xe-4"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="dt-4"  class="layui-input" value="${para.DT}" readonly></td>
														      </tr>
														      <tr>
														        <td>孟子</td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="ke-5"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="xe-5"  class="layui-input" value="${para.DT}" readonly></td>
														        <td style="padding:0;"><input style="border:0;" type="text" id="dt-5"  class="layui-input" value="${para.DT}" readonly></td>
														      </tr>
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
    	    $("input").each(function () {
				if( $(this).val() == "" ){
				    ok = false;
				}
            });
    	    if( ok ) {
                $.ajax({
                    type: "post",
                    cache: false,
                    async: false,
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    dataType: "json",
                    url: "<c:url value="/cms/forecast/plan/insert"></c:url>",
                    data: $("#form-plan").serialize(),
                    success: function (data) {
                        layer.msg("保存成功", {icon: 1});
                    },
                    error: function (xhr, ts, et) {
                        layer.msg('保存失败', {icon: 2});
                    }
                });
            }else{
                layer.msg('请填妥所有信息', {icon: 2});
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