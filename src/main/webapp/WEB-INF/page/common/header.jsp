<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<header>
    <!-- 侧栏 -->
    <%@ include file="nav.jsp" %>
    <a id="name" href="${pageContext.request.contextPath}/">after tomorrow</a>
    <div class="serachBox" onmouseleave="clearWord()">
        <i class="iconfont serachIcon" onclick="clearWord()">&#xe60c;</i>
        <div class="serach">
            <div class="inputBox">
                <input type="text" class="input" name="keywords" id="input"
                       oninput="serach(this,'${pageContext.request.contextPath }')" autocomplete="off"
                       placeholder="keywords"/>
            </div>
            <div class="result" id="result">
            </div>
        </div>
    </div>
</header>