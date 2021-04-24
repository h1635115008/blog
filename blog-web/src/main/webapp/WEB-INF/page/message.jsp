<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/zyComment/css/semantic.css"
          type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/zyComment/css/zyComment.css"
          type="text/css"/>
    <link href="${pageContext.request.contextPath }/css/common.css"
          type="text/css" rel="Stylesheet"/>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <title>留言</title>
    <style type="text/css">
        #guestComment {
            width: 99%;
            padding: 1px;
            background: #fff;
            border-radius: 5px;
            color: black;
            margin: 0 auto;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }
    </style>
</head>
<body>
<!-- 背景 -->
<!-- <img id="background" src="img/background2.jpg" /> -->
<!-- 头部 -->
<%@ include file="common/header.jsp" %>
<!-- 加载条 -->
<%@ include file="common/loading.jsp" %>
<!-- 内容 -->
<div class="main">
    <div class="content">
        <div id="guestComment">
            <div style="border-bottom: 1px solid #DFDFDF;margin: 0 auto;font-size: 15px;height: 50px;width: 94%;line-height: 50px;background: #fff;color: black;">
                有什么想和我说的可以在下面给我留言哟！
            </div>
        </div>
    </div>
</div>
<!-- 返回顶部 -->
<div class="back" id="back">
    <i class="iconfont up">&#xe63f;</i>
</div>
<!-- 尾部 -->
<%@ include file="common/footer.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/plugins/zyComment/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/message/reply.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/xss.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/zyComment/js/zyComment.js"></script>
<script type="text/javascript">
    var agoComment = [
        <c:forEach items="${requestScope.guestMessages}" var="message" begin="0" end="${requestScope.guestMessages.size()}">
        {
            "id": "${message.id}",
            "userName": "${message.name}",
            "time": "${message.time}",
            "parentId": ${message.parentId},
            "rootNodeId": ${message.rootNodeId},
            "parentUserName": "<c:out value="${message.parentUserName}" default=""/>",
            "content": "${message.words}"
        },
        </c:forEach>
    ];
    $("#guestComment").zyComment(
        {
            "width": "355",
            "height": "33",
            "agoComment": agoComment,
            "callback": function (data) {
                console.info("填写内容返回值：");
                console.info(data);
                // 添加新的评论
                $("#guestComment").zyComment("setCommentAfter", {
                    "id": data.id,
                    "name": data.name,
                    "content": data.content,
                    "time": data.time
                });
            }
        });
</script>
</body>
</html>