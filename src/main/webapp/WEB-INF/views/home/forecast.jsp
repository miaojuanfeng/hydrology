<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>预报</title>
  <%@ include file="../linker.jsp" %>
</head>
<style type="text/css">
  .input-tr{
    padding:0 !important;
  }
  .input-tr .layui-input{
    border: none !important;
  }
</style>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md3">
        <div class="layui-card layui-form" style="height: 663px;overflow: scroll;">
          <div class="layui-card-header">预报参数</div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="40%">
              <col width="60%">
            </colgroup>
            <tbody>
            <tr>
              <td>预报站点</td>
              <td class="input-tr">
                <select name="station" lay-filter="station" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <c:forEach items="${stations}" var="station" varStatus="id">
                    <option value="${station.stcd}">${station.stname}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>预报模型</td>
              <td class="input-tr">
                <select name="model" lay-filter="model" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>预报时间</td>
              <td class="input-tr">
                <input id="forecastTime" type="text" name="forecastTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${forecastTime}">
              </td>
            </tr>
            <tr>
              <td>影响时间</td>
              <td class="input-tr">
                <input id="affectTime" type="text" name="affectTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${affectTime}">
              </td>
            </tr>
            <tr>
              <td>预报天数</td>
              <td class="input-tr">
                <select name="day" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="3">3天</option>
                  <option value="5">5天</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>未来降雨</td>
              <td class="input-tr">
                <select name="unit" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="0">实测雨量</option>
                  <option value="2">日本台</option>
                  <option value="6">欧洲台</option>
                </select>
              </td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header">流域模型</div>
          <div id="area" class="demo-tree demo-tree-box" style="width: 100%; height: 240px; overflow-y: scroll; padding-top:10px;"></div>

          <div class="layui-card-header"><span class="stname"></span>参数调整</div>
          <table class="layui-table table-param" style="margin:0;">
            <colgroup>
              <col width="25%">
              <col width="25%">
              <col width="25%">
              <col width="25%">
            </colgroup>
            <tbody>
            <tr>
              <td>SM</td>
              <td class="input-tr">
                <input id="SM" type="text" name="SM" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>CI</td>
              <td class="input-tr">
                <input id="CI" type="text" name="CI" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            <tr>
              <td>CS</td>
              <td class="input-tr">
                <input id="CS" type="text" name="CS" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>L</td>
              <td class="input-tr">
                <input id="L" type="text" name="L" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            <tr>
              <td>KE</td>
              <td class="input-tr">
                <input id="KE" type="text" name="KE" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>XE</td>
              <td class="input-tr">
                <input id="XE" type="text" name="XE" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header"><span class="stname"></span>状态调整</div>
          <table class="layui-table table-param" style="margin:0;">
            <colgroup>
              <col width="25%">
              <col width="25%">
              <col width="25%">
              <col width="25%">
            </colgroup>
            <tbody>
            <tr>
              <td>WU0</td>
              <td class="input-tr">
                <input id="WU0" type="text" name="WU0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>WL0</td>
              <td class="input-tr">
                <input id="WL0" type="text" name="WL0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            <tr>
              <td>WD0</td>
              <td class="input-tr">
                <input id="WD0" type="text" name="WD0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>S0</td>
              <td class="input-tr">
                <input id="S0" type="text" name="S0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            <tr>
              <td>FR0</td>
              <td class="input-tr">
                <input id="FR0" type="text" name="FR0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>QRS0</td>
              <td class="input-tr">
                <input id="QRS0" type="text" name="QRS0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            <tr>
              <td>QRSS0</td>
              <td class="input-tr">
                <input id="QRSS0" type="text" name="QRSS0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
              <td>QRG0</td>
              <td class="input-tr">
                <input id="QRG0" type="text" name="QRG0" placeholder="请输入" autocomplete="off" class="layui-input">
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="layui-col-md9">
        <div class="layui-card layui-form">
          <div class="layui-card-header">
            <button lay-submit="" lay-filter="forecast1" class="layui-btn layui-btn-sm">预报流量</button>
            <button lay-submit="" lay-filter="forecast2" class="layui-btn layui-btn-sm">预报水位</button>
          </div>
          <div class="layui-card-body layui-row">
            <div id="chart" style="width:100%;height:600px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
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
  }).use(['index', 'admin', 'echarts', 'table', 'tree', 'form', 'laydate'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,tree = layui.tree
          ,form = layui.form
          ,laydate = layui.laydate
          ,echarts = layui.echarts;

      var data1 = [];
      var paramStcd = '';
      // var data1 = [{
      //     title: '江西'
      //     ,id: 1
      //     ,children: [{
      //         title: '南昌'
      //         ,id: 1000
      //         ,children: [{
      //             title: '青山湖区'
      //             ,id: 10001
      //         },{
      //             title: '高新区'
      //             ,id: 10002
      //         }]
      //     },{
      //         title: '九江'
      //         ,id: 1001
      //     },{
      //         title: '赣州'
      //         ,id: 1002
      //     }]
      // },{
      //     title: '广西'
      //     ,id: 2
      //     ,children: [{
      //         title: '南宁'
      //         ,id: 2000
      //     },{
      //         title: '桂林'
      //         ,id: 2001
      //     }]
      // },{
      //     title: '陕西'
      //     ,id: 3
      //     ,children: [{
      //         title: '西安'
      //         ,id: 3000
      //     },{
      //         title: '延安'
      //         ,id: 3001
      //     }]
      // }];


      laydate.render({
          elem: '#forecastTime'
          ,type: 'datetime'
      });

      laydate.render({
          elem: '#affectTime'
          ,type: 'datetime'
      });

      tree.render({
          elem: '#area'
          ,id: 'area'
          ,data: data1
          ,onlyIconControl: true
          ,click: function(obj){
              // layer.msg(JSON.stringify(obj.data));
              // layer.msg(JSON.stringify(data1));
              // $(".stname").html(obj.data.stname);
              setParam(obj.data);
          }
      });

      /* 根据站点获取模型 */
      form.on('select(station)', function(data){
          var loading = layer.load(0);
          $("select[name=model]").html('<option value="">请选择</option>');
          form.render('select');
          $.post(
              '${pageContext.request.contextPath}/model/getByStation',
              {
                  stcd: data.value
              },
              function (data) {
                  var stations = $.parseJSON(data);
                  var html = '';
                  $.each(stations, function (key, value) {
                      html += '<option value="' + value.id + '">' + value.name + '</option>';
                  });
                  $("select[name=model]").append(html);
                  form.render('select');
                  layer.close(loading);
              }
          );
      });

      /* 根据模型获取流域模型 */
      form.on('select(model)', function(data){
          var loading = layer.load(0);
          data1 = {};
          tree.reload('area', {
              data: data1
          });
          $.post(
              '${pageContext.request.contextPath}/model/getArea',
              {
                  modelId: data.value
              },
              function (data) {
                  data1 = $.parseJSON(data);
                  if(data1.length > 0){
                      setParam(data1[0]);
                  }else{
                      clearParam();
                  }
                  tree.reload('area', {
                      data: data1
                  });
                  layer.close(loading);
              }
          );
      });

      function setParam(data) {
          paramStcd = data.stcd;
          $(".stname").html(data.stname);

          $("input[name=SM]").val(data.plan.sm);
          $("input[name=CI]").val(data.plan.ci);
          $("input[name=CS]").val(data.plan.cs);
          $("input[name=L]").val(data.plan.l);
          $("input[name=KE]").val(data.ke);
          $("input[name=XE]").val(data.xe);

          $("input[name=WU0]").val(data.plan.wu0);
          $("input[name=WL0]").val(data.plan.wl0);
          $("input[name=WD0]").val(data.plan.wd0);
          $("input[name=S0]").val(data.plan.s0);
          $("input[name=FR0]").val(data.plan.fr0);
          $("input[name=QRS0]").val(data.plan.qrs0);
          $("input[name=QRSS0]").val(data.plan.qrss0);
          $("input[name=QRG0]").val(data.plan.qrg0);
      }

      function clearParam() {
          paramStcd = "";
          $(".stname").html("");

          $("input[name=SM]").val("");
          $("input[name=CI]").val("");
          $("input[name=CS]").val("");
          $("input[name=L]").val("");
          $("input[name=KE]").val("");
          $("input[name=XE]").val("");

          $("input[name=WU0]").val("");
          $("input[name=WL0]").val("");
          $("input[name=WD0]").val("");
          $("input[name=S0]").val("");
          $("input[name=FR0]").val("");
          $("input[name=QRS0]").val("");
          $("input[name=QRSS0]").val("");
          $("input[name=QRG0]").val("");
      }

      function updateParam() {
          function each(data) {
              data.forEach(function (e, index) {
                  if (e.stcd == paramStcd) {
                      e.plan.sm = $("input[name=SM]").val();
                      e.plan.ci = $("input[name=CI]").val();
                      e.plan.cs = $("input[name=CS]").val();
                      e.plan.l = $("input[name=L]").val();
                      e.ke = $("input[name=KE]").val();
                      e.xe = $("input[name=XE]").val();

                      e.plan.wu0 = $("input[name=WU0]").val();
                      e.plan.wl0 = $("input[name=WL0]").val();
                      e.plan.wd0 = $("input[name=WD0]").val();
                      e.plan.s0 = $("input[name=S0]").val();
                      e.plan.fr0 = $("input[name=FR0]").val();
                      e.plan.qrs0 = $("input[name=QRS0]").val();
                      e.plan.qrss0 = $("input[name=QRSS0]").val();
                      e.plan.qrg0 = $("input[name=QRG0]").val();

                      return;
                  }
                  if ( e.children != undefined && e.children.length > 0) {
                      each(e.children);
                  }
              })
          }
          each(data1);
      }

      $(".table-param input").keyup(function () {
          updateParam();
      });

      var option = {
          title: {
              text: '今日流量趋势',
              x: 'center',
              textStyle: {
                  fontSize: 14
              }
          },
          tooltip : {
              trigger: 'axis'
          },
          legend: {
              data:['','']
          },
          xAxis : [{
              type : 'category',
              boundaryGap : false,
              data: ['06:00','06:30','07:00','07:30','08:00','08:30','09:00','09:30','10:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00','22:30','23:00','23:30']
          }],
          yAxis : [{
              type : 'value'
          }],
          series : [{
              name:'PV',
              type:'line',
              smooth:true,
              itemStyle: {normal: {areaStyle: {type: 'default'}}},
              data: [111,222,333,444,555,666,3333,33333,55555,66666,33333,3333,6666,11888,26666,38888,56666,42222,39999,28888,17777,9666,6555,5555,3333,2222,3111,6999,5888,2777,1666,999,888,777]
          },{
              name:'UV',
              type:'line',
              smooth:true,
              itemStyle: {normal: {areaStyle: {type: 'default'}}},
              data: [11,22,33,44,55,66,333,3333,5555,12666,3333,333,666,1188,2666,3888,6666,4222,3999,2888,1777,966,655,555,333,222,311,699,588,277,166,99,88,77]
          }]
      };

      var chart = echarts.init(document.getElementById('chart'));
      chart.setOption(option);

      /* 预报流量 */
      form.on('submit(forecast1)', function(data){
          // parent.layer.alert(JSON.stringify(data.field), {
          //   title: '最终的提交信息'
          // })
          // var submit = true;
          // if ($("input[name=name]").val() == "" ||
          //     data1.length == 0) {
          //     submit = false;
          // }
          // if (!submit) {
          //     layer.msg('请填妥相关信息');
          //     return false;
          // }
          $.post({
              url: "${pageContext.request.contextPath}/forecast/compute",
              contentType: "application/x-www-form-urlencoded",
              data: {
                  forecastTime: $("input[name=forecastTime]").val(),
                  affectTime: $("input[name=affectTime]").val(),
                  day: $("select[name=day]").val(),
                  unit: $("select[name=unit]").val(),
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
              parent.layer.alert("计算出错，请重试", {
                  title: '错误'
              })
          });
          return false;
      });

      /* 预报水位 */
      form.on('submit(forecast2)', function(data){
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
              url: "${pageContext.request.contextPath}/model/insert" + update,
              contentType: "application/x-www-form-urlencoded",
              data: {
                  name: $("input[name=name]").val(),
                  stcd: $("input[name=stcd]").val(),
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

