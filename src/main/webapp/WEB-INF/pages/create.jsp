<%@ page import="static com.gmail.pashasimonpure.controller.constant.RegexConstant.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Create User</title>
        <link rel='stylesheet' href='css/style.css'>
    </head>

    <body>
        <div class="block">
            <h2>New User Form</h2>

            <form action="create" method="post">
                <lable class="form_label">User Name:</lable>
                <input class="form_input" type="text" name="name" placeholder="required" required pattern="<%= USER_NAME_REGEX %>">
                <br>
                <lable class="form_label">Password:</lable>
                <input class="form_input" type="password" name="password" placeholder="required" required pattern="<%= PASSWORD_REGEX %>">
                <br>
                <lable class="form_label">Age:</lable>
                <input class="form_input" type="text" name="age" placeholder="required" required pattern="<%= AGE_REGEX %>">
                <br>
                <lable class="form_label">Is Active:</lable>
                <select class="form_input" name="isActive">
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
                <br>
                <p>Additional information :</p>
                <lable class="form_label">Address:</lable>
                <input class="form_input" type="text" name="address" placeholder="required" required pattern="<%= ADDRESS_REGEX %>">
                <br>
                <lable class="form_label">Telephone:</lable>
                <input class="form_input" type="text" name="telephone" placeholder="Format: +375259452959" required
                       pattern="<%= PHONE_NUMBER_REGEX %>">
                <br><br>
                <input class="myButton" type="submit" value="Add User (submit)">
                <input class="myButton" type="reset" value="Reset">
            </form>

            <p>If you click the "Submit" button, the form-data will be sent to a page called "/create".</p>
            <a href="users">User List</a>
        </div>
    </body>
</html>