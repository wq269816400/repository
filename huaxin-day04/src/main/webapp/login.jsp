<%--
  Created by IntelliJ IDEA.
  User: wanq
  Date: 2019/3/21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
</head>

<body>
<form action="login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" /> </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password" /> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录" />
                <input type="reset" value="重置" /></td>
        </tr>
    </table>
</form>
</body>
</html>

</body>
</html>
