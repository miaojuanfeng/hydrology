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
    <%--<div class="layui-col-md2">--%>
      <%--<div class="layui-card layui-form">--%>
        <%--<div class="layui-card-header">搜索方案</div>--%>
        <%--<form class="layui-form" action="" lay-filter="component-form-group">--%>
          <%--<div class="layui-card-body layui-row">--%>
            <%--<select name="modules" lay-verify="required" lay-search="">--%>
              <%--<option value="">站点类型</option>--%>
              <%--<option value="1">河道站</option>--%>
              <%--<option value="2">水库站</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <%--<div class="layui-card-body layui-row">--%>
            <%--<select name="modules" lay-verify="required" lay-search="">--%>
              <%--<option value="">站点区域</option>--%>
              <%--<option value="1">宁都</option>--%>
              <%--<option value="2">石城</option>--%>
              <%--<option value="2">汾坑</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <%--<div class="layui-card-body layui-row">--%>
            <%--<select name="modules" lay-verify="required" lay-search="">--%>
              <%--<option value="">站点名称</option>--%>
              <%--<option value="1">宁都</option>--%>
              <%--<option value="2">石城</option>--%>
              <%--<option value="2">汾坑</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <%--<div class="layui-card-body layui-row">--%>
            <%--<button class="layui-btn layui-btn-sm layui-btn-fluid" lay-submit="" lay-filter="component-form-demo1">立即搜索</button>--%>
          <%--</div>--%>
        <%--</form>--%>
      <%--</div>--%>
    <%--</div>--%>
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="new">新增方案</button>
        </div>
        <div class="layui-card-body">

          <table class="layui-hide" id="test-table-page"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</a>
</script>

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
            ,method: 'post'
            ,url: "${pageContext.request.contextPath}/plan/list"
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'stname', width:80, title: '预报站'}
                ,{field:'name', title: '名称'}
                ,{field:'username', width:120, title: '创建用户', sort: true}
                ,{field:'createTime', width:170, title: '创建时间'}
                ,{fixed: 'right', width:140, align:'center', toolbar: '#barDemo', title: '操作'}
            ]]
            ,page: true
        });

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                var href = '<c:url value="/model/insert/' + data.id + '"></c:url>';
                var l = parent === self ? layui : top.layui;
                l.index.openTabsPage(href, "编辑模型");
            }else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            }
        });

        form.on('submit(component-form-demo1)', function(data){
            parent.layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

        form.on('submit(new)', function(data){
            var href = '<c:url value="/plan/insert"></c:url>';
            var l = parent === self ? layui : top.layui;
            l.index.openTabsPage(href, "新增方案");
        });

    });
</script>
</body>
</html>
