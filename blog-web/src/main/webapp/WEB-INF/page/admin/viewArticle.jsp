<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>全文</title>
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

        .content .text {
            width: 99%;
            background: #fff;;
            margin: 0 auto;
            border-radius: 5px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .editor-style {
            padding: 5%;
        }

        .text .title {
            text-align: center;
            margin: auto;
            font-size: 25px;
            padding: 5%;
            color: black;
        }

        .text .other {
            width: 98%;
            height: 30px;
            padding: 1%;
            color: #8a8a8a;
        }

        .text .other .time {
            float: right;
            margin-right: 15px;
        }

        .text .other .kind {
            float: right;
            margin-right: 15px;
        }

        .text .other .view {
            float: right;
            margin-right: 15px;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="content">
        <div class="text">
            <div class="title">${article.title}</div>
            <div class="editor-style">${article.code}</div>
            <div class="other">
                <div class="view iconfonts">&#xe662; 浏览次数：${article.view}</div>
                <div class="kind iconfonts">
                    &#xe6b1; 标签：${article.tag.replaceAll(",", " ")}
                </div>
                <div class="time iconfonts">
                    &#xe649; 创建时间：${article.time}
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>