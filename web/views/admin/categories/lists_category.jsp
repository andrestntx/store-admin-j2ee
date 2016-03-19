<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : lists_users
    Created on : 18/03/2016, 10:06:29 AM
    Author     : Felipe Iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/style.css">
        <title>Ver Categorías</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Listado de Categorías</h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nombre</th>                        
                    </tr>
                </thead>
                <c:forEach var="$category" items="${categories}"> 
                    <tbody>
                        <tr>
                            <td>${category.getName()}</td>                             
                        </tr>
                    </tbody>
                 </c:forEach>
            </table>
        </div>        
    </body>                 
</html>
