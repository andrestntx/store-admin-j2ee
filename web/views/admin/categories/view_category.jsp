<%-- 
    Document   : view_user
    Created on : 18/03/2016, 09:00:10 AM
    Author     : Felipe Iz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Categoría</h1>
            
            <c:if test="${message != null}" var="exsits_message" scope="request">
                <p class="alert alert-info">${requestScope.message}</p>
            </c:if>
            
            <dl class="dl-horizontal">
                <dt>Nombre:</dt> <dd>${requestScope.category.name}</dd>                              
                <dt>Descripción</dt> <dd>${requestScope.category.description}</dd>                              
            </dl>
            <a class="btn btn-success" href="/storeAdmins/admin-categories">Categorías</a>
            <a class="btn btn-warning" href="/storeAdmins/admin-categories?category=${requestScope.category.id}&option=edit">Editar</a>
        </div>
    </body>
</html>
