<%@ page import="com.example.lab10.classes.DateBase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10.classes.University" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="Header.jsp"/>

<div id="container">
    <div id="add-remove-div">
        <form id="add-form" method="post" action="FirstServlet">"/>
            <input type="text" placeholder="Рейтинг" name="rating"/>
            <input type="text" placeholder="Адрес" name="address"/>
            <button type="submit">Добавить</button>
        </form>
        <br/><br/>
        <form id="remove-form" method="get" action="FirstServlet">
            <h3 style="font-weight: bold">Удалить университет</h3>
            <input type="text" placeholder="id" name="id"/>
            <button type="submit">Удалить</button>
        </form>
    </div>

    <div id="tableDiv">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Рейтинг</th>
                <th>Адресс</th>
            </tr>
            </thead>

            <tbody>
            <%
                try {
                    DateBase db = new DateBase();
                    db.getConnection();
                    ArrayList<University> universities = new ArrayList<>(db.getClasses());
                    for (University uc : universities) {
            %>
            <tr>
                <td><%=uc.getUniversityId()%></td>
                <td><%=uc.getUniversityName()%></td>
                <td><%=uc.getUniversityRating()%></td>
                <td><%=uc.getUniversityAddress()%></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            </tbody>
        </table>
    </div>

</div>


<jsp:include page="Footer.jsp"/>

</body>
</html>