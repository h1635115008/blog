<%--
  Created by IntelliJ IDEA.
  User: ideapad
  Date: 2019/4/8
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Refresh" content="3;${pageContext.request.contextPath}/"/>
    <title>404</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/404/404.css">
    <script src="${pageContext.request.contextPath }/js/libs/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/error/scriptalizer.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $('#parallax').jparallax({});
        });
    </script>
</head>
<body>
<div id="content" class="narrowcolumn">
    <div id="parallax">
        <div class="error1"
             style="position: absolute; left: 80.3846%; margin-left: -1040.98px; top: 64%; margin-top: -444.8px;">
            <img src="${pageContext.request.contextPath }/img/wand.jpg" alt="Mauer">
        </div>
        <div class="error2"
             style="position: absolute; left: 80.3846%; margin-left: -803.846px; top: 64%; margin-top: -400px;">
            <img src="${pageContext.request.contextPath }/img/licht.png" alt="Licht">
        </div>
        <div class="error3"
             style="position: absolute; left: 80.3846%; margin-left: -803.846px; top: 64%; margin-top: -384px;">
            <img src="${pageContext.request.contextPath }/img/halo1.png" alt="Halo1">
        </div>
        <div class="error4"
             style="position: absolute; left: 80.3846%; margin-left: -924.423px; top: 64%; margin-top: -409.6px;">
            <img src="${pageContext.request.contextPath }/img/halo2.png" alt="Halo2">
        </div>
        <div class="error5"
             style="position: absolute; left: 80.3846%; margin-left: -1020.88px; top: 64%; margin-top: -441.6px;">
            <img src="${pageContext.request.contextPath }/img/batman-404.png" alt="Batcave 404">
        </div>
    </div>
    <div class="footer-banner" style="width:728px; margin:0 auto"></div>
</div>
</body>
</html>
