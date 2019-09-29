<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<script>
   	var myChart = echarts.init(document.getElementById('main'), 'macarons');
   	<%--option = {--%>
	    <%--title : {--%>
            <%--text: '累计雨量',--%>
	        <%--subtext: '${hour}',--%>
	        <%--x: 'center',--%>
	        <%--align: 'right'--%>

	    <%--},--%>
	    <%--tooltip : {--%>
	        <%--trigger: 'axis'--%>
	    <%--},--%>
	   <%----%>
	    <%--toolbox: {--%>
	        <%--show : true,--%>
	        <%--feature : {--%>
	            <%--dataView : {show: true, readOnly: false},--%>
	            <%--magicType : {show: true, type: ['line', 'bar']},--%>
	            <%--restore : {show: true},--%>
	            <%--saveAsImage : {show: true}--%>
	        <%--}--%>
	    <%--},--%>
	    <%--calculable : true,--%>
	    <%--xAxis : [--%>
	        <%--{--%>
	            <%--type : 'category',--%>
	            <%--// data : ['固村','山车','留田','禾塘水库','大沽','山田水库','小布','和平','小吟','马头']--%>
                <%--data:[--%>
                    <%--<c:forEach items="${stationArr}" var="station" varStatus="id">--%>
                        <%--'${station}',--%>
                    <%--</c:forEach>--%>
                <%--]--%>
	        <%--}--%>
	    <%--],--%>
	    <%--yAxis : [--%>
	        <%--{--%>
	            <%--type : 'value',--%>
	            <%--name:'降雨量（mm）'--%>
	        <%--}--%>
	    <%--],--%>
	    <%--series : [--%>
	        <%--{--%>
	            <%--name:'小时雨量',--%>
	            <%--type:'bar',--%>
	            <%--itemStyle:{--%>
	                                    <%--normal:{--%>
	                                         <%--color:'#26d0ce',--%>
	                                    <%--}--%>
	                                <%--},--%>

	            <%--//data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.22, 32.6, 20.0],--%>
                <%--data:[--%>
                    <%--<c:forEach items="${rainfallArr}" var="rainfall" varStatus="id">--%>
                        <%--${rainfall},--%>
                    <%--</c:forEach>--%>
                <%--],--%>
	            <%--// markPoint : {--%>
	            <%--// 	symbolSize: 70,--%>
	            <%--//     data : [--%>
	            <%--//         {type : 'max', name: '最大值'},--%>
	            <%--//--%>
	            <%--//     ]--%>
	            <%--// },--%>
	            <%--markLine : {--%>
	                <%--data : [--%>
	                    <%--{type : 'average', name: '平均值'}--%>
	                <%--]--%>
	            <%--}--%>
	        <%--}--%>
	       <%----%>
	    <%--]--%>
	<%--};--%>


    option = {
        title : {
            text: '累计雨量',
            subtext: '${hour}',
            x: 'center',
            align: 'right'
        },
        color: ['#4DBEEE'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : [
                    <c:forEach items="${stationArr}" var="station" varStatus="id">
                    '${station}',
                    </c:forEach>
                ],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {

                name: '降雨量(mm)',
                type: 'value',
                max: ${max}
            }
        ],
        series : [
            {
                name:'累计雨量',
                type:'bar',
                label: {
                    normal: {
                        show: true,
                        //  color:'#26d0ce',
                        position: 'top'
                    }
                },
                barWidth: '40%',
                data:[
                    <c:forEach items="${rainfallArr}" var="rainfall" varStatus="id">
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
    myChart.setOption(option);
</script>