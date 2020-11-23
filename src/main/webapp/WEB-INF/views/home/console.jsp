<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>主页</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header">快捷方式</div>
              <div class="layui-card-body">
                
                <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a lay-href="home/homepage1.html">
                          <i class="layui-icon layui-icon-console"></i>
                          <cite>主页一</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="home/homepage2.html">
                          <i class="layui-icon layui-icon-chart"></i>
                          <cite>主页二</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="component/layer/list.html">
                          <i class="layui-icon layui-icon-template-1"></i>
                          <cite>弹层</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a layadmin-event="im">
                          <i class="layui-icon layui-icon-chat"></i>
                          <cite>聊天</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="component/progress/index.html">
                          <i class="layui-icon layui-icon-find-fill"></i>
                          <cite>进度条</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="app/workorder/list.html">
                          <i class="layui-icon layui-icon-survey"></i>
                          <cite>工单</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="user/user/list.html">
                          <i class="layui-icon layui-icon-user"></i>
                          <cite>用户</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/system/website.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>设置</cite>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                    </ul>
                    
                  </div>
                </div>
                
              </div>
            </div>
          </div>
          <div class="layui-col-md8">
            <div class="layui-card">
              <div class="layui-card-header">站点数据</div>
              <div class="layui-card-body">

                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs2">
                        <a lay-href="app/content/comment.html" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>汾坑</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a lay-href="app/forum/list.html" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>宁都</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a lay-href="template/goodslist.html" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>汾坑</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>宁都</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>汾坑</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>汾坑</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>石城</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs2">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>雨量站</h3>
                          <p><cite>宁都</cite></p>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs2">
                        <a href="javascript:;" class="layadmin-backlog-body">
                          <h3>待审友情链接</h3>
                          <p><cite style="color: #FF5722;">5</cite></p>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="layui-col-md12">
            <div class="layui-card">
              <div class="layui-card-header">数据概览</div>
              <div class="layui-card-body">
                <div id="chart1" style="width:100%;height:340px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
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
  }).use(['index', 'echarts', 'carousel'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,carousel = layui.carousel
          ,device = layui.device()
          ,echarts = layui.echarts;

      //轮播切换
      $('.layadmin-carousel').each(function(){
          var othis = $(this);
          carousel.render({
              elem: this
              ,width: '100%'
              ,arrow: 'none'
              ,interval: othis.data('interval')
              ,autoplay: othis.data('autoplay') === true
              ,trigger: (device.ios || device.android) ? 'click' : 'hover'
              ,anim: othis.data('anim')
          });
      });

      var option1 = {
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

      var chart1 = echarts.init(document.getElementById('chart1'));
      chart1.setOption(option1);
  });
  </script>
</body>
</html>

