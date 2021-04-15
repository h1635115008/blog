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
        .main .content .replace {
            width: 96%;
            height: 700px;
            background: #fff;
            border-radius: 5px;
            margin: 40px auto;
            border: solid 1px rgba(0, 0, 0, 0);
            margin-bottom: 100px;
            color: black;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .main .content .replace img {
            display: block;
            width: 100px;
            height: 100px;
            margin: 0 auto;
            margin-top: 150px;
        }

        .main .content .replace pre {
            width: 200px;
            height: 30px;
            margin: 30px auto;
            text-align: center;
        }

        .main .content .messageBlock {
            width: 94%;
            background: white;
            border-radius: 5px;
            margin: 20px auto;
            padding: 10px;
            background: #fff;
            color: black;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04);
        }

        .main .content .replyBlock {
            width: 83.5%;
            border-radius: 5px;
            padding: 10px;
            background: rgba(97, 109, 152, 0.8);
            margin-left: 10%;
            margin-bottom: 10px;
        }

        .main .content .replyBlock .message img {
            width: 30px;
            height: 30px;
            border: solid 1px white;
            display: block;
            border-radius: 23px;
            float: left;
            height: 30px;
            border: solid 1px white;
            display: block;
            border-radius: 23px;
        }

        .replyBlock .message .nameTime {
            width: 150px;
            height: 45px;
            float: left;
            margin-left: 20px;
            margin-top: 2px;
            line-height: 15px;
        }

        .replyBlock .message .nameTime .guestName {
            height: 15px;
            display: block;
            font-size: 12px;
            line-height: 15px;
        }

        .replyBlock .message .nameTime .time {
            height: 15px;
            font-size: 12px;
            display: block;
        }

        .replyBlock .message {
            width: 100%;
            height: 45px;
        }

        .replyBlock .words {
            width: 94%;
            margin: 10px 10px;
            word-wrap: break-word;
        }

        .main .content .messageBlock .message {
            width: 100%;
            height: 45px;
        }

        .messageBlock .message img {
            width: 40px;
            height: 40px;
            border: solid 1px #8a8a8a;
            display: block;
            border-radius: 23px;
            float: left;
        }

        .messageBlock .message .nameTime {
            width: 150px;
            height: 45px;
            float: left;
            margin-left: 20px;
            margin-top: 2px;
        }

        .messageBlock .message .nameTime .guestName {
            height: 25px;
            display: block;
        }

        .messageBlock .message .nameTime .time {
            height: 15px;
            font-size: 10px;
            display: block;
            color: #8a8a8a;
        }

        .messageBlock .message .floor {
            width: 30px;
            float: right;
            height: 45px;
            line-height: 45px;
            display: block;
            color: #8a8a8a;
        }

        .main .content .messageBlock .words {
            width: 93%;
            margin: 30px 20px;
            word-wrap: break-word;
            white-space: pre-wrap;
        }

        .main .content .messageBlock .reply {
            display: block;
            width: 30px;
            height: 20px;
            color: #8a8a8a;
            text-decoration: none;
            font-size: 12px;
            margin-left: 95%;
        }

        .main .content form {
            width: 100%;
            height: 300px;
            margin: 0 auto;
        }

        .main .content form .input {
            width: 32%;
            float: left;
        }

        .main .content form .input span {
            color: #8a8a8a;
            width: 48px;
        }

        .main .content form .input input {
            background: #fff;
            color: #8a8a8a;
            width: 168px;
            outline: none;
        }

        .main .content form .input #submit {
            border: none;
            margin-left: 60px;
            width: 80px;
            border-radius: 5px;
            height: 20px;
            color: white;
            background: #8a8a8a;
            transition: color 0.3s, background-color 0.3s;
        }

        .main .content form .input #submit:hover {
            background: white;
            border: 1px solid black;
            color: black;
        }

        @media all and (max-width: 414px) {
            .main .content form .input {
                width: 220px;
                margin: 10px auto;
                float: none;
            }

            .main .content form .input #submit {
                float: right;
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
        <p style="margin: 0 auto; font-size: 15px; height: 50px; padding: 0 10px; width: 94%; line-height: 50px; background: #fff; color: black; box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.04); border-radius: 5px;">
            有什么想和我说的可以在下面给我留言哟！</p>
        <c:choose>
            <c:when test="${requestScope.guestMessages.size() <= 0}">
                <div class="replace">
                    <img src="${pageContext.request.contextPath }/img/nothing.png">
                    <p style="text-align: center; margin-top: 30px">还没有人给我留言 >_<|||</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.guestMessages}" var="guestMessage">
                    <div class="messageBlock">
                        <div class="message">
                            <img class="guestIcon"
                                 src="${pageContext.request.contextPath }/img/guestIcon.png"
                                 onerror="onerror();">
                            <div class="nameTime">
                                <span class="guestName">${guestMessage.name} </span>
                                <span class="time">
                                    <fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${guestMessage.time}"/>
                                </span>
                            </div>
                            <span class="floor">F${guestMessage.id}</span>
                        </div>
                        <pre class="words">${guestMessage.words}</pre>
                        <a href="#textarea" class="reply">回复</a>
                    </div>
                    <c:forEach items="${guestMessage.messageReplys}" var="messageReply">
                        <div class="replyBlock">
                            <div class="message">
                                <img class="guestIcon"
                                     src="${pageContext.request.contextPath }/img/replyIcon.png"
                                     onerror="onerror();">
                                <div class="nameTime">
                                    <span class="guestName">${messageReply.name} </span>
                                    <span class="time">
                                        <fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${messageReply.time}"/>
                                    </span>
                                </div>
                            </div>
                            <pre class="words">${messageReply.words}</pre>
                        </div>
                    </c:forEach>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <form action="${pageContext.request.contextPath }/addMessage"
              method="post">
				<textarea rows="10" cols="100" value="提交"
                          style="margin: 20px auto;display: block;width: 92%;height: 160px;border-radius: 5px;padding: 10px;font-size: 15px;background: #fff;color: #8a8a8a;outline: none;border: 1px solid #8a8a8a;"
                          name="words" maxlength="500" wrap="hard" id="textarea"></textarea>
            <div style="margin: 0px auto; width: 92%;">
                <div class="input"><span>昵称：</span><input type="text" name="name" required="required"/></div>
                <div class="input"><span>邮箱：</span><input type="email" name="email" required="required"/></div>
                <div class="input"><input type="submit" value="提交" id="submit"/></div>
            </div>
        </form>
    </div>
</div>
<!-- 返回顶部 -->
<div class="back" id="back">
    <i class="iconfont up">&#xe63f;</i>
</div>
<!-- 尾部 -->
<%@ include file="common/footer.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/message/reply.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/xss.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/libs/jquery.min.js"></script>
</body>
</html>