<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../layout/header.jsp" %>

<div class="container">

<form>
  <input type="hidden" id="id" value="${principal.user.id}" />
  <div class="form-group">
    <label for="username">Username</label>
    <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
  </div>
  <c:choose>
    <c:when test="${empty principal.user.oauth}">
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password">
      </div>
      <div class="form-group">
        <label for="email">Email address</label>
        <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
      </div>
      <button type="button" id="btn-update" class="btn btn-primary">회원수정완료</button>
</form>
    </c:when>
  <C:otherwise>
    <div class="form-group">
      <label for="email">Email address</label>
      <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" readonly>
    </div>
  </C:otherwise>
  </c:choose>





</div>

<script>
</script>



<!-- /를 붙이면 자동으로 static을 찾아간다. -->
<script src="/js/user.js"></script>



<%@ include file ="../layout/footer.jsp" %>




