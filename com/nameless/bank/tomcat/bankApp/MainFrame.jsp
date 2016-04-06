<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>������ ��������</title>
    </head>

<body>
<form action="<c:url value="/main"/>" method="POST">
            <p/>
        <b>������ �������� �����:<b>
            <br/>
            <table>
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>���</th>
                    <th>�����</th>
                </tr>
                <c:forEach var="client" items="${form.clients}">
                <tr>
                    <td><input type="radio" name="clientId" value="${client.id}"></td>
                    <td><c:out value="${client.id}"/></td>
                    <td><c:out value="${client.name}"/></td>
                    <td><c:out value="${client.address}"/></td>
                </tr>
                </c:forEach>
            </table>
                
            <table>
                <tr>
                    <td><input type="submit" value="�������� �������" name="Add"/></td>
                    <td><input type="submit" value="������� �������" name="Accounts"/></td>
                </tr>
            </table>
            
        </form>
   </body>
<html>