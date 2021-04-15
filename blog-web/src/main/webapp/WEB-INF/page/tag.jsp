<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <title>标签</title>
    <link href="${pageContext.request.contextPath }/css/common.css"
          type="text/css" rel="Stylesheet"/>
    <style type="text/css">
        .tags {
            width: 99%;
            background: rgb(39, 40, 34);
            border-radius: 5px;
            padding: 1px;
            background: #fff;
            border-radius: 5px;
            color: white;
            margin: 0 auto;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .tags .tagBox {
            width: 93%;
            height: 40px;
            margin: 25px auto;
        }

        .tags .tagBox .tag_ {
            width: 86%;
            height: 40px;
            font-size: 30px;
            line-height: 40px;
            float: left;
            color: #8a8a8a;
        }

        .tags .tagItem {
            width: 86%;
            margin: 25px auto;
            height: 30px;
            display: block;
            color: white;
            border-bottom: 1px #8a8a8a dotted;
        }

        .tags .tagItem:hover {
            border-bottom: 2px black dotted;
        }

        .tags .tagItem .date {
            width: 80px;
            height: 20px;
            font-size: 10px;
            line-height: 20px;
            float: left;
            text-align: right;
            margin: 3px 10px 0px 12px;
            color: #8a8a8a;
        }

        .tags .tagItem .date:before {
            display: block;
            content: "";
            width: 6px;
            height: 6px;
            border-radius: 4px;
            background: darkgray;
            float: left;
            margin: 7px 0 7px 0;
        }

        .tags .tagItem .title {
            width: 80%;
            height: 30px;
            float: left;
            color: #000;
            overflow: hidden;
            line-height: 30px;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        @media all and (max-width: 414px) {
            .tags .tagItem .title {
                width: 190px;
            }
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
        <div class="tags">
            <c:forEach items="${requestScope.aticleOrderByTags.keySet()}" var="tag">
                <div class="tagBox">
                    <div class="tag_ iconfont">${requestScope.aticleOrderByTags[tag][0].unicode}
                            ${tag}</div>
                </div>
                <c:forEach items="${requestScope.aticleOrderByTags.get(tag)}" var="article">
                    <a class="tagItem"
                       href="${pageContext.request.contextPath }/text/${article.id}">
                        <div class="date"><fmt:formatDate pattern="yy-MM-dd" value="${article.time}"/>
                        </div>
                        <div class="title">${article.title}
                        </div>
                    </a>
                </c:forEach>
            </c:forEach>
        </div>
        <!--  %@ include file="${pageContext.request.contextPath }/common/paging.jsp"%> -->
    </div>
</div>
<!-- 返回顶部 -->
<div class="back" id="back">
    <i class="iconfont up">&#xe63f;</i>
</div>
<!-- 尾部 -->
<%@ include file="common/footer.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/common.js"></script>
</body>
</html>