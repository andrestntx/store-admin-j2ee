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
        <title>ver producto</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Producto Guardado</h1>

            <dl class="dl-horizontal">
                <dt>Nombre:</dt> <dd>${requestScope.product.name}</dd>
                <dt>Precio:</dt> <dd>${requestScope.product.price}</dd>
                <dt>Descripción</dt> <dd>${requestScope.product.description}</dd>
            </dl>
            <a class="btn btn-info" href="/storeAdmins/admin/products?product=${requestScope.product.id}">Editar Producto</a>
            <a class="btn btn-info" href="/storeAdmins/admin/products">Ver Producto</a>
            
        </div>
    </body>
</html>