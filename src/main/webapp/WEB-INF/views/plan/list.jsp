<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>方案列表</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>

<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md2">
      <div class="layui-card layui-form">
        <div class="layui-card-header">搜索方案</div>
        <form class="layui-form" action="" lay-filter="component-form-group">
          <div class="layui-card-body layui-row">
            <select name="modules" lay-verify="required" lay-search="">
              <option value="">站点类型</option>
              <option value="1">河道站</option>
              <option value="2">水库站</option>
            </select>
          </div>
          <div class="layui-card-body layui-row">
            <select name="modules" lay-verify="required" lay-search="">
              <option value="">站点区域</option>
              <option value="1">宁都</option>
              <option value="2">石城</option>
              <option value="2">汾坑</option>
            </select>
          </div>
          <div class="layui-card-body layui-row">
            <select name="modules" lay-verify="required" lay-search="">
              <option value="">站点名称</option>
              <option value="1">宁都</option>
              <option value="2">石城</option>
              <option value="2">汾坑</option>
            </select>
          </div>
          <div class="layui-card-body layui-row">
            <button class="layui-btn layui-btn-sm layui-btn-fluid" lay-submit="" lay-filter="component-form-demo1">立即搜索</button>
          </div>
        </form>
      </div>
    </div>
    <div class="layui-col-md10">
      <div class="layui-card">
        <div class="layui-card-header">
          <button type="button" class="layui-btn layui-btn-sm">新增方案</button>
        </div>
        <div class="layui-card-body">

          <table class="layui-hide" id="test-table-page"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
    layui.config({
        base: base //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function(){
        var admin = layui.admin
            ,table = layui.table
            ,form = layui.form;

        table.render({
            elem: '#test-table-page'
            ,url: layui.setter.base + 'json/table/user.js'
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'username', width:80, title: '用户名'}
                ,{field:'sex', width:80, title: '性别', sort: true}
                ,{field:'city', width:80, title: '城市'}
                ,{field:'sign', title: '签名', minWidth: 150}
                ,{field:'experience', width:80, title: '积分', sort: true}
                ,{field:'score', width:80, title: '评分', sort: true}
                ,{field:'classify', width:80, title: '职业'}
                ,{field:'wealth', width:135, title: '财富', sort: true}
            ]]
            ,page: true
        });

        form.on('submit(component-form-demo1)', function(data){
            parent.layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

    });
</script>
</body>
</html>
