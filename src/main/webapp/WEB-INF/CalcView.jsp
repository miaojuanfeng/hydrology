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
							            	<select name="quiz1" lay-verify="required" lay-search="">
								                <option value="站类1">站类1</option>
								                <option value="站类2">站类2</option>
								                <option value="站类3">站类3</option>
								            </select>
								        </div>
								        <div class="layui-col-xs12 layui-col-sm12 layui-col-md5">
											<select name="modules" lay-verify="required" lay-search="">
												<option value="站类1">站类1</option>
												<option value="站类2">站类2</option>
												<option value="站类3">站类3</option>
											</select>
										</div>
								    </div>
								    <div class="layui-form-item">
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md3">
							            	<label class="layui-form-label"><span>预报方案</span></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm12 layui-col-md9">
							            	<select name="quiz1" lay-verify="required" lay-search="">
								                <option value="方案1">方案1</option>
								                <option value="方案2">方案2</option>
								                <option value="方案3">方案3</option>
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
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							                <input type="text" name="startTime" class="layui-input">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>CI</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							                <input type="text" name="endTime" class="layui-input">
							            </div>
								    </div>
								    <div class="layui-form-item">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
						               		<label class="layui-form-label"><b>LAG</b></label>
						               	</div>
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							                <input type="text" name="startTime" class="layui-input">
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md2">
							            	<label class="layui-form-label"><b>CS</b></label>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							                <input type="text" name="endTime" class="layui-input">
							            </div>
								    </div>
									<!-- div class="layui-form-item">
								        <table class="layui-table" lay-skin="line">
										    <colgroup>
										    	<col width="10">
										        <col width="100">
										        <col width="100">
										        <col>
										    </colgroup>
										    <thead>
										    <tr>
										    	<th></th>
										        <th>方案名</th>
										        <th>构建人</th>
										        <th>构建时间</th>
										    </tr>
										    </thead>
										    <tbody>
										    <tr>
										    	<td><input type="radio" name="sex" value="男" checked=""></td>
										        <td>贤心</td>
										        <td>汉族</td>
										        <td>1989-10-14</td>
										    </tr>
										    <tr>
										    	<td><input type="radio" name="sex" value="男" checked=""></td>
										        <td>张爱玲</td>
										        <td>汉族</td>
										        <td>1920-09-30</td>
										    </tr>
										    <tr>
										    	<td><input type="radio" name="sex" value="男" checked=""></td>
										        <td>Helen Keller</td>
										        <td>拉丁美裔</td>
										        <td>1880-06-27</td>
										    </tr>
										    <tr>
										    	<td><input type="radio" name="sex" value="男" checked=""></td>
										        <td>岳飞</td>
										        <td>汉族</td>
										        <td>1103-北宋崇宁二年</td>
										    </tr>
										    <tr>
										    	<td><input type="radio" name="sex" value="男" checked=""></td>
										        <td>孟子</td>
										        <td>华夏族（汉族）</td>
										        <td>公元前-372年</td>
										    </tr>
										    </tbody>
										</table>
								    </div-->
									<div class="layui-form-item" style="margin-top:15px;">
						               	<div class="layui-col-xs12 layui-col-sm6 layui-col-md4 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">重置参数</a>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">开始预报</a>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">导出参数</a>
							            </div>
								    </div>
								    <div class="layui-form-item" style="margin-top:25px;">
									    <div class="layui-calc-title">
										    <span>预报结果</span>
										</div>
									</div>
								    <div class="layui-form-item">
								        <table class="layui-table">
								        	<colgroup>
										    	<col width="50">
										        <col width="50">
										    </colgroup>
										    <tr>
										        <td>洪峰时间：</td>
										        <td>2019-08-20 10:20</td>
										    </tr>
										    <tr>
										        <td>洪峰水位：</td>
										        <td>132米</td>
										    </tr>
										    <tr>
										        <td>洪峰流量：</td>
										        <td>123立方米/秒</td>
										    </tr>
										</table>
								    </div>
								    <div class="layui-form-item" style="margin-bottom:20px;">
								    	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">导出预报单</a>
							            </div>
							            <div class="layui-col-xs12 layui-col-sm6 layui-col-md6 xaj-col-button">
							                <a class="layui-btn layui-btn-primary layui-btn-radius">&nbsp;&nbsp;保存入库&nbsp;&nbsp;</a>
							            </div>
							        </div>
									<!--div class="layui-form-item">
								        <label class="layui-form-label">单选框</label>
								        <div class="layui-input-block">
								            <input type="radio" name="sex" value="男" title="男男男男男男男" checked="">
								            <input type="radio" name="sex" value="女" title="女女女女女女女">
								        </div>
								    </div>
								    <div class="layui-form-item">
								        <label class="layui-form-label">请选择省</label>
								        <div class="layui-input-inline">
								            <select name="quiz1">
								                <option value="">请选择省</option>
								                <option value="浙江" selected="">浙江省</option>
								                <option value="你的工号">江西省</option>
								                <option value="你最喜欢的老师">福建省</option>
								            </select>
								        </div>
								    </div>
								    <div class="layui-form-item">
								    	<label class="layui-form-label">请选择市</label>
								        <div class="layui-input-inline">
								            <select name="quiz2">
								                <option value="">请选择市</option>
								                <option value="杭州">杭州</option>
								                <option value="宁波" disabled="">宁波</option>
								                <option value="温州">温州</option>
								                <option value="温州">台州</option>
								                <option value="温州">绍兴</option>
								            </select>
								        </div>
								   	</div>
								   	<div class="layui-form-item">
								   		<label class="layui-form-label">请选择县/区</label>
								        <div class="layui-input-inline">
								            <select name="quiz3">
								                <option value="">请选择县/区</option>
								                <option value="西湖区">西湖区</option>
								                <option value="余杭区">余杭区</option>
								                <option value="拱墅区">临安市</option>
								            </select>
								        </div>
								    </div-->
								</form>
					            </div>
					        </div>
				    	</div>
				    
					    <div class="layui-col-xs12 layui-col-sm6 layui-col-md9">
					        <div class="layui-collapse">
					            <div class="layui-colla-item">
						            <!-- div id="div-nav" style="height:58px;border-bottom:1px solid #eee;">
									    <div style="padding:10px;">
											<a href="javascript:;">
    											<span>asdsaa</span>
    										</a>
    										<a class="selected" href="javascript:;">
    											<span>xcvxcv</span>
    										</a>
    										<a href="javascript:;">
    											<span>675675</span>
    										</a>
										</div>
									</div -->
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


        

        $(document).ready(function(){
        	var contentHeight = $(window).height() - 60 - 22;
           	var viewHeight = contentHeight;
           	$(".layui-colla-item").css("height", viewHeight);
           	$("#div-iframe").css("height", viewHeight);
           	//$("#div-iframe").css("height", viewHeight-59);
        	
           	$('#iframe7').attr('src', '<c:url value="/cms/iframe/7"></c:url>');
           	
        	$(".layui-collapse").fadeIn();
        });
    });
    
</script>
</body>
</html>