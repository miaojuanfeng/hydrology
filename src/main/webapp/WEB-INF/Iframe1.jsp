<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>--%>
<script type="text/javascript" src="<c:url value="/assets/echarts/echarts.gauge.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/assets/echarts/macarons.js"></c:url>"></script>
<style>
    .river{
        text-align:center;
    }
    .river .title{
        font-size:18px;
        color:#008acd;
        margin:0;
    }
    .river .desc{
        font-size:12px;
        margin-top:5px;
        color:#aaa;
    }
</style>
<div class="river" style="height:100%;">
    <p class="title">预警等级</p>
    <p class="desc">${station.stname}</p>
    <div id="main" style="width:100%;height:100%;margin-top:-25px;"></div>
</div>
<script>
    var myChart = echarts.init(document.getElementById('main'), 'macarons');
    option = {
        //backgroundColor: "#ffffff",
        series: [{
            name: '预警等级',
            type: 'gauge',
            title: {				// 仪表盘标题。
                show: true,				// 是否显示标题,默认 true。
                offsetCenter: [0,"20%"],//相对于仪表盘中心的偏移位置，数组第一项是水平方向的偏移，第二项是垂直方向的偏移。可以是绝对的数值，也可以是相对于仪表盘半径的百分比。
                color: "#4DBEEE",			// 文字的颜色,默认 #333。
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
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 1,					//线度,默认 1。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 10,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                },
            },
            axisLabel: {			// 刻度标签。
                show: false,				// 是否显示标签,默认 true。
                distance: 5,			// 标签与刻度线的距离,默认 5。
                color: "#fff",			// 文字的颜色,默认 #fff。
                fontSize: 12,			// 文字的字体大小,默认 5。
                formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
            },
            axisLine: {
                show: true,
                lineStyle: {
                    width: 23,
                    shadowBlur: 0,
                    color: [
                        [0.3, '#26d0ce'],
                        [0.7, '#4DBEEE'],
                        [1, '#F775A9']
                    ]
                }
            },
            data: [{
                value: ${ava},
                name: '预警等级',
            }]
        }]
    };
    myChart.setOption(option);
</script>