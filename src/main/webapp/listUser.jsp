<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <title>Show All Users</title>
</head>
<body>
<table border=1>
  <thead>
  <tr>
    <th>User Id</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>User Right</th>
    <th colspan=2>Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="user">
    <tr>
      <td><c:out value="${user.id}" /></td>
      <td><c:out value="${user.name}" /></td>
      <td><c:out value="${user.lastName}" /></td>
      <td><c:out value="${user.userRight}" /></td>
      <td><a href="/admin/update?id=<c:out value="${user.id}"/>">Update</a></td>
      <td><a href="/admin/delete?id=<c:out value="${user.id}"/>">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<p><a href="/admin/create">Add User</a></p>
</body>
</html>