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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 文章管理 <span
        class="c-gray en">&gt;</span> 文章列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="datadel()"
                                                               class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a
            class="btn btn-primary radius" data-title="添加文章" data-href="article-add.html"
            onclick="article_add('文章添加','${pageContext.request.contextPath}/admin/addArticle')"
            href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加文章</a></span> <span
            class="r">共有文章：<strong>${articles.size()}</strong> 篇</span></div>
    <!--onclick="Hui_admin_tab(this)"-->
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">ID</th>
                <th>标题</th>
                <th width="80">分类</th>
                <th width="120">更新时间</th>
                <th width="75">浏览次数</th>
                <th width="60">发布状态</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
                <tr class="text-c">
                    <td><input type="checkbox" value="${article.id}" name="ids"></td>
                    <td>${article.id}</td>
                    <td class="text-l"><u style="cursor:pointer" class="text-primary"
                                          onClick="article_edit('查看','${pageContext.request.contextPath}/admin/viewArticle/${article.id}')"
                                          title="查看">${article.title}</u>
                    </td>
                    <td>${article.tag.replaceAll(","," ")}</td>
                    <td>${article.time}</td>
                    <td>${article.view}</td>
                    <td class="td-status">
                        <c:choose>
                            <c:when test="${article.status==0}">
                                <span class="label label-success radius">草稿</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-success radius">已发布</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="f-14 td-manage">
                        <c:if test="${article.status == 0}">
                            <a style="text-decoration:none" onClick="article_start(this,${article.id})"
                               href="javascript:;"
                               title="发布"><i
                                    class="Hui-iconfont">&#xe603;</i></a>
                        </c:if>
                        <a style="text-decoration:none" class="ml-5"
                           onClick="article_edit('文章编辑','${pageContext.request.contextPath}/admin/editArticle/${article.id}')"
                           href="javascript:;"
                           title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
                        <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'${article.id}')"
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
            {"orderable": false, "aTargets": [0, 7]}// 不参与排序的列
        ]
    });

    /*文章-添加*/
    function article_add(title, url, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*文章-编辑*/
    function article_edit(title, url, id, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*文章-删除*/
    function article_del(obj, id) {
        var ids = new Array();
        ids.push(id);
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/admin/deleteArticle',
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
                    url: '${pageContext.request.contextPath}/admin/deleteArticle',
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

    /*文章-发布*/
    function article_start(obj, id) {
        layer.confirm('确认要发布吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/admin/editArticle',
                dataType: 'json',
                data: "id=" + id + "&status=1",
                success: function (data) {
                    if (data.success == true) {
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                        $(obj).remove();
                        layer.msg('已发布!', {icon: 6, time: 1000});
                    } else {
                        layer.msg('发布失败!', {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                }
            });
        });
    }
</script>
</body>
</html>