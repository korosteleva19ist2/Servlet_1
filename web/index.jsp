<%--
  Created by IntelliJ IDEA.
  User: Лезок
  Date: 20.10.2023
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@page import="ru.appline.logic.Model" %> <%--на java создаем ипорт --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите id пользователя (0-вывод всего списка пользователей) <br/>
  </body>
<%--динамически количество доступных пользователей в нашей мапе --%>

  Доступно: <%
    Model model=Model.getInstance();
    out.print(model.getFromList().size()); //выведет количество пользователей в мапе
  %>
<form method="get" action="get">
  <label>ID
    <input type="text" name="id"><br/>

  </label>
  <button type="submit">Поиск</button>
</form>
<a href="addUser.html">Добавить пользователя</a>
</html>
