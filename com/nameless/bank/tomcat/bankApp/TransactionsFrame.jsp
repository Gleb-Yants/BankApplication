<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>������ ����������</title>
    </head>

<body>
<form action="<c:url value="/transactions"/>" method="POST">
            <p/>
        <b>������ ����������:<b>
            <br/>
            <table>
                <tr>
                    <th>�� ����</th>
                    <th>����</th>
                    <th>���������� ����� (� ���.)</th>
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
                    <td><input type="submit" value="� ������ ��������" name="Cancel"/></td>
                </tr>
            </table>
            
        </form>
   </body>
<html>