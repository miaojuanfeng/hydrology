<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Title</title>
    <%@ include file="../common/HeaderCommon.jsp" %>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<table class="layui-hide" id="layerTable" lay-filter="layerFilter"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button id="add" class="layui-btn layui-btn-normal" lay-event="addEvent">
            <i class="layui-icon layui-icon-add-1"></i> <%-- 新增 --%>
        </button>

        <button id="update" style='display:none' class="layui-btn layui-btn-warm" lay-event="updateEvent">
            <i class="layui-icon layui-icon-edit"></i> <%-- 修改 --%>
        </button>

        <button id="remove" style='display:none' class="layui-btn layui-btn-danger" lay-event="deleteEvent">
            <i class="layui-icon layui-icon-delete"></i> <%-- 删除 --%>
        </button>
    </div>
</script>

    <script type="text/javascript">
        layui.use('table', () => {
            let table = layui.table;
            table.render({
                elem: '#layerTable',
                contentType: "application/json",
                dataType: "json",
                method: 'post',
                url:'<c:url value="/product/list"></c:url>',
                limit: 16,
                page: true,
                cellMinWidth: 80,
                toolbar: '#toolbarDemo',
                cols: [[
                    {type:'radio'},
                    {field: 'layerName', title: '类别名称'},
                    {field: 'isFarm', title: '是否农特产'},
                    {field: 'createUser', title: '创建用户'},
                    {field: 'createTime', title: '创建时间', align: 'right'},
                    {field: 'updateTime', title: '更新时间', align: 'right'}
                ]],
                done: () => {
                    $('th').css({'text-align': 'center', 'font-weight' : 'bold'});
                    $('th').eq(4).children().removeAttr('align');
                    $('th').eq(5).children().removeAttr('align');
                }
            });

            //-----------------------------------------------------------------------------------------------

            /**
             * 选中时显示删除按钮
             */
            table.on('radio(layerFilter)', (obj) => {
                if (obj.checked) {
                    $('#update').show();
                    $('#remove').show();
                }
            });

            //-----------------------------------------------------------------------------------------------

            table.on('toolbar(layerFilter)', (obj) => {
                let checkStatus  = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'deleteEvent': //删除事件
                        let data = checkStatus .data;
                        if (data.length) {
                            let json = {
                                "pkId": data[0].id
                            };

                            $.ajax({
                                type : "post",
                                url : "<c:url value="/product/remove"></c:url>",
                                contentType: "application/json",
                                dataType : "json",
                                data :JSON.stringify(json),
                                success : () => {
                                    table.reload('layerTable');
                                }
                            });

                        }
                    break;

                    case 'addEvent': //新增事件
                        layer.open({
                            type: 2,
                            offset: ['150px'],
                            title: '<i class="layui-icon layui-icon-add-1"></i>',
                            anim: 1,
                            skin: 'layui-layer-molv',
                            resize: false,
                            shade:  [0.7, '#000'],
                            content: "<c:url value="/product/addView"></c:url>"
                        });
                    break;

                };
            });

            //-----------------------------------------------------------------------------------------------

        });




    </script>
</body>
</html>
