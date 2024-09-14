<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>XML</h3>
<c:set var="spaceships">
    <spaceships>
        <spaceship>
            <name>starship</name>
            <height>121</height>
            <diametr>9</diametr>
            <payloadCapasity>100-150</payloadCapasity>
        </spaceship>
        <spaceship>
            <name>falcon 9</name>
            <height>8</height>
            <diametr>4</diametr>
            <payloadCapasity>800</payloadCapasity>
        </spaceship>
    </spaceships>
</c:set>
<x:parse xml = "${spaceships}" var = "output"/>
<x:out select="$output/spaceships/spaceship[1]/name"/>
<x:if select="$output/spaceships/spaceship[2]/name = 'falcon 9'">
    <p>The second spaceship is Falcon 9.</p>
</x:if>
<x:choose>
    <x:when select="$output/spaceships/spaceship[1]/height > 100">
        <p>The first spaceship is tall.</p>
    </x:when>
    <x:when select="$output/spaceships/spaceship[1]/height < 100">
        <p>The first spaceship is short.</p>
    </x:when>
    <x:otherwise>
        <p>The height of the first spaceship is unknown.</p>
    </x:otherwise>
</x:choose>
</body>
</html>
