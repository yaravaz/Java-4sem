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
    <title>Login</title>
    <style>
        .login-page {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f1f1f1;
            font-family: Arial, sans-serif;
        }

        .form {
            width: 300px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form p {
            margin: 0 0 10px 0;
            font-weight: lighter;
            font-size: 14px;
        }

        .header {
            font-size: 20px !important;
            font-weight: bold !important;
            color: #333333;
            text-align: center;
            margin-bottom: 20px;
        }

        .label {
            font-size: 14px;
            color: #333333;
        }

        .form input {
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
</body>
<div class = "login-page">
    <div class="form">
        <p><font color="red">${errorMessage}</font></p>
        <form class="login-form" action="${pageContext.servletContext.contextPath}/controller?command=login" method="POST">
            <p class="header"> Вход в систему </p>
            <p class="label"> Имя : <input name="login" type="text" />
            </p>
            <p class="label"> Пароль : <input name="password" type="password" />
            </p>
            <input class ="button-main-page" type="submit" value="Войти"/>
        </form>
        <div>
            <form action="${pageContext.servletContext.contextPath}/controller?command=registration_page" method="post">
                <input class ="button-main-page" type="submit" value="Регистрация"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
</html>
