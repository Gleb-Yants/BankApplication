<%@ page contentType="text/html; charset=windows-1251" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
    <head>
        <title>�������� �������</title>
    </head>
 
    <body>
        <form action="<c:url value="/addClient"/>" method="POST">
<p/>
        <b>�������� �������<b>
            <br/>
            <table>
                <tr>
                    <td>���:</td><td><input type="text" name="name" value="${client.name}"/></td>
                </tr>
                <tr>
                    <td>�����:</td><td><input type="text" name="address" value="${client.address}"/></td>
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