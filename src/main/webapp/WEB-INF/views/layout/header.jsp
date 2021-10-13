<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- 시큐리티 메뉴얼 : https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
<!-- 시큐리티 태그라이브러리 : https://www.baeldung.com/spring-security-taglibs -->
<!-- isAuthenticated() : 인증이 되었는지 확인 -->
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- script문 같은 경우에는 /body 태그와 가장 가까운 곳에 둔다. 위에서부터 내려오기 때문 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    <style>
        *{font-family: 'GmarketSansLight';}
        @font-face {
            font-family: 'GmarketSansLight';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">

        <sec:authorize access="isAnonymous()">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/auth/loginForm">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="/auth/joinForm">Join</a></li>
            </ul>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/board/savaForm">Write</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/updateForm">Info</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </sec:authorize>
        <%--    <c:choose>--%>
        <%--      <c:when test="${empty sessionScope.login}">--%>
        <%--        <ul class="navbar-nav">--%>
        <%--          <li class="nav-item"><a class="nav-link" href="/auth/loginForm">Login</a></li>--%>
        <%--          <li class="nav-item"><a class="nav-link" href="/auth/joinForm">Join</a></li>--%>
        <%--        </ul>--%>
        <%--      </c:when>--%>
        <%--      <c:otherwise>--%>
        <%--        <ul class="navbar-nav">--%>
        <%--          <li class="nav-item"><a class="nav-link" href="/board/form">Write</a></li>--%>
        <%--          <li class="nav-item"><a class="nav-link" href="/user/form">Info</a></li>--%>
        <%--          <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>--%>
        <%--        </ul>--%>
        <%--      </c:otherwise>--%>
        <%--    </c:choose>--%>


    </div>
</nav>
<br/>