<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>������ ���������</title>
    </head>

<body>
<form action="<c:url value="/accounts"/>" method="POST">
<input type="hidden" name="clientId" value="${form.clientId}"/>
            <p/>
        <b>������ ���������:<b>
            <br/>
            <table>
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>��������� �����</th>
                    <th>���������� ����� (� ���.)</th>
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
                    <td><input type="submit" value="�������� �������" name="AddAcc"/></td>
                    <td><input type="submit" value="������� �������" name="Remove"/></td>
                    <td><input type="submit" value="������ ����������" name="Transactions"/></td>
                    <td><input type="submit" value="������� �����" name="Transact"/></td>
                    <td><input type="submit" value="� ������ ��������" name="Cancel"/></td>
                </tr>
            </table>
            
        </form>
   </body>
<html>