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
    <title>归档</title>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link href="${pageContext.request.contextPath }/css/common.css"
          type="text/css" rel="Stylesheet"/>
    <style type="text/css">
        .fileBox {
            width: 99%;
            border-radius: 5px;
            color: white;
            padding: 1px;
            background: #fff;
            border-radius: 5px;
            color: black;
            margin: 0 auto;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .fileBox .yearBox {
            width: 93%;
            height: 40px;
            margin: 25px auto;
        }

        .fileBox .yearBox .year {
            width: 86%;
            height: 40px;
            font-size: 30px;
            line-height: 40px;
            float: left;
            color: #8a8a8a;
        }

        .fileBox .yearBox .year:before {
            display: block;
            content: "";
            width: 10px;
            height: 10px;
            border-radius: 5px;
            background: #8a8a8a;
            float: left;
            margin: 15px;
        }

        .fileBox .fileItem {
            width: 86%;
            margin: 25px auto;
            height: 30px;
            display: block;
            color: black;
            border-bottom: 1px #8a8a8a dotted;
        }

        .fileBox .fileItem:hover {
            border-bottom: 2px black dotted;
        }

        .fileBox .fileItem .date {
            width: 45px;
            height: 20px;
            font-size: 10px;
            line-height: 20px;
            float: left;
            text-align: right;
            margin: 3px 10px 0px 12px;
            color: #8a8a8a;;
        }

        .fileBox .fileItem .date:before {
            display: block;
            content: "";
            width: 6px;
            height: 6px;
            border-radius: 4px;
            background: #8a8a8a;
            float: left;
            margin: 7px 0 7px 0;
        }

        .fileBox .fileItem .title {
            width: 80%;
            height: 30px;
            float: left;
            overflow: hidden;
            line-height: 30px;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        @media all and (max-width: 414px) {
            .fileBox .fileItem .title {
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
        <div class="fileBox">
            <c:forEach items="${requestScope.aticleOrderByYears.keySet()}" var="year">
                <div class="yearBox">
                    <div class="year">
                            ${year}
                    </div>
                </div>
                <c:forEach items="${requestScope.aticleOrderByYears.get(year)}" var="article">
                    <a class="fileItem"
                       href="${pageContext.request.contextPath }/text/${article.id}">
                        <div class="date">
                            <fmt:formatDate pattern="MM-dd" value="${article.time}"/>
                        </div>
                        <div class="title">
                                ${article.title}
                        </div>
                    </a>
                </c:forEach>
            </c:forEach>
            <%-- %@ include file="${pageContext.request.contextPath }/common/paging.jsp"%> --%>
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
        src="${pageContext.request.contextPath }/js/common.js"></script>
</body>
</html>