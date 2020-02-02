<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <title>User List</title>
        <link rel='stylesheet' href='css/table.css'>
        <link rel='stylesheet' href='css/style.css'>
    </head>

    <body>
        <h2>User List</h2>
        <table >
            <tr>
                <td>User ID</td>
                <td>User Name</td>
                <td>Password</td>
                <td>Is Active</td>
                <td>Age</td>
                <td>Address</td>
                <td>Telephone</td>
                <td>Action</td>
            </tr>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:choose>
                        <c:when test="${user.active}">
                            I am a superman
                        </c:when>
                        <c:otherwise>
                            Staying at shadow
                        </c:otherwise>
                    </c:choose></td>
                    <td><c:out value="${user.age}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.telephone}"/></td>
                    <td>
                        <form action="delete" method="post">
                            <input type="hidden" name="id" value="${user.id}"/>
                            <input class="myButton" type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <br><a href="create">Click here to add new user.</a>
    </body>
</html>