<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>新建方案</title>
  <%@ include file="../linker.jsp" %>
</head>
<style>
  .layui-tab .layui-form-label{
    width: 50px;
  }
</style>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header">新建方案</div>
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="component-form-group">

          <div class="layui-form-item">
            <label class="layui-form-label">方案名称</label>
            <div class="layui-input-block">
              <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入方案名称" class="layui-input">
            </div>
          </div>

          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">预报站</label>
              <div class="layui-input-inline">
                <select name="modules" lay-verify="required" lay-search="">
                  <option value="">直接选择或搜索选择</option>
                  <option value="1">layer</option>
                  <option value="2">form</option>
                  <option value="3">layim</option>
                  <option value="4">element</option>
                  <option value="5">laytpl</option>
                  <option value="6">upload</option>
                  <option value="7">laydate</option>
                  <option value="8">laypage</option>
                  <option value="9">flow</option>
                  <option value="10">util</option>
                  <option value="11">code</option>
                  <option value="12">tree</option>
                  <option value="13">layedit</option>
                  <option value="14">nav</option>
                  <option value="15">tab</option>
                  <option value="16">table</option>
                  <option value="17">select</option>
                  <option value="18">checkbox</option>
                  <option value="19">switch</option>
                  <option value="20">radio</option>
                </select>
              </div>
            </div>
          </div>

          <%--<div class="layui-form-item">--%>
            <%--<label class="layui-form-label">预报模型</label>--%>
            <%--<div class="layui-input-block">--%>
              <%--<input type="radio" name="sex" value="男" title="新安江模型" checked="">--%>
              <%--<input type="radio" name="sex" value="女" title="单位线模型">--%>
              <%--<input type="radio" name="sex" value="禁" title="API模型" disabled="">--%>
            <%--</div>--%>
          <%--</div>--%>

          <div class="layui-form-item">
            <label class="layui-form-label">预报模型</label>
            <div class="layui-input-block">

              <div class="layui-tab" lay-filter="demo">
                <ul class="layui-tab-title">
                  <li class="layui-this" lay-id="11">新安江模型</li>
                  <li lay-id="22">单位线模型</li>
                  <li lay-id="33">API模型</li>
                </ul>
                <div class="layui-tab-content" style="padding-top:20px;">
                  <%--新安江模型--%>
                  <div class="layui-tab-item layui-show">
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">WU0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WU0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">WL0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WL0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">WD0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WD0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">WUM</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WUM" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">WLM</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WLM" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">WDM</label>
                        <div class="layui-input-inline">
                          <input type="text" name="WDM" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">B</label>
                        <div class="layui-input-inline">
                          <input type="text" name="B" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">K</label>
                        <div class="layui-input-inline">
                          <input type="text" name="K" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">C</label>
                        <div class="layui-input-inline">
                          <input type="text" name="C" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">SM</label>
                        <div class="layui-input-inline">
                          <input type="text" name="SM" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">EX</label>
                        <div class="layui-input-inline">
                          <input type="text" name="EX" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">KSS</label>
                        <div class="layui-input-inline">
                          <input type="text" name="KSS" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">KG</label>
                        <div class="layui-input-inline">
                          <input type="text" name="KG" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">IM</label>
                        <div class="layui-input-inline">
                          <input type="text" name="IM" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">CS</label>
                        <div class="layui-input-inline">
                          <input type="text" name="CS" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">CI</label>
                        <div class="layui-input-inline">
                          <input type="text" name="CI" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">CG</label>
                        <div class="layui-input-inline">
                          <input type="text" name="CG" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">L</label>
                        <div class="layui-input-inline">
                          <input type="text" name="L" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">T</label>
                        <div class="layui-input-inline">
                          <input type="text" name="T" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">F</label>
                        <div class="layui-input-inline">
                          <input type="text" name="F" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">S0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="S0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">FR0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="FR0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">QRS0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="QRS0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                      <div class="layui-inline">
                        <label class="layui-form-label">QRSS0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="QRSS0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                    <div class="layui-form-item">
                      <div class="layui-inline">
                        <label class="layui-form-label">FRG0</label>
                        <div class="layui-input-inline">
                          <input type="text" name="FRG0" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                      </div>
                    </div>
                  </div>
                  <%--单位线模型--%>
                  <div class="layui-tab-item">

                  </div>
                  <%--API模型--%>
                  <div class="layui-tab-item">

                  </div>
                </div>
              </div>
            </div>
          </div>
          

          
          

          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="component-form-demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'laydate'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    
    form.render(null, 'component-form-group');

    laydate.render({
      elem: '#LAY-component-form-group-date'
    });
    
    /* 自定义验证规则 */
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      }
      ,pass: [/(.+){6,12}$/, '密码必须6到12位']
      ,content: function(value){
        layedit.sync(editIndex);
      }
    });
    
    /* 监听指定开关 */
    form.on('switch(component-form-switchTest)', function(data){
      layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
        offset: '6px'
      });
      layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });
    
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(data){
      // parent.layer.alert(JSON.stringify(data.field), {
      //   title: '最终的提交信息'
      // })
      $.post({
          url : "http://localhost:8080/Chapter14/role/findRoles.form",
          //此处需要告知传递参数类型为JSON，不能缺少
          contentType : "application/x-www-form-urlencoded",
          //将JSON转化为字符串传递
          data : data.field,
          //成功后的方法
          success : function(result) {

          }
      }).fail(function(response) {
          parent.layer.alert("数据保存失败", {
            title: '错误'
          })
      });
      return false;
    });
  });
  </script>
</body>
</html>
