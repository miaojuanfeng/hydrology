<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<script>
	var myChart = echarts.init(document.getElementById('main'), 'macarons');
    option = {
        title : {
            text: '水位过程',
            subtext: '${station.stname}',
            x: 'center',
            align: 'right'
        },
        grid: {
            bottom: 80
        },
        legend: {
            data:['加报','警戒']
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
        dataZoom: [

            {
                type: 'inside',
                realtime: true,

            }
        ],
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                axisLine: {onZero: false},
                data : [
                    <c:forEach items="${timeArr}" var="time" varStatus="id">
                    '${time}',
                    </c:forEach>
                ].map(function (str) {
                    return str.replace(' ', '\n')
                })
            }
        ],
        yAxis: [
            {
                name: '水位(m)',
                type: 'value',
                max: 500
            }

        ],
        series: [
            {
                name:'水位',
                type:'line',
                smooth:true,
                animation: true,
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
                ],

                markLine: {

                    // 	symbol:"none",               //去掉警戒线最后面的箭头
                    label:{

                        position:"end"          //将警示值放在哪个位置，三个值“start”,"middle","end"  开始  中点 结束
                    },
                    data : [{
                        name :'加报',



                        lineStyle:{               //警戒线的样式  ，虚实  颜色
                            //  type:"solid",
                            color:"#EE9A00",
                        },
                        yAxis: 200           // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值
                    },
                        {
                            name :'警戒',



                            lineStyle:{               //警戒线的样式  ，虚实  颜色
                                //  type:"solid",
                                color:"#CD0000",
                            },
                            yAxis: 300           // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值
                        }
                    ]

                }

            }

        ]
    };
    myChart.setOption(option);
</script>