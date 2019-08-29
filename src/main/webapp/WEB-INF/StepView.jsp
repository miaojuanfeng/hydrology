<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/LinkCommon.jsp" %>
	<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
    <title>链活后台管理系统</title>
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
            <!-- ul class="layui-tab-title">
                <li class="layui-this" lay-id="1"><span><i class="layui-icon">&#xe638;</i>欢迎页</span></li>
            </ul -->
            <div class="">
                <div class="layui-tab-item layui-show">
                    <!-- iframe id="iframe" src="<c:url value="/cms/welcome"></c:url>" frameborder="0"></iframe -->
                    <div class="layui-row layui-col-space10 my-index-main" style="padding:10px">
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Veriable</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WU0 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WU0}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WL0 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WL0}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WD0 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WD0}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">QRSS = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.QRSS}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">QRG = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.QRG}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WUM = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WUM}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WLM = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WLM}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WDM = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.WDM}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">B = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.B}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">K = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.K}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">C = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.C}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">SM = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.SM}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">EX = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.EX}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">KSS = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.KSS}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">KG = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.KG}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">IM = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.IM}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">XE = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.XE}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">KE = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.KE}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">DT = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${para.DT}" readonly>
						            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					            </div>
					        </div>
					    </div>
				
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step Common</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">PE = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepCommon.PE}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Ek = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepCommon.Ek}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					    </div>
					    
					    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step One</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">W0 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.W0}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Wm = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.Wm}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">Wmm = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.Wmm}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">A = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.A}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Rd = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.Rd}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">R = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepOne.R}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					    </div>
					    
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step Two</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">PEx = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.PEx}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WUx1 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WUx1}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">PEy = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.PEy}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WLx1 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WLx1}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">PEz = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.PEz}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">WDx1 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WDx1}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">EKx = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.EKx}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WUx2 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WUx2}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">EKy = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.EKy}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WLx2 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WLx2}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">EKz = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.EKz}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">WDx2 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WDx2}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WU = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WU}" readonly>
						            <div class="layui-form-mid layui-word-aux">PE = P - Ek</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">WL = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WL}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">WD = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepTwo.WD}" readonly>
						            <div class="layui-form-mid layui-word-aux">Ek = K * E</div>
					            </div>
					        </div>
					    </div>
					    
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step Three</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">FR = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.FR}" readonly>
						            <div class="layui-form-mid layui-word-aux">
						            	<p>PE > 0</p>
						            	<p class="space">FR=R/PE</p>
						            	<p>PE <= 0</p>
						            	<p class="space">FR=1-(1-Wi/Wm)^[B/(1+B)]</p>
						            </div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">SMMF = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.SMMF}" readonly>
						            <div class="layui-form-mid layui-word-aux">SMMF=SM*(1+EX)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">AU = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.AU}" readonly>
						            <div class="layui-form-mid layui-word-aux">AU=SMMF(1-(1-Sup/SM)^[1/(1+EX)])</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Rs = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.Rs}" readonly>
					            </div>
					            <div class="layui-form-mid layui-word-aux"></div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Rss = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.Rss}" readonly>
					            </div>
					            <div class="layui-form-mid layui-word-aux"></div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					        	<label class="layui-form-label">Rg = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.Rg}" readonly>
					            </div>
					            <div class="layui-form-mid layui-word-aux"></div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">S = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.Rs}" readonly>
					            </div>
					            <div class="layui-form-mid layui-word-aux"></div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Sup = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepThree.Rss}" readonly>
					            </div>
					            <div class="layui-form-mid layui-word-aux"></div>
					        </div>
					    </div>
					    
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step Four</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">F = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.F}" readonly>
						            <div class="layui-form-mid layui-word-aux">F=Ft*(1-IMP)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qs = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qs}" readonly>
						            <div class="layui-form-mid layui-word-aux">Qs=(Rs*F+Rd*Ft*IMP)/(3.6*DT)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
								<label class="layui-form-label">Qss = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qss}" readonly>
						            <div class="layui-form-mid layui-word-aux">Qss=Cs*Qssup+(1-Cs)*Rss*F/(3.6*Dt)</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qg = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qg}" readonly>
						            <div class="layui-form-mid layui-word-aux">Qg=Cg*Qgup+(1-Cg)*Rg*F/(3.6*Dt)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qe = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qe}" readonly>
						            <div class="layui-form-mid layui-word-aux">Qe=Qs+Qss+Qg</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
								<label class="layui-form-label">Qeup = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qeup}" readonly>
						            <div class="layui-form-mid layui-word-aux"></div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qssup = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qssup}" readonly>
						            <div class="layui-form-mid layui-word-aux"></div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qgup = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFour.Qgup}" readonly>
						            <div class="layui-form-mid layui-word-aux"></div>
					            </div>
					        </div>
					    </div>
					    
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
						    <legend>Step Five</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">C0 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFive.C0}" readonly>
						            <div class="layui-form-mid layui-word-aux">C0=(0.5*DT-KE*XE)/(0.5*DT+KE-KE*XE)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">C1 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFive.C1}" readonly>
						            <div class="layui-form-mid layui-word-aux">C1=(0.5*DT+KE*XE)/(0.5*DT+KE-KE*XE)</div>
					            </div>
					        </div>
					        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
								<label class="layui-form-label">C2 = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFive.C2}" readonly>
					            	<div class="layui-form-mid layui-word-aux">C2=(-0.5*DT+KE-KE*XE)/(0.5*DT+KE-KE*XE)</div>
					            </div>
					        </div>
							<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					            <label class="layui-form-label">Qt = </label>
					            <div class="layui-input-block">
					                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" value="${stepFive.Qt}" readonly>
						            <div class="layui-form-mid layui-word-aux">Qt=C0*Qe+C1*Qeup+C2*Qeup</div>
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

    });

});
</script>
</body>
</html>