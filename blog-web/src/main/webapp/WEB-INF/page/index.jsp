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
    <title>首页</title>
    <link href="${pageContext.request.contextPath }/css/common.css"
          type="text/css" rel="Stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/write/simditor/simditor.css"
          type="text/css" rel="Stylesheet"/>
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <style type="text/css">
        .iconfonts {
            font-family: "iconfont" !important;
            font-size: 12px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -webkit-text-stroke-width: 0.2px;
            -moz-osx-font-smoothing: grayscale;
        }

        .article {
            width: 99%;
            color: #000000;
            padding: 1px;
            margin-bottom: 20px;
            border-radius: 5px;
            margin: 15px auto;
            background: #fff;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .article .head {
            width: 100%;
            height: 50px;
        }

        .article .head .tag {
            background: url(${pageContext.request.contextPath }/img/tags.png);
            float: left;
            background-size: 100% 30px;
            background-repeat: no-repeat;
            text-align: center;
            line-height: 30px;
            padding: 0 15px;
            margin: 10px 0 0px -3px;
            font-size: 13px;
            color: white;
        }

        .article .head .title {
            width: 80%;
            height: 50px;
            font-size: 20px;
            line-height: 50px;
            text-align: center;
            margin: auto;
        }

        .article .head .title .link {
            color: black;
            text-decoration: none;
            display: block;
        }

        .article .head .title .link:hover {
            text-decoration: underline;
        }

        .article .head .more {
            width: 80px;
            height: 25px;
            float: right;
            font-size: 12px;
            line-height: 25px;
            text-align: center;
            border-radius: 5px;
            background: #8a8a8a;
            margin: 15px 10px 0px 10px;
            display: block;
            color: white;
            text-decoration: none;
            border: solid 1px darkgray;
            transition: color 0.3s, background-color 0.3s;
        }

        .article .head .more:hover {
            background: white;
            color: black;
            border: solid 1px black;
        }

        .article .head .more .iconfont {
            font-size: 12px;
        }

        .article .summary {
            width: 96%;
            margin: 10px auto;
            font-size: 16px;
            word-wrap: break-word;
            word-break: break-all;
            color: #8a8a8a;
        }

        .article .other {
            width: 98%;
            height: 30px;
            padding: 1%;
            color: #8a8a8a;
        }

        .article .other .time {
            float: right;
            margin-right: 15px;
        }

        .article .other .kind {
            float: right;
            margin-right: 15px;
        }

        .article .other .view {
            float: right;
            margin-right: 15px;
        }

        @media all and (max-width: 414px) {
            .article .head .more {
                display: none;
            }

            .article .head .tag {
                display: none;
            }

            .article .head .title {
                padding: 5%;
                height: auto;
                width: 90%;
                line-height: normal;
            }

            .article .head {
                text-align: center;
                margin: auto;
                font-size: 25px;
                width: 100%;
                height: auto;
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
        <c:forEach items="${requestScope.articles}" var="article" begin="0" end="${requestScope.articles.size()}">
            <div class="article">
                <div class="head">
                    <div class="tag">
                            ${article.tag.split(",")[0]}
                    </div>
                    <a class="more"
                       href="${pageContext.request.contextPath }/text/${article.id}">查看全文
                        <span class="iconfonts">&#xe635;</span>
                    </a>
                    <div class="title">
                        <a class="link"
                           href="${pageContext.request.contextPath }/text/${article.id}">${article.title}
                        </a>
                    </div>
                </div>
                <div class="summary editor-style">
                        ${article.summary}
                </div>
                <div class="other">
                    <div class="view iconfonts">
                        &#xe662; 浏览次数：
                            ${article.view}
                    </div>
                    <div class="kind iconfonts">
                        &#xe6b1; 标签：
                            ${article.tag.replaceAll(",", " ")}
                    </div>
                    <div class="time iconfonts">
                        &#xe649; 创建时间：
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${article.time}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
        <%@ include file="common/paging.jsp" %>
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