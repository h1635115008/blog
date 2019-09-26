<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>关于</title>
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
        .content .about {
            width: 99%;
            min-height: 800px;
            background: #fff;
            margin: 0 auto;
            border-radius: 5px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .editor-style {
            padding: 5%;
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
        <div class="about">
            <div class="editor-style">
                <ul>
                    <li><b style="font-size: large;">个人状态</b></li>
                </ul>
                <p>
                    <font>目前大四在读，正在积极找工作。工作意向是Java开发工程师。下面是个人技能。</font>
                </p>
                <ul>
                    <li>编程语言：Java (熟练掌握)/ C语言 (熟练掌握)/ JavaScript (熟练使用)</li>
                    <li>前端框架：JQuery (熟悉)/ BootStrap (熟悉)</li>
                    <li>后端框架：Spring (熟练使用)/ SpringMVC (熟练使用)/ MyBatis (熟练使用)/ SpringBoot (了解)
                    </li>
                    <li>关系型数据库：MySQL (熟练使用，了解SQL优化、MySQL主从复制和读写分离)</li>
                    <li>非关系型数据库：Redis (了解Redis分布式高可用集群)</li>
                    <li>缓存框架：Ehcache (了解)</li>
                    <li>版本控制、项目构建管理工具：Git / Maven (熟练使用)</li>
                    <li>操作系统：Linux / Windows （熟练使用/Linux服务器上部署过项目，熟悉并能编写shell脚本）</li>
                    <li>网络协议：TCP/IP协议族 (了解) / HTTP协议 (掌握）</li>
                    <li>算法与数据结构：基础算法和数据结构 (掌握)</li>
                </ul>
                <ul>
                    <li>
                        <font><b style="font-size: large;">友情链接</b></font>
                    </li>
                </ul>
                <p>期待你的加入。。。</p>
                <p>
                    <font><b><br></b></font>
                </p>
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
        src="${pageContext.request.contextPath }/js/common.js"></script>
</body>
</html>