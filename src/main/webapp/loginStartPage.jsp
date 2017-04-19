<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Numl
  Date: 15.04.2017
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
</head>
<body>

<form method="post">

  Username: <input type="text" name="user_name"
        value="<c:out value="${user.user_name}" />"/>
  <br>
  Password: <input type="text" name="password"
                   value="<c:out value="${user.password}" />"/>
  <br>
  <input type="submit" value="Login">
</form>
</body>
</html>
