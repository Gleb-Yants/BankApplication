<%@ page contentType="text/html; charset=windows-1251" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
    <head>
        <title>Добавить клиента</title>
    </head>
 
    <body>
        <form action="<c:url value="/addClient"/>" method="POST">
<p/>
        <b>Добавить клиента<b>
            <br/>
            <table>
                <tr>
                    <td>Имя:</td><td><input type="text" name="name" value="${client.name}"/></td>
                </tr>
                <tr>
                    <td>Адрес:</td><td><input type="text" name="address" value="${client.address}"/></td>
                </tr>
                
            </table>
            <table>
                <tr>
                    <td><input type="submit" value="OK" name="OK"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
