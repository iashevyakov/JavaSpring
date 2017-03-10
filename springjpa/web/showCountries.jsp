<%@ page import="ru.itis.models.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style1.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>
<% List<Country> countries = (List<Country>) request.getAttribute("countries");%>
<table  cellspacing="0">
    <tr>
        <th>Country</th>
        <th>Continent</th>
        <th>President</th>
        <th>Type</th>
    </tr>
    <%String type;%>
    <%for (int i=0; i<countries.size();i++){
        if (countries.get(i).isFederation()){
            type="Federation";
        }
        else{
            type="Confederation";
        }%>
    <tr><td><%=countries.get(i).getCountryName()%></td> <td><%=countries.get(i).getContinent()%></td><td><%=countries.get(i).getPresidentName()%></td><td><%=type%></td></tr>
    <%}%>


</table>


</body>
</html>