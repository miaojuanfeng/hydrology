<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>新建模型</title>
  <%@ include file="../linker.jsp" %>
</head>
<style>
  .layui-tab .layui-form-label{
    width: 50px;
  }
</style>
<body>

  <div class="layui-fluid">
    <form class="layui-form" action="" lay-filter="component-form-group">
      <div class="layui-row layui-col-space15">

        <div class="layui-col-md8">
          <div class="layui-card">
            <div class="layui-card-header">
              <div style="float: left;">流域模型</div>
              <div style="float: right;">
                <button id="insert-root" type="button" class="layui-btn layui-btn-sm">插入根节点</button>
              </div>
              <div style="clear:both;float:none;"></div>
            </div>
            <div class="layui-card-body" style="padding: 15px;">
                <div id="test9" class="demo-tree demo-tree-box" style="width: 100%; height: 500px; overflow: scroll;"></div>
            </div>
          </div>
        </div>

        <div class="layui-col-md4">
          <div class="layui-card">
            <div class="layui-card-header">基本信息</div>
            <div class="layui-card-body" style="padding: 15px;">
              <div class="layui-form-item">
                <label class="layui-form-label">模型名称</label>
                <div class="layui-input-block">
                  <input type="hidden" name="id" value="${id}">
                  <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入模型名称" class="layui-input" value="${name}">
                </div>
              </div>
            </div>
          </div>
          <div class="layui-card">
            <div class="layui-card-header">站点信息</div>
            <div class="layui-card-body">
              <table class="layui-table" style="margin:0;">
                <colgroup>
                  <col width="30%">
                  <col width="70%">
                </colgroup>
                <tbody>
                <tr>
                  <td>站点名称</td>
                  <td id="td-stname"></td>
                </tr>
                <tr>
                  <td>预报方案</td>
                  <td id="td-plan"></td>
                </tr>
                <tr>
                  <td>产流模型</td>
                  <td id="td-cl"></td>
                </tr>
                <tr>
                  <td>汇流模型</td>
                  <td id="td-hl"></td>
                </tr>
                <tr>
                  <td>KE</td>
                  <td id="td-ke"></td>
                </tr>
                <tr>
                  <td>XE</td>
                  <td id="td-xe"></td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>

      </div>
      <div class="layui-form-item layui-layout-admin">
        <div class="layui-input-block">
          <div class="layui-footer" style="left: 0;">
            <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">关闭</button>
          </div>
        </div>
      </div>
    </form>
  </div>


  <form class="layui-form" lay-filter="addform" id="addform" style="padding:15px;display: none;">
    <div class="layui-form-item">
      <label class="layui-form-label">站点名称</label>
      <div class="layui-input-block">
        <select name="station" lay-filter="station" lay-verify="required" lay-search="">
          <option value="">请选择</option>
          <c:forEach items="${stations}" var="station" varStatus="id">
            <option value="${station.stcd}">${station.stname}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">预报方案</label>
      <div class="layui-input-block">
        <select name="plan" lay-verify="required" lay-search=""></select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">产流模型</label>
      <div class="layui-input-block">
        <select name="cl_model" lay-verify="required" lay-search="">
          <option value="">请选择</option>
          <option value="1">新安江</option>
          <option value="2">经验单位线</option>
          <option value="3">API</option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">汇流模型</label>
      <div class="layui-input-block">
        <select name="hl_model" lay-verify="required" lay-search="">
          <option value="">请选择</option>
          <option value="1">新安江</option>
          <option value="2">经验单位线</option>
          <option value="3">API</option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">KE</label>
      <div class="layui-input-block">
        <input type="text" name="KE" lay-verify="KE" autocomplete="off" placeholder="请输入" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">XE</label>
      <div class="layui-input-block">
        <input type="text" name="XE" lay-verify="XE" autocomplete="off" placeholder="请输入" class="layui-input">
      </div>
    </div>
  </form>


  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'laydate', 'tree'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,tree = layui.tree
    ,form = layui.form;

    var data1 = ${data};
    if (data1.length > 0) {
        $("#insert-root").addClass("layui-btn-disabled");
    }

    // var data1 = [{
    //     title: '江西'
    //     ,id: 1
    //     ,spread: true
    //     ,children: [{
    //         title: '南昌'
    //         ,id: 1000
    //         ,spread: true
    //         ,children: [{
    //             title: '青山湖区'
    //             ,id: 10001
    //             ,spread: true
    //         },{
    //             title: '高新区'
    //             ,id: 10002
    //             ,spread: true
    //         }]
    //     },{
    //         title: '九江'
    //         ,id: 1001
    //         ,spread: true
    //     },{
    //         title: '赣州'
    //         ,id: 1002
    //         ,spread: true
    //     }]
    // },{
    //     title: '广西'
    //     ,id: 2
    //     ,spread: true
    //     ,children: [{
    //         title: '南宁'
    //         ,id: 2000
    //         ,spread: true
    //     },{
    //         title: '桂林'
    //         ,id: 2001
    //         ,spread: true
    //     }]
    // },{
    //     title: '陕西'
    //     ,id: 3
    //     ,spread: true
    //     ,children: [{
    //         title: '西安'
    //         ,id: 3000
    //         ,spread: true
    //     },{
    //         title: '延安'
    //         ,id: 3001
    //         ,spread: true
    //     }]
    // }];

    tree.render({
        elem: '#test9'
        ,id: 'test9'
        ,data: data1
        ,onlyIconControl: true
        ,customOperate: true
        ,edit: ['add', 'update', 'del'] //操作节点的图标
        ,click: function(obj){
            var data = obj.data;
            $('#td-stname').html(data.stname);
            $('#td-plan').html(data.plan);
            $('#td-cl').html(data.cl);
            $('#td-hl').html(data.hl);
            $('#td-ke').html(data.ke);
            $('#td-xe').html(data.xe);
        }
        ,operate: function (obj) {
            var type = obj.type;
            var data = obj.data;
            var elem = obj.elem;
            var deptId = data.id;
            var parentId = data.parentId;

            if( type == 'add' ){
                openForm("add", deptId);
            }else if( type == 'update' ){
                openForm("update", deptId);
            }else if( type == 'del' ){
                function each(data) {
                    data.forEach(function (e, index) {
                        if (e.id == deptId) {
                            data.splice(index, 1);
                        }
                        if ( e.children != undefined && e.children.length > 0) {
                            each(e.children);
                        }
                    })
                }
                each(data1);
                if (data1.length == 0) {
                    $("#insert-root").removeClass("layui-btn-disabled");
                }
                tree.reload('test9', {
                    data: data1
                });
            }
        }
    });

    function clearForm() {
        $("#addform select[name=station]").val('');
        $("#addform select[name=plan]").val('');
        $("#addform select[name=cl_model]").val('');
        $("#addform select[name=hl_model]").val('');
        $("#addform input[name=KE]").val('');
        $("#addform input[name=XE]").val('');
        form.render();
    }

    function setForm(e) {
        clearForm();
        $("#addform select[name=station]").val(e.stcd);
        $("#addform select[name=plan]").html('<option value="">请选择</option>');
        form.render('select');
        $.post(
            '${pageContext.request.contextPath}/views/plan/getByStation',
            {
                stcd: e.stcd
            },
            function (data) {
                var stations = $.parseJSON(data);
                var html = '';
                $.each(stations, function (key, value) {
                    html += '<option value="' + value.id + '">' + value.name + '</option>';
                });
                $("#addform select[name=plan]").append(html);

                $("#addform select[name=plan]").val(e.planId);

                form.render('select');
            }
        );
        $("#addform select[name=cl_model]").val(e.clId);
        $("#addform select[name=hl_model]").val(e.hlId);
        $("#addform input[name=KE]").val(e.ke);
        $("#addform input[name=XE]").val(e.xe);
        form.render();
    }

    function openForm(action, deptId){
        layer.open({
            type: 1
            ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            ,id: 'layerDemo1' //防止重复弹出
            ,content: $('#addform')
            ,area:["500px","450px"]
            ,btn: ['确定', '取消']
            ,btnAlign: 'c' //按钮居中
            ,shade: 0 //不显示遮罩
            ,btn1: function(index, layero){
                if(action == "new" || action == "add") {
                    var submit = true;
                    if ($("#addform select[name=station]").val() == "" ||
                        $("#addform select[name=plan]").val() == "" ||
                        $("#addform select[name=cl_model]").val() == "" ||
                        $("#addform select[name=hl_model]").val() == "" ||
                        $("#addform input[name=KE]").val() == "" ||
                        $("#addform input[name=XE]").val() == "") {
                        submit = false;
                    }
                    if (!submit) {
                        layer.msg('请填妥相关信息');
                        return false;
                    }
                    var station = {
                        title: $("#addform select[name=station]").find("option:selected").text(),
                        stcd: $("#addform select[name=station]").val(),
                        stname: $("#addform select[name=station]").find("option:selected").text(),
                        planId: $("#addform select[name=plan]").val(),
                        plan: $("#addform select[name=plan]").find("option:selected").text(),
                        clId: $("#addform select[name=cl_model]").val(),
                        cl: $("#addform select[name=cl_model]").find("option:selected").text(),
                        hlId: $("#addform select[name=hl_model]").val(),
                        hl: $("#addform select[name=hl_model]").find("option:selected").text(),
                        ke: $("#addform input[name=KE]").val(),
                        xe: $("#addform input[name=XE]").val(),
                        id: new Date().getTime(),
                        spread: true
                    };
                    if (deptId == 0) {
                        data1.push(station);
                    } else {
                        function each(data) {
                            data.forEach(function (e) {
                                if (e.id == deptId) {
                                    if (e.children == undefined) {
                                        e.children = [];
                                    }
                                    e.children.push(station);
                                }
                                if (e.children != undefined && e.children.length > 0) {
                                    each(e.children);
                                }
                            })
                        }
                        each(data1);
                    }
                    if (data1.length > 0) {
                        $("#insert-root").addClass("layui-btn-disabled");
                    }
                    tree.reload('test9', {
                        data: data1
                    });
                    clearForm();
                    layer.closeAll();
                }else if(action == "update"){
                    function each(data) {
                        data.forEach(function (e) {
                            if (e.id == deptId) {
                                e.title = $("#addform select[name=station]").find("option:selected").text();
                                e.stcd = $("#addform select[name=station]").val();
                                e.stname = $("#addform select[name=station]").find("option:selected").text();
                                e.planId = $("#addform select[name=plan]").val();
                                e.plan = $("#addform select[name=plan]").find("option:selected").text();
                                e.clId = $("#addform select[name=cl_model]").val();
                                e.cl = $("#addform select[name=cl_model]").find("option:selected").text();
                                e.hlId = $("#addform select[name=hl_model]").val();
                                e.hl = $("#addform select[name=hl_model]").find("option:selected").text();
                                e.ke = $("#addform input[name=KE]").val();
                                e.xe = $("#addform input[name=XE]").val();
                            }
                            if (e.children != undefined && e.children.length > 0) {
                                each(e.children);
                            }
                        })
                    }
                    each(data1);
                    tree.reload('test9', {
                        data: data1
                    });
                    clearForm();
                    layer.closeAll();
                }
            }
            ,btn2: function(index, layero){
                clearForm();
                layer.closeAll();
            }
            ,cancel: function(){ //右上角关闭回调
                clearForm();
                layer.closeAll();
            }
            ,success: function(layero, index){  //弹出成功的回调
                if(action == "update"){
                    function each(data) {
                        for(var i=0; i<data.length; i++){
                            var e = data[i];
                            if (e.id == deptId) {
                                return e;
                            }
                            if (e.children != undefined && e.children.length > 0) {
                                var e = each(e.children);
                                if( e != undefined ){
                                    return e;
                                }
                            }
                        }
                        return undefined;
                    }
                    var e = each(data1);
                    setForm(e);
                }
            }
        });
    }

    /* 根据站点获取方案 */
    form.on('select(station)', function(data){
        $("#addform select[name=plan]").html('<option value="">请选择</option>');
        form.render('select');
        $.post(
            '${pageContext.request.contextPath}/views/plan/getByStation',
            {
                stcd: data.value
            },
            function (data) {
                var stations = $.parseJSON(data);
                var html = '';
                $.each(stations, function (key, value) {
                    html += '<option value="' + value.id + '">' + value.name + '</option>';
                });
                $("#addform select[name=plan]").append(html);
                form.render('select');
            }
        );
    });
    
    form.render(null, 'component-form-group');
    
    /* 自定义验证规则 */
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      }
      ,pass: [/(.+){6,12}$/, '密码必须6到12位']
      ,content: function(value){
        layedit.sync(editIndex);
      }
    });
    
    /* 监听指定开关 */
    form.on('switch(component-form-switchTest)', function(data){
      layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
        offset: '6px'
      });
      layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });

    /* 插入根节点 */
    $("#insert-root").click(function(){
        if( data1.length > 0 ){
            return false;
        }
        openForm("new", 0);
    });

    var openTree = function(treeData, objId) {
        var nodeId = familyTree(treeData, objId);
        function each(data) {
            data.forEach(e => {
                if (nodeId.indexOf(e.id) != -1) {
                e.spread = true;
              }
              if (e.children.length > 0) {
                  each(e.children);
              }
            })
        }
        each(treeData);
        return treeData;
    };

    // 查找一个节点的所有父节点
    var familyTree = function(arr1, id) {
        var temp = [];
        var forFn = function(arr, id) {
            for (var i = 0; i < arr.length; i++) {
                var item = arr[i]
                if (item.id === id) {
                    temp.push(item.id);
                    forFn(arr1, item.parentId);
                    break
                } else {
                    if (item.children) {
                        forFn(item.children, id);
                    }
                }
            }
        }
        forFn(arr1, id);
        return temp;
    };
    
    /* 监听提交 */
    form.on('submit(save)', function(data){
      // parent.layer.alert(JSON.stringify(data.field), {
      //   title: '最终的提交信息'
      // })
      var submit = true;
      if ($("input[name=name]").val() == "" ||
          data1.length == 0) {
          submit = false;
      }
      if (!submit) {
          layer.msg('请填妥相关信息');
          return false;
      }
      var id = $("input[name=id]").val();
      var update = '';
      if( id != '' ){
          update = '/' + id;
      }
      $.post({
          url: "${pageContext.request.contextPath}/views/model/insert" + update,
          contentType: "application/x-www-form-urlencoded",
          data: {
              name: $("input[name=name]").val(),
              data: JSON.stringify(data1)
          },
          success : function(result) {
              parent.layer.alert("数据保存成功", {
                  title: '成功'
              }, function () {
                  alert('关闭当前页面');
              })
          }
      }).fail(function(response) {
          parent.layer.alert("数据保存失败", {
            title: '错误'
          })
      });
      return false;
    });
  });
  </script>
</body>
</html>
