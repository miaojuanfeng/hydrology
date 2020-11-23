<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>预报站</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md8">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
            <div class="layui-card">
              <%--<div class="layui-card-header">今日雨量</div>--%>
              <div class="layui-card-body">
                  <div id="chart1" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
            <div class="layui-card">
              <%--<div class="layui-card-header">水位过程</div>--%>
              <div class="layui-card-body">
                  <div id="chart2" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="layui-col-md4">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
            <div class="layui-card">
              <%--<div class="layui-card-header">预警等级</div>--%>
              <div class="layui-card-body">
                <div id="chart3" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
              <div class="layui-card">
                  <%--<div class="layui-card-header">流域平均降雨</div>--%>
                  <div class="layui-card-body">
                      <div id="chart4" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                  </div>
              </div>
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
  }).use(['index', 'admin', 'echarts'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,echarts = layui.echarts;

      var option1 = {
          title: {
              text: '今日雨量',
              subtext: '${chart1.hour}',
              x: 'center',
              textStyle: {
                  fontSize: 14
              }
          },
          tooltip : {
              trigger: 'axis'
          },
          calculable : true,
          xAxis : [
              {
                  type : 'category',
                  data : [
                      <c:forEach items="${chart1.stationArr}" var="station" varStatus="id">
                      '${station}',
                      </c:forEach>
                  ]
              }
          ],
          yAxis : [
              {

                  name: '降雨量(mm)',
                  type: 'value',
                  max: ${chart1.max}
              }
          ],
          series : [
              {
                  name:'累计雨量',
                  type:'bar',
                  label: {
                      normal: {
                          show: true,
                          position: 'top'
                      }
                  },
                  data:[
                      <c:forEach items="${chart1.rainfallArr}" var="rainfall" varStatus="id">
                      ${rainfall},
                      </c:forEach>
                  ],
                  markLine : {
                      data : [
                          {type : 'average', name: '平均值'}
                      ]
                  }
              }
          ]
      };
      var chart1 = echarts.init(document.getElementById('chart1'));
      chart1.setOption(option1);

      var option2 = {
          title: {
              text: '水位过程',
              subtext: '${chart2.station.stname}',
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
              data: [
                  <c:forEach items="${chart2.timeArr}" var="time" varStatus="id">
                  '${time}',
                  </c:forEach>
              ].map(function (str) {
                  return str.replace(' ', '\n')
              })
          }],
          yAxis : [{
              name: '水位(m)',
              type: 'value',
              max: ${chart2.max},
              min: ${chart2.min}
          }],
          series : [{
              name:'水位',
              type:'line',
              smooth:true,
              itemStyle: {normal: {areaStyle: {type: 'default'}}},
              data: [
                  <c:forEach items="${chart2.riverArr}" var="river" varStatus="id">
                  '${river}',
                  </c:forEach>
              ]
          }]
      };
      var chart2 = echarts.init(document.getElementById('chart2'));
      chart2.setOption(option2);

      // chart2.showLoading();
      <%--$.post({--%>
          <%--url : "${basePath}/station/chart2",--%>
          <%--contentType : "application/x-www-form-urlencoded",--%>
          <%--data : {--%>
              <%--stcd: '${stcd}'--%>
          <%--},--%>
          <%--success : function(result) {--%>
              <%--chart2.hideLoading();--%>
              <%--var option2 = {--%>
                  <%--title : {--%>
                      <%--text: '水位过程',--%>
                      <%--subtext: result.station.stname,--%>
                      <%--x: 'center',--%>
                      <%--align: 'right'--%>
                  <%--},--%>
                  <%--grid: {--%>
                      <%--bottom: 80--%>
                  <%--},--%>
                  <%--legend: {--%>
                      <%--data:['加报','警戒']--%>
                  <%--},--%>
                  <%--tooltip : {--%>
                      <%--trigger: 'axis',--%>
                      <%--axisPointer: {--%>
                          <%--type: 'cross',--%>
                          <%--animation: false,--%>
                          <%--label: {--%>
                              <%--backgroundColor: '#505765'--%>
                          <%--}--%>
                      <%--}--%>
                  <%--},--%>
                  <%--dataZoom: [--%>
                      <%--{--%>
                          <%--type: 'inside',--%>
                          <%--realtime: true,--%>
                      <%--}--%>
                  <%--],--%>
                  <%--xAxis : [--%>
                      <%--{--%>
                          <%--type: 'category',--%>
                          <%--boundaryGap: false,--%>
                          <%--axisLine: {onZero: false},--%>
                          <%--axisLabel: {interval: 288},--%>
                          <%--data : result.timeArr.map(function (str) {--%>
                              <%--return str.replace(' ', '\n')--%>
                          <%--})--%>
                      <%--}--%>
                  <%--],--%>
                  <%--yAxis: [--%>
                      <%--{--%>
                          <%--name: '水位(m)',--%>
                          <%--type: 'value',--%>
                          <%--// max: result.max,--%>
                          <%--// min: result.min--%>
                      <%--}--%>
                  <%--],--%>
                  <%--series: [--%>
                      <%--{--%>
                          <%--name:'水位',--%>
                          <%--type:'line',--%>
                          <%--areaStyle: {},--%>
                          <%--symbol: 'circle',//折线点设置为实心点--%>
                          <%--symbolSize: 1,   //折线点的大小--%>
                          <%--smooth:true,--%>
                          <%--animation: true,--%>
                          <%--itemStyle:{--%>
                              <%--normal:{--%>
                                  <%--color:'#4DBEEE',--%>
                              <%--}--%>
                          <%--},--%>
                          <%--lineStyle: {--%>
                              <%--normal: {--%>
                                  <%--color:'#4DBEEE',--%>
                                  <%--width: 1,--%>
                                  <%--shadowColor: 'rgba(0,0,0,0.4)',--%>
                                  <%--shadowBlur: 15,--%>
                                  <%--//shadowOffsetY: 5--%>
                              <%--}--%>
                          <%--},--%>
                          <%--data: result.riverArr,--%>
                          <%--markLine: {--%>
                              <%--// 	symbol:"none",               //去掉警戒线最后面的箭头--%>
                              <%--label:{--%>
                                  <%--position:"end"          //将警示值放在哪个位置，三个值“start”,"middle","end"  开始  中点 结束--%>
                              <%--},--%>
                              <%--data : [--%>
                                  <%--{--%>
                                      <%--name :'加报',--%>
                                      <%--lineStyle:{               //警戒线的样式  ，虚实  颜色--%>
                                          <%--//  type:"solid",--%>
                                          <%--color:"#EE9A00",--%>
                                      <%--},--%>
                                      <%--yAxis: result.jbLine          // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值--%>
                                  <%--},--%>
                                  <%--{--%>
                                      <%--name :'警戒',--%>
                                      <%--lineStyle:{               //警戒线的样式  ，虚实  颜色--%>
                                          <%--//  type:"solid",--%>
                                          <%--color:"#CD0000",--%>
                                      <%--},--%>
                                      <%--yAxis: result.jjLine           // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值--%>
                                  <%--}--%>
                              <%--]--%>
                          <%--}--%>
                      <%--}--%>
                  <%--]--%>
              <%--};--%>
              <%--chart2.setOption(option2);--%>
          <%--}--%>
      <%--}).fail(function(response) {--%>
          <%--chart2.hideLoading();--%>
          <%--parent.layer.alert("数据获取失败", {--%>
              <%--title: '错误'--%>
          <%--});--%>
      <%--});--%>

      var option3 = {
          //backgroundColor: "#ffffff",
          series: [{
              name: '预警等级',
              type: 'gauge',
              title: {				// 仪表盘标题。
                  show: true,				// 是否显示标题,默认 true。
                  offsetCenter: [0,"30%"],//相对于仪表盘中心的偏移位置，数组第一项是水平方向的偏移，第二项是垂直方向的偏移。可以是绝对的数值，也可以是相对于仪表盘半径的百分比。
                  // color: "#4DBEEE",			// 文字的颜色,默认 #333。
                  fontSize: 20,			// 文字的字体大小,默认 15。
              },
              pointer: {    // 仪表盘指针。
                  show: true,    // 是否显示指针,默认 true。
                  length: "70%",   // 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
                  width: 5,    // 指针宽度,默认 8。
              },
              detail: {
                  formatter: '{value}',
                  offsetCenter: [0,"50%"],
              },
              axisTick: {				// 刻度(线)样式。
                  show: true,				// 是否显示刻度(线),默认 true。
                  splitNumber: 5,			// 分隔线之间分割的刻度数,默认 5。
                  length:12,				// 刻度线长。支持相对半径的百分比,默认 8。
                  lineStyle: {			// 刻度线样式。
                      // color: "#eee",				//线的颜色,默认 #eee。
                      opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                      width: 1,					//线度,默认 1。
                      type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                      shadowBlur: 10,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                      // shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  },
              },
              axisLabel: {			// 刻度标签。
                  show: false,				// 是否显示标签,默认 true。
                  distance: 5,			// 标签与刻度线的距离,默认 5。
                  // color: "#fff",			// 文字的颜色,默认 #fff。
                  fontSize: 12,			// 文字的字体大小,默认 5。
                  formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
              },
              axisLine: {
                  show: true,
                  lineStyle: {
                      width: 23,
                      shadowBlur: 0,
                      // color: [
                      //     [0.3, '#26d0ce'],
                      //     [0.7, '#4DBEEE'],
                      //     [1, '#F775A9']
                      // ]
                  }
              },
              data: [{
                  value: ${chart3.ava},
                  name: '预警等级',
              }]
          }]
      };
      var chart3 = echarts.init(document.getElementById('chart3'));
      chart3.setOption(option3);

      var option4 = {
          title: {
              text: '流域平均降雨',
              subtext: '${chart4.station.stname}',
              x: 'center',
              align: 'right',
              textStyle: {
                  fontSize: 14
              }
          },
          tooltip : {
              trigger: 'axis',
              axisPointer: {
                  type: 'cross',
                  animation: false,
                  // label: {
                  //     backgroundColor: '#505765'
                  // }
              }
          },
          xAxis : [{
              type : 'category',
              data: [
                  <c:forEach items="${chart4.dateArr}" var="date" varStatus="id">
                  '${date}',
                  </c:forEach>
              ]
          }],
          yAxis : [{
              name: '降雨量(mm)',
              type: 'value',
          }],
          series: [{
              data: [
                  <c:forEach items="${chart4.rainfallArr}" var="rainfall" varStatus="id">
                  '${rainfall}',
                  </c:forEach>
              ],
              type: 'line',
              name:'日雨量',
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              // label: {
              //        normal: {
              //            show: true,
              //             //  color:'#26d0ce',
              //            position: 'top'
              //        }
              //    },
              smooth: true,
              // itemStyle:{
              //     normal:{
              //         color:'#4DBEEE',
              //     }
              // },
              // lineStyle: {
              //     normal: {
              //         color:'#4DBEEE',
              //         width: 3,
              //         shadowColor: 'rgba(0,0,0,0.4)',
              //         shadowBlur: 10,
              //         shadowOffsetY: 10
              //     }
              // }
          }]
      };

      var chart4 = echarts.init(document.getElementById('chart4'));
      chart4.setOption(option4);
  });
  </script>
</body>
</html>

