<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Ярослава
  Date: 22.04.2024
  Time: 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            display: flex;
            flex-direction: column;
        }
    </style>
</head>
<body>
    <h3>Вывод текста</h3>
    <c:out value="12*5+8"/>
    <c:out value="${12*5+8} "/>

    <c:set var="user" scope="session" value="name"/>
    <c:out value="${user}"/>

    <c:remove var="user" scope="session"/>

    <h3>If</h3>
    <c:set var="salary" scope="session" value="${23400*2}"/>
    <c:if test="${salary > 45000}">
        <p>Salary = <c:out value="${salary}"/></p></c:if>

    <h3>Choose</h3>
    <c:set var="alter" scope="session" value="${50}"/>
    <c:choose>
        <c:when test="${alter <= 28}">
            Переменная alter <= 0
        </c:when>
        <c:when test="${alter > 28}">
            Переменная alter > 28
        </c:when>
        <c:otherwise>
            Не выбрано ни одно условие.
        </c:otherwise>
    </c:choose>

    <h3>Foreach</h3>
    <c:forEach var="i" begin="${1}" end="${10}"  >
        <c:out value="${i}"/>
    </c:forEach>

    <h3>Catch</h3>
    <c:catch var ="error">
        <% int x = 1 / 0;%>
    </c:catch>
    <c:if test="${error != null}">
        <p>Ошибка: ${error.message}</p>
    </c:if>
</body>
</html>
