<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<link rel="stylesheet" href="<c:url value="/assets/static/css/xaj.css"></c:url>">
<style>
    #div-nav a{
        text-decoration: none;
    }
</style>
<div id="div-nav" style="height:58px;border-bottom:1px solid #eee;">
    <div style="padding:10px;">
        <a class="selected" href="javascript:;">
            <span>宁都</span>
        </a>
        <a href="javascript:;">
            <span>石城</span>
        </a>
        <a href="javascript:;">
            <span>汾坑</span>
        </a>
    </div>
</div>
<div id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'), 'macarons');
    option = {
   	    title : {
   	        text: '洪水过程',
   	        subtext: '${station.stname}',
   	        x: 'center',
   	        align: 'right'
   	    },
   	    grid: {
   	        bottom: 80
   	    },
   	    toolbox: {
   	        feature: {
   	            dataZoom: {
   	                yAxisIndex: 'none'
   	            },
   	            restore: {},
   	            saveAsImage: {}
   	        }
   	    },
   	    tooltip : {
   	        trigger: 'axis',
   	        axisPointer: {
   	            type: 'cross',
   	            animation: false,
   	            label: {
   	                backgroundColor: '#505765'
   	            }
   	        }
   	    },
   	    legend: {
   	        data:['实测水位','预报水位','降雨量'],
   	        x: 'left'
   	    },
   	    dataZoom: [
   	        {
   	            show: false,
   	            realtime: true,
   	          //  start: 65,
   	           // end: 85
   	        },
   	        {
   	            type: 'inside',
   	            realtime: true,
   	           start: 65,
   	          end: 85
   	        }
   	    ],
   	    xAxis : [
   	        {
   	            type : 'category',
   	            boundaryGap : true,
   	            axisLine: {onZero: false},
   	            data : [
                    <c:forEach items="${timeArr}" var="time" varStatus="id">
                    '${time}',
                    </c:forEach>
                ].map(function (str) {
   	                return str.replace('-', '/').replace('-', '/').replace(' ', '\n')
   	            })
   	        }
   	    ],
   	    yAxis: [
   	        {
   	            name: '预报水位(m)',
   	            type: 'value',
   	            max: ${riverMax}
   	        },
   	        {
   	            name: '降雨量(mm)',
   	            nameLocation: 'start',
   	            max: ${rainfallMax},
   	            type: 'value',
   	            inverse: true
   	        }
   	    ],
   	    series: [
   	        {
   	            name:'实测水位',
   	            type:'line',
   	            animation: true,
   	             smooth: true,
                symbol: 'circle',
                symbolSize: 1,
   	        itemStyle:{
   	                                    normal:{
   	                                         color:'#26d0ce',
   	                                        
   	                                    }
   	                                },
   	            lineStyle: {
   	                normal: {
   	                    color:'#26d0ce',
   	                    width: 3,
   	                    shadowColor: 'rgba(0,0,0,0.4)',
   	                    shadowBlur: 10,
   	                    shadowOffsetY: 10
   	                }
   	            },
   	           
   	            data:[
                    <c:forEach items="${riverArr}" var="river" varStatus="id">
                    '${river}',
                    </c:forEach>
                ]
   	        },
   	        {
   	            name:'预报水位',
   	            type:'line',
   	            animation: true,
   	             smooth: true,
                symbol: 'circle',
                symbolSize: 1,
   	        itemStyle:{
   	                                    normal:{
   	                                         color:'#FF3E96',
   	                                        
   	                                    }
   	                                },
   	            lineStyle: {
   	                normal: {
   	                    color:'#FF3E96',
   	                    width: 3,
   	                    shadowColor: 'rgba(0,0,0,0.4)',
   	                    shadowBlur: 10,
   	                    shadowOffsetY: 10
   	                }
   	            },
   	           
   	            data:[
                    <c:forEach items="${forcastArr}" var="forcast" varStatus="id">
                    '${forcast}',
                    </c:forEach>
                ],
   	            markLine: {
   	               itemStyle: {
   	                   normal: { lineStyle: { color:'#CD2626' },
   	                   label: { show: true , position:'middle' }
   	                   }
   	               },
   	                data: [
   	                   
   	                    {
   	                        name: '警戒水位',
   	                        yAxis: ${jjLine}
   	                    }
   	                    ]
   	            },

   	             markPoint: {
   	                 	 symbolSize: 65,
   	                data: [
   	                    {type: 'max', name: '最大值'}
   	                   
   	                ]
   	            }
   	        },
   	        {
   	            name:'降雨量',
   	            type:'bar',
   	            itemStyle:{
   	                                    normal:{
   	                                         color:'#7EC0EE',
   	                                        
   	                                    }
   	                                },
   	               barWidth: '40%',
   	            yAxisIndex:1,
   	            animation: true,
   	           
   	           
   	           
   	            data: [
                    <c:forEach items="${rainfallArr}" var="rainfall" varStatus="id">
                    '${rainfall}',
                    </c:forEach>
                ]
   	        }
   	    ]
   	};
    myChart.setOption(option);
</script>