<%@ page import="com.example.lab13.model.University" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab13.command.groupuniversities.Sub" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin page</title>
    <style>
        body{
            background-color: #f8f8f8;
        }
        #container {
            display: flex;
            flex-direction: row;
            align-items: center;
            text-align: center;
            margin-top:15%;
            margin-left:30%
        }

        #tableDiv {
            margin-right:30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        #add-remove-div {
            text-align: center;
        }

        #add-form {
            display: flex;
            flex-direction: column;
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        h3 {
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 200px;
            padding: 5px;
            margin: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div id="container">
    <div id="tableDiv">
        <%
            Sub subscriber = new Sub();
            subscriber.startListen();
        %>
        <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Рейтинг</th>
                <th>Адрес</th>
            </tr>
            </thead>

            <tbody>
            <%
                List<University> universities = (List<University>) request.getAttribute("universities");
                if (universities != null && !universities.isEmpty()) {
                    for (University university : universities) {
            %>
            <tr>
                <td><%= university.getName() %></td>
                <td><%= university.getRating() %></td>
                <td><%= university.getAddress() %></td>
            </tr>
            <%
                    }
                }
            %>

            </tbody>
        </table>
    </div>


    <div id="add-remove-div">
        <form id="add-form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=add_new_university">
            <h3 style="font-weight: bold">Добавить университет</h3>
            <input type="text" placeholder="Название" name="nname"/>
            <input type="text" placeholder="Рейтинг" name="nrating"/>
            <input type="text" placeholder="Адрес" name="naddress"/>
            <button type="submit">Добавить</button>
        </form>
        <br/><br/>
    </div>

</div>


<jsp:include page="Footer.jsp"/>

</body>
</html>