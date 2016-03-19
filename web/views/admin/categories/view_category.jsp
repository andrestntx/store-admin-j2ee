<%-- 
    Document   : view_user
    Created on : 18/03/2016, 09:00:10 AM
    Author     : Felipe Iz
--%>

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
            <h1 class="title-page">Categoría Guardada</h1>

            <dl class="dl-horizontal">
                <dt>Nombre:</dt> <dd>${requestScope.category.name}</dd>                              
            </dl>
            <a class="btn btn-info" href="/storeAdmins/admin/catagories?category=${requestScope.category.id}">Editar Categoría</a>
            <a class="btn btn-info" href="/storeAdmins/admin/catagories">Ver Categoría</a>
            
        </div>
    </body>
</html>
