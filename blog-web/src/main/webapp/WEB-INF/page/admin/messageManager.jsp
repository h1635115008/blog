<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" charset="utf-8"
          content="width=device-width, initial-scale=1">
    <title>文章管理</title>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 留言管理 <span
        class="c-gray en">&gt;</span> 留言列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="datadel()"
                                                               class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span>
        <span class="r">共有留言：<strong>${messages.size()}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">ID</th>
                <th width="120">昵称</th>
                <th width="200">邮箱</th>
                <th>内容</th>
                <th width="75">留言时间</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="message">
                <tr class="text-c">
                    <td><input type="checkbox" value="${message.id}" name="ids"></td>
                    <td>${message.id}</td>
                    <td>${message.name}</td>
                    <td>${message.email}</td>
                    <td>${message.words}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${message.time}"/></td>
                    <td class="f-14 td-manage">
                        <a style="text-decoration:none" class="ml-5" onClick="message_del(this,'${message.id}')"
                           href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    $('.table-sort').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 6]}// 不参与排序的列
        ]
    });

    /*留言-删除*/
    function message_del(obj, id) {
        var ids = new Array();
        ids.push(id);
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/admin/deleteMessage',
                dataType: 'json',
                data: {ids: ids},
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                }
            });
        });
    }

    /*批量-删除*/
    function datadel() {
        var ids = new Array();
        var objs = new Array();
        $("input[type='checkbox']").each(function () {
            if ($(this).prop("checked") && $(this).val() !== "") {
                ids.push($(this).val());
                objs.push($(this));
            }
        });
        layer.confirm('确认要删除吗？', function (index) {
            if (objs.length == 0) {
                layer.msg('没有选择数据!', {icon: 0, time: 1000});
            } else {
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/admin/deleteMessage',
                    dataType: 'json',
                    data: {ids: ids},
                    success: function (data) {
                        for (var i = 0; i < objs.length; i++) {
                            objs[i].parents("tr").remove();
                        }
                        layer.msg('已删除!', {icon: 1, time: 1000});
                    },
                    error: function (data) {
                        console.log(data.msg);
                    }
                });
            }
        });
    }
</script>
</body>
</html>