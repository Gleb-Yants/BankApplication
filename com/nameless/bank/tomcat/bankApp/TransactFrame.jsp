<%@ page contentType="text/html; charset=windows-1251" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
    <head>
        <title>Осуществить перевод</title>
    </head>
 
    <body>
        <form action="<c:url value="/transact"/>" method="POST">
        <input type="hidden" name="fromId" value="${form.fromId}"/>
<tr><b>Осуществить транзакцию</b></tr>
            <table>
                <tr>
                     <b><c:out value="${form.fromName}"/></b>
                </tr>
<tr>
<td>Аккаунт получателя:
                        <select name="accTo">
                        <c:forEach var="account" items="${form.to}">
                            <option value="${account.id}"><c:out value="${account.holder.name}"/></option>
                        </c:forEach>
                        </select>
                    </td>
</tr> </table>
                <tr>
                    <td>Сумма перевода:</td><td><input type="text" name="sum" value="${form.sum}"/></td>
                </tr>               
            <table>
                <tr>
                    <td><input type="submit" value="OK" name="OK"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
