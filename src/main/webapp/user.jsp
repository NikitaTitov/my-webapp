<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">

  <title>Add new user</title>
</head>
<body>

<form method="POST" name="frmAddUser">
  User ID : <input type="text" readonly="readonly" name="id"
                   value="<c:out value="${user.id}" />" /> <br/>
  First Name : <input
        type="text" name="user_name"
        value="<c:out value="${user.name}" />" /> <br />
  Last Name : <input
        type="text" name="last_name"
        value="<c:out value="${user.lastName}" />" /> <br />
    Password : <input
        type="text" name="password"
        value="<c:out value="${user.password}" />" /> <br />
    User right : <input
        type="text" name="user_right"
        value="<c:out value="${user.userRight}" />" /> <br />
    <input
            type="submit" value="Submit" />
</form>
</body>
</html>