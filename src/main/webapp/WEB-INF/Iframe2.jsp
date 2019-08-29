<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<script>
   	var myChart = echarts.init(document.getElementById('main'), 'macarons');
   	option = {
	    title : {
	           text: '小时雨量',
	        subtext: '7时',
	        x: 'center',
	        align: 'right'

	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	   
	    toolbox: {
	        show : true,
	        feature : {
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : ['固村','山车','留田','禾塘水库','大沽','山田水库','小布','和平','小吟','马头']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name:'降雨量（mm）'
	        }
	    ],
	    series : [
	        {
	            name:'小时雨量',
	            type:'bar',
	            itemStyle:{
	                                    normal:{
	                                         color:'#26d0ce',
	                                    }
	                                },

	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.22, 32.6, 20.0],
	            markPoint : {
	            	symbolSize: 70,
	                data : [
	                    {type : 'max', name: '最大值'},
	               
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        }
	       
	    ]
	};
  	myChart.setOption(option);
</script>