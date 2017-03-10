<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/addCountry">
    <input type="text" name="name">
    <input type="text" name="president">
    <input type="text" name="continent">
    <p><select  name="isfederation" >
        <option value="federation">FEDERATION</option>
        <option value="confederation">CONFEDERATION</option>
    </select>
    </p>
    <input type="submit">
</form>
</body>
</html>