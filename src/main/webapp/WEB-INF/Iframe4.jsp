<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
/*body{
	margin:8px 35px 45px 35px;
}*/
</style>
<div id="main" style="height:100%;height:100%"></div>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<script>
	var myChart = echarts.init(document.getElementById('main'), 'macarons');
	option = {
	    title : {
	        text: '降雨趋势',
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
	    xAxis: {
	        type: 'category',
	        data: [
                <c:forEach items="${dateArr}" var="date" varStatus="id">
                '${date}',
                </c:forEach>
            ]
	    },
	    yAxis: {
	         
	            name: '降雨量(mm)',
	            type: 'value'
	            
	        
	    },
	    series: [{
	        data: [
                <c:forEach items="${rainfallArr}" var="rainfall" varStatus="id">
                '${rainfall}',
                </c:forEach>
            ],
	        type: 'line',
	        name:'日雨量',
	         label: {
	                normal: {
	                    show: true,
	                     //  color:'#26d0ce',
	                    position: 'top'
	                }
	            },
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
	            }
	        
	    }]
	};
	myChart.setOption(option);
</script>