<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<div id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'), 'macarons');
    option = {
   	    title : {
   	        text: '洪水过程',
   	        subtext: '宁都站',
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
   	                '2009/6/12 2:00', '2009/6/12 3:00', '2009/6/12 4:00', '2009/6/12 5:00', '2009/6/12 6:00', '2009/6/12 7:00', '2009/6/12 8:00', '2009/6/12 9:00', '2009/6/12 10:00', '2009/6/12 11:00', '2009/6/12 12:00', '2009/6/12 13:00', '2009/6/12 14:00', '2009/6/12 15:00', '2009/6/12 16:00', '2009/6/12 17:00', '2009/6/12 18:00', '2009/6/12 19:00', '2009/6/12 20:00', '2009/6/12 21:00', '2009/6/12 22:00', '2009/6/12 23:00'
   	               
   	            ].map(function (str) {
   	                return str.replace(' ', '\n')
   	            })
   	        }
   	    ],
   	    yAxis: [
   	        {
   	            name: '预报水位(m)',
   	            type: 'value',
   	            max: 500
   	        },
   	        {
   	            name: '降雨量(mm)',
   	            nameLocation: 'start',
   	            max: 5,
   	            type: 'value',
   	            inverse: true
   	        }
   	    ],
   	    series: [
   	        {
   	            name:'实测水位',
   	            type:'line',
   	            animation: false,
   	           
   	             smooth: true,
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
   	                0.41,0.41,2.41,4.42,0.42,0.42,0.42,5.42,10.42,10.42,10.42,22.42,30.43,50.43,100.43,100.43
   	            ]
   	        },
   	        {
   	            name:'预报水位',
   	            type:'line',
   	            animation: false,
   	           
   	             smooth: true,
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
   	                0.81,0.81,2.81,5.42,1.42,1.42,1.42,8.42,12.42,12.42,12.42,27.42,36.43,56.43,120.43,120.43,120.43,96.43,30.44,0.44,0.44,0.44
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
   	                        yAxis: 300
   	                    }
   	                    ]
   	            },

   	             markPoint: {
   	                 	 symbolSize: 80,
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
   	            animation: false,
   	           
   	           
   	           
   	            data: [
   	                0.571,0.814,1.153,0.774,0.202,0.086,0.075,0.071,0.032,0.019,0.003,0.004,0.004,0.004,0.004,0.004,0.004,0.007,0.072,0.153,0.256,0.306
   	            ]
   	        }
   	    ]
   	};
    myChart.setOption(option);
</script>