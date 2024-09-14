<%--
  Created by IntelliJ IDEA.
  User: Ярослава
  Date: 22.04.2024
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h3>Formatting date</h3>
<c:set var="now" value="<%= new java.util.Date()%>"/>
<fmt:formatDate type="time" value="${now}"/>
<fmt:setLocale value="ru-RU"/>
<fmt:timeZone value="GMT+3:00">
    Сегодня: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/> <br/>
</fmt:timeZone>
<c:set var="date" value="19-09-2020" />
<fmt:parseDate value="${date}" var="parsedDate" pattern="dd-MM-yyyy" />
Парсинг строковой даты: <c:out value="${parsedDate}" />
<br/>
Стиль времени:
<br/>
<fmt:formatDate value="${now}" type="time" timeStyle="short" /><br/>
<fmt:formatDate value="${now}" type="time" timeStyle="medium" /><br/>
<fmt:formatDate value="${now}" type="time" timeStyle="long" /><br/><br/>
<h3>Formatting numbers</h3>
<c:set var="currentNumber" value="123"/>
<c:out value="${currentNumber}"/> <br/>
<fmt:formatNumber value="${currentNumber}" /><br/>
Проценты: <fmt:formatNumber value="${currentNumber}" type="percent"/><br/>
<fmt:setLocale value="en_US"/>
<fmt:formatNumber value= "${currentNumber}" type="currency"/><br/>
</body>
</html>
