<%--
  Created by IntelliJ IDEA.
  User: Ярослава
  Date: 14.04.2024
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .errorMessage {
            color: red;
            font-size: 14px;
            text-align: center;
        }

        .header {
            text-align: center;
            font-size: 20px !important;
            font-weight: bold !important;
            color: #333333;
            margin-bottom: 30px;
        }

        .label {
            font-size: 14px;
            color: #333333;
        }

        form {
            width: 300px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        form p {
            margin: 0 0 10px 0;
            font-weight: lighter;
        }

        form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .button-main-page {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .button-main-page:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/controller?command=register_new_user" method="POST">
    <p class="errorMessage"><font color="red">${errorRegister}</font></p>
    <p class="header"> Регистрация нового пользователя </p>
    <p class="label"> Введите имя : <input name="newLoginName" type="text" />
    </p>
    <p class="label"> Введите пароль : <input name="newPassword" type="password" />
    </p>
    <input class ="button-main-page" type="submit" value="Зарегистрировать"/>
</form>
</body>
</html>
