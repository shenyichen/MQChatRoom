<%--
  Created by IntelliJ IDEA.
  User: hxu
  Date: 7/2/17
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>User Rank List</title>
    <meta http-equiv="refresh" content="10">
</head>

<body>
<h2>User Rank List</h2>

<table border="1">
    <tr>
        <th>Rank</th>
        <th>User</th>
        <th>Count</th>
    </tr>
    
    <c:forEach items="${Ranks}" var="ranko">
        <tr>
            <td>${ranko.rank}</td>
            <td>${ranko.uname}</td>
            <td>${ranko.count}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>