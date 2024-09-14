<%--
  Created by IntelliJ IDEA.
  User: Ярослава
  Date: 22.04.2024
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:set var="theString" value="kjnjnojsdfoenb"/>
    <c:out value="${theString}"/>

    <c:if test="${fn:contains(theString, 'sdf')}">
        <p>the sdf<p>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(theString, 'SdF')}">
        <p>the SdF<p>
    </c:if>

    <c:if test="${fn:endsWith(theString, 'enb')}">
        <p>заканчивается enb<p>
    </c:if>
    <p>Index sdf : ${fn:indexOf(theString, "sdf")}</p>

    <c:set var="string1" value="qwerty"/>
    <c:set var="string2" value="${fn:split(string1, ' ')}" />
    <c:set var="string3" value="${fn:join(string2, '-')}" />

    <p>Результат: ${string3}</p>

    <p>Длина : ${fn:length(theString)}</p>

    <c:set var="theString2" value="${fn:replace(theString,
                                'sdf', 'SDF')}" />
    <p>${theString2}</p>
    <c:set var="theString" value="${fn:toLowerCase(theString2)}" />
    <p>${theString}</p>
</body>
</html>
