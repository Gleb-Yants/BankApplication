<%@ page contentType="text/html; charset=windows-1251" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
    <head>
        <title>Добавить аккаунт</title>
    </head>
 
    <body>
        <form action="<c:url value="/addAccount"/>" method="POST">
        <input type="hidden" name="clientId" value="${form.clientId}"/>
<p/>
        <b>Добавить аккаунт<b>
            <br/>
            <table>
                <tr>
                     <td><c:out value="${form.clientName}"/></td>
                </tr>
                <tr>
                    <td>Вклад:</td><td><input type="text" name="money" value="${form.money}"/></td>
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