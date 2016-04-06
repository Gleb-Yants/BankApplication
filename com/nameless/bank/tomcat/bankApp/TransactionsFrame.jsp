<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Список транзакций</title>
    </head>

<body>
<form action="<c:url value="/transactions"/>" method="POST">
            <p/>
        <b>Список транзакций:<b>
            <br/>
            <table>
                <tr>
                    <th>От кого</th>
                    <th>Кому</th>
                    <th>Количество денег (в руб.)</th>
                </tr>
                <c:forEach var="transaction" items="${form.transactions}">
                <tr>
                    <td><c:out value="${transaction.from.holder.name}"/></td>
                    <td><c:out value="${transaction.to.holder.name}"/></td>
                    <td><c:out value="${transaction.amount}"/></td>
                </tr>
                </c:forEach>
            </table>
                
            <table>
                <tr>
                    <td><input type="submit" value="К списку клиентов" name="Cancel"/></td>
                </tr>
            </table>
            
        </form>
   </body>
<html>
