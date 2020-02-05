<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div class="block">
            <h2>Message</h2>
                ${msg}
            <br><br>
            <a href="users">Show User List</a><br>
            <a href="create">Create New User</a>
        </div>
    </body>
</html>