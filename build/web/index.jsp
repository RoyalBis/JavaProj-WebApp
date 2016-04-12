<%--
  Created by IntelliJ IDEA.
  User: 723403
  Date: 4/4/2016
  Time: 2:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="http://localhost:8080/rest/user/search?employeeId=2">http://localhost:8080/rest/user/search?CustomerId=2</a>
  <br/>
  <a href="http://localhost:8080/rest/user/search?employeeName=2">http://localhost:8080/rest/user/search?CustomerId=2</a>
  <br/>

  <form method="post" action="http://localhost:8080/rest/user/login">
    <label>Username:</label>
    <input name="UserName" value="Laetia.Enison"/>
    <label>Password:</label>
    <input name="Password" value="123ABC"/>
    <input type="submit" value="submit"/>
  </form>
  <form method="post" action="http://localhost:8080/rest/user/register">
    <br/><br/><br/>
    inputData
    <br/>
    <br>
<textarea rows="8" cols="60" name="inputData" type="text">
{
    "UserName": "Laetia.Enison",
    "Password": "123ABC"
}
</textarea><br><br>
    <input type="submit" value="submit"/>
  </form>
  </body>
</html>
