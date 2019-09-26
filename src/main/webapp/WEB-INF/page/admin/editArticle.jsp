<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" charset="utf-8"
          content="width=device-width, initial-scale=1">
    <title>编辑文章</title>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/h-ui.admin/css/style.css"/>
    <link media="all" rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/write/simditor/app.css">
    <link media="all" rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/write/simditor/simditor.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/write/simditor/mobilecheck.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/write/option.js"></script>
    <script src="${pageContext.request.contextPath }/js/write/sweet-alert.js"></script>
    <link href="${pageContext.request.contextPath }/css/write/alert/allf.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/css/write/select/fSelect.css"
          rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/write/alert/sweet-alert.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/write/simditor/simditor-html.css"
          media="screen" charset="utf-8"/>
    <script type="text/javascript">
        if (mobilecheck()) {
            $('<link/>', {
                media: 'all',
                rel: 'stylesheet',
                type: 'text/css',
                href: '${pageContext.request.contextPath }/css/write/simditor/mobile.css'
            }).appendTo('head')
        }
    </script>
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-article-add"
          action="${pageContext.request.contextPath}/admin/addArticle" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${article.title}" placeholder="" id="title" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">标签分类：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="tag" multiple="multiple" id="tag" name="tag">
                    <c:forEach var="kind" items="${requestScope.tagOrderByKinds.keySet()}">
                        <optgroup label="${kind}">
                            <c:forEach var="tag" items="${requestScope.tagOrderByKinds[kind]}">
                                <option value="${tag.name}">${tag.name}
                                </option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">关键词：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${article.keywords}" placeholder="" id="keywords"
                       name="keywords">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章概述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="summary" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符"
                          datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！">${article.summary}</textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章内容：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <section id="my-editor">
                    <textarea id="txt-content" data-autosave="editor-content" name="code" autofocus
                              required>${article.code}</textarea>
                </section>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onclick="article_save_submit();" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont"></i> 保存提交
                </button>
                <button onclick="article_save();" class="btn btn-secondary radius" type="button"><i
                        class="Hui-iconfont"></i> 保存草稿
                </button>
            </div>
        </div>
    </form>
</article>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer /作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/write/simditor/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/write/simditor/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/write/simditor/uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/write/simditor/simditor.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/write/simditor/beautify-html.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/write/simditor/simditor-html.js"></script>
<script src="${pageContext.request.contextPath }/js/write/fSelect.js"></script>
<script type="text/javascript">
    var $preview, editor, mobileToolbar, toolbar;
    Simditor.locale = 'en-US';
    toolbar = ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment', 'html'];
    mobileToolbar = ["bold", "underline", "strikethrough", "color", "ul", "ol"];
    if (mobilecheck()) {
        toolbar = mobileToolbar;
    }
    editor = new Simditor({
        textarea: $('#txt-content'),
        placeholder: '这里输入文字...',
        toolbar: toolbar,
        pasteImage: true,
        fileKey: 'upload_file',
        defaultImage: '${pageContext.request.contextPath }/img/about.png',
        upload: {
            url: '${pageContext.request.contextPath }/upload'
        }
    });
    //多选框
    $('.tag').fSelect($.extend({
        placeholder: '',
        numDisplayed: 10,
        overflowText: '{n} selected',
        searchText: 'Search',
        showSearch: true
    }));

    //文章保存提交
    function article_save_submit() {
        save(1, "保存提交")
    }

    function article_save() {
        save(0, "保存草稿");
    }

    /*字数-限制*/
    $(".textarea").Huitextarealength({
        minlength: 10,
        maxlength: 300
    });

    function save(status, msg) {
        layer.confirm('确认' + msg + '吗？', function (index) {
            var text = $(".simditor-body").text();
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/admin/editArticle',
                dataType: 'json',
                data: $('#form-article-add').serialize() + encodeURI("&status=" + status + "&text=" + text + "&id=${article.id}"),
                success: function (data) {
                    if (data.msg == "success") {
                        layer.msg('已保存!', {icon: 1, time: 1000});
                    } else {
                        layer.msg('保存失败!', {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                }
            });
        });
    }

    //文档就绪
    $(function () {
        $(".fs-label").html("${article.tag}");
        <c:forEach var="tag" items="${article.tag.split(',')}">
        $(".fs-option[data-value=${tag}]").addClass("selected");
        $("option[value=${tag}]").attr("selected", "selected");
        </c:forEach>
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>