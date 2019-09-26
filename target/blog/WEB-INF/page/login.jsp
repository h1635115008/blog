<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/css/login/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css">
    <link rel="icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath }/img/title.ico"
          type="image/x-icon">
</head>
<body>

<div id="particles-js">
    <form class="login" method="post" action="${pageContext.request.contextPath }/login">
        <div class="login-top">登录</div>
        <div class="login-message">${message}</div>
        <div class="login-center clearfix">
            <div class="login-center-img"><i class="iconfont">&#xe643;</i></div>
            <div class="login-center-input">
                <input type="text" name="username" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入您的用户名'" required="required"/>
                <div class="login-center-input-text">用户名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><i class="iconfont">&#xe642;</i></div>
            <div class="login-center-input">
                <input type="password" name="password" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入您的密码'" required="required"/>
                <div class="login-center-input-text">密码</div>
            </div>
        </div>
        <div class="login-button">
            <input type="submit" value="登录"/>
        </div>
    </form>
</div>
</body>
</html>