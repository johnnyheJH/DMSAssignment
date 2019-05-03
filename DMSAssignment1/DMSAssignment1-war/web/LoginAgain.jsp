<%-- 
    Document   : Login
    Created on : 2019-5-2, 21:15:24
    Author     : Iris-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
     <body>
        
        <form action="LoginServlet" method="post">
        <center>
            <table>
                <tr>
                    <h3>Welcome to Book Management System</h3>                    
                    Please login if you have an account. Register if you don't.
                                        
                </tr>
                <tr>
                    <td  width="92" align="center">Username:</td>
                    <td width="232"><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td align="center">Password:</td>
                <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                    <input name="loginButton" type="submit" value="Login"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                    <input name="registerButton" type="submit" value="Register"></td>
                </tr>
            </table>
                    <h3><font color="red">Customer not found or Password wrong</h3>                    
        </center>
        </form>

    </body>
</html>
