<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Список аккаунтов</title>
    </head>

<body>
<form action="<c:url value="/accounts"/>" method="POST">
<input type="hidden" name="clientId" value="${form.clientId}"/>
            <p/>
        <b>Список аккаунтов:<b>
            <br/>
            <table>
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Держатель счета</th>
                    <th>Количество денег (в руб.)</th>
                </tr>
                <c:forEach var="account" items="${form.accounts}">
                <tr>
                    <td><input type="radio" name="accountId" value="${account.id}"></td>
                    <td><c:out value="${account.id}"/></td>
                    <td><c:out value="${account.holder.name}"/></td>
                    <td><c:out value="${account.money}"/></td>
                </tr>
                </c:forEach>
            </table>
                
            <table>
                <tr>
                    <td><input type="submit" value="Добавить аккаунт" name="AddAcc"/></td>
                    <td><input type="submit" value="Удалить аккаунт" name="Remove"/></td>
                    <td><input type="submit" value="Список транзакций" name="Transactions"/></td>
                    <td><input type="submit" value="Перевод денег" name="Transact"/></td>
                    <td><input type="submit" value="К списку клиентов" name="Cancel"/></td>
                </tr>
            </table>
            
        </form>
   </body>
<html>
