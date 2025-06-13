<%-- 
    Document   : test
    Created on : Jun 13, 2025, 9:42:28 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/seller/manage-order" method="get">
            <input name="status"/>
             <input name="paymentMethod"/>
              <input name="search"/>
              <input type="submit"/>
        </form>
        <c:forEach items="${orders}" var="o">
            <h1>${o.orderId}}</h1>
    </c:forEach>
    </body>
</html>
