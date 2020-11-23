<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>预报</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md3">
        <div class="layui-card layui-form" style="height: 663px;overflow: scroll;">
          <div class="layui-card-header">
            <div style="float: left;">预报参数</div>
            <div style="float: right;">
              <button type="button" class="layui-btn layui-btn-sm">修改</button>
            </div>
            <div style="clear:both;float:none;"></div>
          </div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="30%">
              <col width="70%">
            </colgroup>
            <tbody>
            <tr>
              <td>预报站</td>
              <td></td>
            </tr>
            <tr>
              <td>预报模型</td>
              <td></td>
            </tr>
            <tr>
              <td>预报时间</td>
              <td></td>
            </tr>
            <tr>
              <td>影响时间</td>
              <td></td>
            </tr>
            <tr>
              <td>预报天数</td>
              <td></td>
            </tr>
            <tr>
              <td>未来降雨</td>
              <td></td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header">流域模型</div>
          <div id="area" class="demo-tree demo-tree-box" style="width: 100%; height: 320px; overflow-y: scroll;"></div>

          <div class="layui-card-header">
            <div style="float: left;">参数调整</div>
            <div style="float: right;">
              <button type="button" class="layui-btn layui-btn-sm">修改</button>
            </div>
            <div style="clear:both;float:none;"></div>
          </div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="20%">
              <col width="30%">
              <col width="20%">
              <col width="30%">
            </colgroup>
            <tbody>
            <tr>
              <td>SM</td>
              <td></td>
              <td>CI</td>
              <td></td>
            </tr>
            <tr>
              <td>CS</td>
              <td></td>
              <td>L</td>
              <td></td>
            </tr>
            <tr>
              <td>KE</td>
              <td></td>
              <td>XE</td>
              <td></td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header">
            <div style="float: left;">初始状态调整</div>
            <div style="float: right;">
              <button type="button" class="layui-btn layui-btn-sm">修改</button>
            </div>
            <div style="clear:both;float:none;"></div>
          </div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="20%">
              <col width="30%">
              <col width="20%">
              <col width="30%">
            </colgroup>
            <tbody>
            <tr>
              <td>WU0</td>
              <td></td>
              <td>WL0</td>
              <td></td>
            </tr>
            <tr>
              <td>WD0</td>
              <td></td>
              <td>S0</td>
              <td></td>
            </tr>
            <tr>
              <td>FR0</td>
              <td></td>
              <td>QRS0</td>
              <td></td>
            </tr>
            <tr>
              <td>QRSS0</td>
              <td></td>
              <td>QRG0</td>
              <td></td>
            </tr>
            </tbody>
          </table>

        </div>
      </div>
      <div class="layui-col-md9">
        <div class="layui-card layui-form">
          <div class="layui-card-header">
            <button type="button" class="layui-btn layui-btn-sm">预报流量</button>
            <button type="button" class="layui-btn layui-btn-sm">预报水位</button>
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
  }).use(['index', 'admin', 'echarts', 'table', 'tree'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,tree = layui.tree
          ,form = layui.form
          ,echarts = layui.echarts;

      var data1 = [{
          title: '江西'
          ,id: 1
          ,children: [{
              title: '南昌'
              ,id: 1000
              ,children: [{
                  title: '青山湖区'
                  ,id: 10001
              },{
                  title: '高新区'
                  ,id: 10002
              }]
          },{
              title: '九江'
              ,id: 1001
          },{
              title: '赣州'
              ,id: 1002
          }]
      },{
          title: '广西'
          ,id: 2
          ,children: [{
              title: '南宁'
              ,id: 2000
          },{
              title: '桂林'
              ,id: 2001
          }]
      },{
          title: '陕西'
          ,id: 3
          ,children: [{
              title: '西安'
              ,id: 3000
          },{
              title: '延安'
              ,id: 3001
          }]
      }];

      tree.render({
          elem: '#area'
          ,data: data1
          ,onlyIconControl: true
          ,click: function(obj){
              layer.msg(JSON.stringify(obj.data));
              layer.msg(JSON.stringify(data1));
          }
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
  });
  </script>
</body>
</html>

