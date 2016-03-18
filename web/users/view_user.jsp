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
        <link rel="stylesheet" href="/storeAdmins/css/bootstrap.min.css">
        <link rel="stylesheet" href="/storeAdmins/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Usuario Registrado</h1>

            <dl class="dl-horizontal">
                <dt>Nombre:</dt> <dd>${requestScope.user.name}</dd>
                <dt>Nombre de usuario:</dt> <dd>${requestScope.user.username}</dd>
                <dt>Email:</dt> <dd>${requestScope.user.email}</dd>                
            </dl>
            <a class="btn btn-info" href="/storeAdmins/UserController?funcion=1&id=${requestScope.user.id}">Editar Usuario</a>
            <a class="btn btn-info" href="/storeAdmins/UserController?funcion=2">Ver Usuarios</a>
            
        </div>
    </body>
</html>
